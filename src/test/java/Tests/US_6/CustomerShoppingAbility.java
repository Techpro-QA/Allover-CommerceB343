package Tests.US_6;

import Pages.CustomerShoppingAbilityPage;
import com.github.javafaker.Faker;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
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
    public void checkProductElement(String searchData) {

        // siteye gidilir
        Driver.getDriver().get(ConfigReader.getProperty("alloverCommerceUrl"));

        CustomerShoppingAbilityPage customerShoppingAbilityPage = new CustomerShoppingAbilityPage();

        customerShoppingAbilityPage.searchBoxArea.sendKeys(searchData+ Keys.ENTER);

        String lowerCaseTitle = Driver.getDriver().getTitle().toLowerCase();
        Assert.assertTrue(lowerCaseTitle.contains(searchData.toLowerCase()));

    }

    //Aradığı ürünü sepete ekleyebilmeli (ADD TO CART)


    @Test
    public void addCart() {

        ExtentReportsListener.extentTestInfo("jugzguzgzugzugigi");
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


        customerShoppingAbilityPage.addCartLaptop.click();

        customerShoppingAbilityPage.cartButton.click();
        customerShoppingAbilityPage.viewCartButton.click();

        //Sepete (Cart) eklediği ürünleri görebilmeli

        Assert.assertTrue(customerShoppingAbilityPage.bookList.getText().contains("book"));
        Assert.assertTrue(customerShoppingAbilityPage.laptopList.getText().contains("laptop"));


        //Sepete eklediği ürünlerin miktarını artırabilmeli ve azaltabilmeli

        customerShoppingAbilityPage.bookMinus.click();
        customerShoppingAbilityPage.laptopPlus.click();

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

        //Ödeme seçeneklerini görebilmeli ve seçebilmeli

        Faker faker = new Faker();
        WaitUtils.waitFor(3);
        //String firstname=faker.name().firstName();

        customerShoppingAbilityPage.firstNameArea.sendKeys(faker.name().firstName());
        customerShoppingAbilityPage.lastNameArea.sendKeys(faker.name().lastName());
        ReusableMethods.ddmValue(customerShoppingAbilityPage.countryArea,"CA");
        customerShoppingAbilityPage.streetAddressArea.sendKeys(faker.address().streetAddress());
        customerShoppingAbilityPage.townArea.sendKeys(faker.address().city());
        ReusableMethods.ddmValue(customerShoppingAbilityPage.stateArea,"MB");

        customerShoppingAbilityPage.zipCodeArea.sendKeys("R3C 0V8");
        customerShoppingAbilityPage.phoneArea.sendKeys("2045551234");
        customerShoppingAbilityPage.emailArea.sendKeys("emreowen57@gmail.com");

        ReusableMethods.click(customerShoppingAbilityPage.payAtTheDoorButton);
        ReusableMethods.click(customerShoppingAbilityPage.placeOrderButton);


        JSUtils.JSscrollAllTheWayUp();
        System.out.println(customerShoppingAbilityPage.noShippingText.getText());
        Assert.assertTrue(customerShoppingAbilityPage.noShippingText.isDisplayed());

        US6ScreenUtils.captureScreen("noShippingText");



    }




}