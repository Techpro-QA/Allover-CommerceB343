package Tests.US_10;

import Pages.VendorRegistrationPage;
import com.github.javafaker.Faker;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;
import java.io.IOException;

public class WeakTest {

    @Test(dataProvider = "WEAK",dataProviderClass = DataProvidersUS10.class)
    public void weakTest(String data) throws IOException {

        ExtentReportsListenerUS_10.extentTestInfo("Allover Commerce sayfasina gidilir");

        // siteye gidilir
        Driver.getDriver().get(ConfigReader.getProperty("alloverCommerceUrl"));

        VendorRegistrationPage alloverCommercePage = new VendorRegistrationPage();

        // register butonuna tiklanir
        alloverCommercePage.registerButton.click();

        // Acilan popup formunda Signup as a vendor? a tiklanir
        alloverCommercePage.signupAsAVendorButton.click();

        Faker faker = new Faker();
        Actions action = new Actions(Driver.getDriver());

        ExtentReportsListenerUS_10.extentTestInfo("Driverin locateleri tam görebilmesi icin sayfa scroll edilir");
        action.scrollToElement(alloverCommercePage.vendorRegistrationRegisterButton).perform();

        ExtentReportsListenerUS_10.extentTestInfo("Email girisi yapilir");

        // Email icin gecerli bir data girilir
        ReusableMethods.sendKeysJS(alloverCommercePage.vendorRegistrationEmailTextBox, faker.internet().emailAddress());

        // RE-SEND CODE a tiklanir
        alloverCommercePage.resendCodeButton.click();

        ExtentReportsListenerUS_10.extentTestInfo("Password girisi yapilir");
        ExtentReportsListenerUS_10.extentTestInfo("Girilen password ==>>  " + data);


        // Password icin gecerli bir data girilir
        action.sendKeys(alloverCommercePage.vendorRegistrationPasswordTextBox, data).perform();

        ExtentReportsListenerUS_10.extentTestInfo("Password 'Weak' uyarısı doğrulanır");
        Assert.assertTrue(alloverCommercePage.weakTextBox.isDisplayed());

        //Weak texti ekran görüntüsü reporta ekleniyor
        Tests.US_10.US10ScreenUtils.captureScreen("weakTextBox görüntülenir");

        // Confirm Password alanına, Password alanına yazılan şifrenin aynısı yazılır
        action.sendKeys(alloverCommercePage.vendorRegistrationConfirmPasswordTextBox, data).perform();

        // Driver kapatılır
        Driver.quitDriver();
    }
}