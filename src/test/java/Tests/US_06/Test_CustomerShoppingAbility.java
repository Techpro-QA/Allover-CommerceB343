package Tests.US_06;

import Pages.CustomerShoppingAbilityPage;
import com.github.javafaker.Faker;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utilities.*;

public class Test_CustomerShoppingAbility {

    // Farklı ürünler için dataProvider kullanılıyor
    // Different products provided by DataProvider
    @DataProvider
    public static Object[][] products() {
        return new Object[][]{
                {"book"}, {"pencil"}, {"laptop"}, {"basketball"}, {"bag"}
        };
    }

    // Kullanıcı search box’tan istediği bir ürünü arayabilmeli
    // User should be able to search any product from the search box
    @Test(dataProvider = "products")
    public void searchForProduct(String searchData) {

        // Siteye gidilir
        // Navigate to the site
        ExtentReportsListenerUS_06.extentTestInfo("Allover Commerce sayfasina gidilir");
        Driver.getDriver().get(ConfigReader.getProperty("alloverCommerceUrl"));

        // Page objesi olusturulur
        // Page object is created
        CustomerShoppingAbilityPage customerShoppingAbilityPage = new CustomerShoppingAbilityPage();

        // Arama kutusuna ürün yazılır ve Enter’a basılır
        // Enter product name into search box and press Enter
        customerShoppingAbilityPage.searchBoxArea.sendKeys(searchData+ Keys.ENTER);

        WaitUtils.waitFor(2);

        // Ekran görüntüsü alınır
        // Take a screenshot
        US06ScreenUtils.captureScreen(searchData + " aranabildigi görüntülenir");

        // Sayfa başlığında aranan ürünün geçtiği doğrulanır
        // Verify searched product is included in page title
        String lowerCaseTitle = Driver.getDriver().getTitle().toLowerCase();
        Assert.assertTrue(lowerCaseTitle.contains(searchData.toLowerCase()));

    }

    // Kullanıcı aradığı ürünü sepete ekleyebilmeli ve alışveriş adımlarını tamamlayabilmeli
    // User should be able to add searched product to cart and complete shopping flow
    @Test
    public void testSuccessfulShoppingFlow() {

        // Siteye gidilir
        // Navigate to the site
        ExtentReportsListenerUS_06.extentTestInfo("Allover Commerce sayfasina gidilir");
        Driver.getDriver().get(ConfigReader.getProperty("alloverCommerceUrl"));
        CustomerShoppingAbilityPage customerShoppingAbilityPage = new CustomerShoppingAbilityPage();

        // Kitap aratılır ve sepete eklenir
        // Search book and add to cart
        customerShoppingAbilityPage.searchBoxArea.sendKeys("book");
        customerShoppingAbilityPage.searchBoxButton.click();
        customerShoppingAbilityPage.bookElement.click();
        // Bir kitap eklenir
        // Add one book
        customerShoppingAbilityPage.addCartBook.click();
        customerShoppingAbilityPage.addCartBook.click();

        // Laptop aratılır ve sepete eklenir
        // Search laptop and add to cart
        customerShoppingAbilityPage.searchBoxArea.sendKeys("laptop");
        customerShoppingAbilityPage.searchBoxButton.click();
        customerShoppingAbilityPage.laptopElement.click();

        ExtentReportsListenerUS_06.extentTestInfo("Sepete istenen ürünler eklenir");

        // Sepete gidilir
        // Go to cart
        customerShoppingAbilityPage.addCartLaptop.click();

        customerShoppingAbilityPage.cartButton.click();
        WaitUtils.waitFor(2);

        US06ScreenUtils.captureScreen("Kullanicin sectigi ürünler görüntülenir");
        customerShoppingAbilityPage.viewCartButton.click();

        // Sepete eklenen ürünler görüntülenir
        // Verify added products are displayed in cart

        Assert.assertTrue(customerShoppingAbilityPage.bookList.getText().contains("book"));
        Assert.assertTrue(customerShoppingAbilityPage.laptopList.getText().contains("laptop"));


        // Sepette ürün miktarları artırılıp azaltılabilir
        // Quantities of products in cart can be increased/decreased

        customerShoppingAbilityPage.bookMinus.click();
        // Kitap sayısı azaltılır
        // Decrease book quantity
        customerShoppingAbilityPage.laptopPlus.click();
        // Laptop sayısı artırılır
        // Increase laptop quantity

        US06ScreenUtils.captureScreen("Ürünlerde artirma ve azaltma yapildigi görüntülenir");
        ActionsUtils.scrollDown();
        customerShoppingAbilityPage.updateCartButton.click();
        // Değişiklikler güncellenir
        // Update cart

        WaitUtils.waitFor(3);

        // Sepette fiyat doğrulamaları yapılır
        // Verify price calculations in cart
        Assert.assertEquals(customerShoppingAbilityPage.oneLaptopPrice.getText(),"$540.00");
        Assert.assertEquals(customerShoppingAbilityPage.subtotalLaptopPrice.getText(),"$1,080.00");
        Assert.assertEquals(customerShoppingAbilityPage.subtotalBookPrice.getText(),"$100.00");
        Assert.assertEquals(customerShoppingAbilityPage.oneBookPrice.getText(),"$100.00");

        // Kullanıcı checkout adımında fatura adresini görebilmeli
        // User should see billing address at checkout

        WaitUtils.waitForClickablility(customerShoppingAbilityPage.proceedToCheckoutButton,3);
        ReusableMethods.click(customerShoppingAbilityPage.proceedToCheckoutButton);
        Assert.assertTrue(customerShoppingAbilityPage.billingDetailText.isDisplayed());
        US06ScreenUtils.captureScreen("Fatura adresi görüntülenir");

        // Ödeme adımı ve kullanıcı bilgileri doldurulur
        // Fill payment step and user details

        Faker faker = new Faker();
        WaitUtils.waitFor(3);

        // Kullanıcı bilgileri formu doldurulur
        // Fill in user billing information
        customerShoppingAbilityPage.firstNameArea.sendKeys("Emre");
        customerShoppingAbilityPage.lastNameArea.sendKeys("Mentes");
        ReusableMethods.ddmValue(customerShoppingAbilityPage.countryArea,"DE");
        customerShoppingAbilityPage.streetAddressArea.sendKeys(faker.address().streetAddress());
        customerShoppingAbilityPage.townArea.sendKeys("Konstanz");
        ReusableMethods.ddmValue(customerShoppingAbilityPage.stateArea,"DE-BW");
        customerShoppingAbilityPage.zipCodeArea.sendKeys("34400");
        customerShoppingAbilityPage.phoneArea.sendKeys("5444444444");
        customerShoppingAbilityPage.emailArea.sendKeys("emreowen57@gmail.com");

        // Ödeme yöntemi seçilir ve sipariş tamamlanır
        // Select payment method and place the order
        ReusableMethods.click(customerShoppingAbilityPage.payAtTheDoorButton);
        ReusableMethods.click(customerShoppingAbilityPage.placeOrderButton);

        // Siparişin başarıyla tamamlandığı doğrulanır
        // Verify order has been successfully placed
        Assert.assertTrue(customerShoppingAbilityPage.thankYouMessage.isDisplayed());
        US06ScreenUtils.captureScreen("Alisveris yapildigi dogrulandi");

    }

}