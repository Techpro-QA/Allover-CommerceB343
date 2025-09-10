package Tests.US_06;

import Pages.CustomerShoppingAbilityPage;
import com.github.javafaker.Faker;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utilities.*;

public class CustomerShoppingAbility {

    @DataProvider
    public static Object[][] products() {
        return new Object[][]{
                {"book"}, {"pencil"}, {"laptop"}, {"basketball"}, {"bag"}
        };
    }


    //Kullanıcı search box tan istediği bir ürünü arayabilmeli
    @Test(dataProvider = "products")
    public void searchForProduct(String searchData) {

        // siteye gidilir

        ExtentReportsListener.extentTestInfo("Allover Commerce sayfasina gidilir");
        Driver.getDriver().get(ConfigReader.getProperty("alloverCommerceUrl"));

        CustomerShoppingAbilityPage customerShoppingAbilityPage = new CustomerShoppingAbilityPage();

        customerShoppingAbilityPage.searchBoxArea.sendKeys(searchData+ Keys.ENTER);
        WaitUtils.waitFor(2);
        US06ScreenUtils.captureScreen(searchData + " aranabildigi görüntülenir");

        String lowerCaseTitle = Driver.getDriver().getTitle().toLowerCase();
        Assert.assertTrue(lowerCaseTitle.contains(searchData.toLowerCase()));

    }

    //Aradığı ürünü sepete ekleyebilmeli (ADD TO CART)


    @Test
    public void testSuccessfulShoppingFlow() {

        ExtentReportsListener.extentTestInfo("Allover Commerce sayfasina gidilir");
        Driver.getDriver().get(ConfigReader.getProperty("alloverCommerceUrl"));
        CustomerShoppingAbilityPage customerShoppingAbilityPage = new CustomerShoppingAbilityPage();

        customerShoppingAbilityPage.searchBoxArea.sendKeys("book");
        customerShoppingAbilityPage.searchBoxButton.click();
        customerShoppingAbilityPage.bookElement.click();
        customerShoppingAbilityPage.addCartBook.click();
        customerShoppingAbilityPage.addCartBook.click();

        customerShoppingAbilityPage.searchBoxArea.sendKeys("laptop");
        customerShoppingAbilityPage.searchBoxButton.click();
        customerShoppingAbilityPage.laptopElement.click();

        ExtentReportsListener.extentTestInfo("Sepete istenen ürünler eklenir");

        customerShoppingAbilityPage.addCartLaptop.click();

        customerShoppingAbilityPage.cartButton.click();
        WaitUtils.waitFor(2);

        US06ScreenUtils.captureScreen("Kullanicin sectigi ürünler görüntülenir");
        customerShoppingAbilityPage.viewCartButton.click();

        //Sepete (Cart) eklediği ürünleri görebilmeli

        Assert.assertTrue(customerShoppingAbilityPage.bookList.getText().contains("book"));
        Assert.assertTrue(customerShoppingAbilityPage.laptopList.getText().contains("laptop"));

        //Sepete eklediği ürünlerin miktarını artırabilmeli ve azaltabilmeli

        customerShoppingAbilityPage.bookMinus.click();
        customerShoppingAbilityPage.laptopPlus.click();

        US06ScreenUtils.captureScreen("Ürünlerde artirma ve azaltma yapildigi görüntülenir");
        ActionsUtils.scrollDown();
        customerShoppingAbilityPage.updateCartButton.click();
        WaitUtils.waitFor(3);

        Assert.assertEquals(customerShoppingAbilityPage.oneLaptopPrice.getText(),"$540.00");
        Assert.assertEquals(customerShoppingAbilityPage.subtotalLaptopPrice.getText(),"$1,080.00");
        Assert.assertEquals(customerShoppingAbilityPage.subtotalBookPrice.getText(),"$100.00");
        Assert.assertEquals(customerShoppingAbilityPage.oneBookPrice.getText(),"$100.00");


        //Ürünleri satınalabilmek için fatura adresini görebilmeli

        WaitUtils.waitForClickablility(customerShoppingAbilityPage.proceedToCheckoutButton,3);
        ReusableMethods.click(customerShoppingAbilityPage.proceedToCheckoutButton);
        Assert.assertTrue(customerShoppingAbilityPage.billingDetailText.isDisplayed());
        US06ScreenUtils.captureScreen("Fatura adresi görüntülenir");

        //Ödeme seçeneklerini görebilmeli ve seçebilmeli

        Faker faker = new Faker();
        WaitUtils.waitFor(3);
        //String firstname=faker.name().firstName();

        customerShoppingAbilityPage.firstNameArea.sendKeys("Emre");
        customerShoppingAbilityPage.lastNameArea.sendKeys("Mentes");
        ReusableMethods.ddmValue(customerShoppingAbilityPage.countryArea,"DE");
        customerShoppingAbilityPage.streetAddressArea.sendKeys(faker.address().streetAddress());
        customerShoppingAbilityPage.townArea.sendKeys("Konstanz");
        ReusableMethods.ddmValue(customerShoppingAbilityPage.stateArea,"DE-BW");

        customerShoppingAbilityPage.zipCodeArea.sendKeys("34400");
        customerShoppingAbilityPage.phoneArea.sendKeys("5444444444");
        customerShoppingAbilityPage.emailArea.sendKeys("emreowen57@gmail.com");

        ReusableMethods.click(customerShoppingAbilityPage.payAtTheDoorButton);
        ReusableMethods.click(customerShoppingAbilityPage.placeOrderButton);

        Assert.assertTrue(customerShoppingAbilityPage.thankYouMessage.isDisplayed());

        US06ScreenUtils.captureScreen("Alisveris yapildigi dogrulandi");




    }




}