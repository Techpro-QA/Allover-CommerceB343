package Tests.US_10;

import Pages.VendorRegistrationPage;
import com.github.javafaker.Faker;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ExtentReportsListener;
import utilities.ReusableMethods;
import java.io.IOException;

public class GoodTest {

    @Test(dataProvider = "GOOD",dataProviderClass = DataProvidersUS10.class)
    public void goodTest(String data) throws IOException {

        ExtentReportsListener.extentTestInfo("Allover Commerce sayfasina gidilir");

        //siteye gidilir
        Driver.getDriver().get(ConfigReader.getProperty("alloverCommerceUrl"));

        VendorRegistrationPage alloverCommercePage = new VendorRegistrationPage();

        //register butonuna tiklanir
        alloverCommercePage.registerButton.click();

        //Acilan popup formunda Signup as a vendor? a tiklanir
        alloverCommercePage.signupAsAVendorButton.click();

        Faker faker = new Faker();
        Actions action = new Actions(Driver.getDriver());

        ExtentReportsListener.extentTestInfo("Driverin locateleri tam görebilmesi icin sayfa scroll edilir");
        action.scrollToElement(alloverCommercePage.vendorRegistrationRegisterButton).perform();

        ExtentReportsListener.extentTestInfo("Email girisi yapilir");

        //Email icin gecerli bir data girilir
        ReusableMethods.sendKeysJS(alloverCommercePage.vendorRegistrationEmailTextBox, faker.internet().emailAddress());

        //RE-SEND CODE a tiklanir
        alloverCommercePage.resendCodeButton.click();

        ExtentReportsListener.extentTestInfo("Password girisi yapilir");

        //Password icin gecerli bir data girilir
        action.sendKeys(alloverCommercePage.vendorRegistrationPasswordTextBox, data).perform();

        ExtentReportsListener.extentTestInfo("Password 'Good' uyarısı doğrulanıyor");
        Assert.assertTrue(alloverCommercePage.goodTextBox.isDisplayed());

        //Good texti ekran görüntüsü reporta ekleniyor
        US10ScreenUtils.captureScreen("goodTextBox");

        //Confirm Password alanına, Password alanına yazılan şifrenin aynısı yazılır.
        action.sendKeys(alloverCommercePage.vendorRegistrationConfirmPasswordTextBox, data).perform();

        // Driver kapatılır
        Driver.quitDriver();

    }
}
