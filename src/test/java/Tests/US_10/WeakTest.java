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

public class WeakTest {
    @Test
    public void weakTest() {

        ExtentReportsListener.extentTestInfo("Allover Commerce sayfasina gidilir");

        // siteye gidilir
        Driver.getDriver().get(ConfigReader.getProperty("alloverCommerceUrl"));

        VendorRegistrationPage alloverCommercePage = new VendorRegistrationPage();

        // register butonuna tiklanir
        alloverCommercePage.registerButton.click();

        // Acilan popup formunda Signup as a vendor? a tiklanir
        alloverCommercePage.signupAsAVendorButton.click();

        Faker faker = new Faker();
        Actions action = new Actions(Driver.getDriver());

        ExtentReportsListener.extentTestInfo("Driverin locateleri tam görebilmesi icin sayfa scroll edilir");
        action.scrollToElement(alloverCommercePage.vendorRegistrationRegisterButton).perform();

        ExtentReportsListener.extentTestInfo("Email girisi yapilir");

        // Email icin gecerli bir data girilir
        ReusableMethods.sendKeysJS(alloverCommercePage.vendorRegistrationEmailTextBox, faker.internet().emailAddress());

        // RE-SEND CODE a tiklanir
        alloverCommercePage.resendCodeButton.click();

        ExtentReportsListener.extentTestInfo("Password girisi yapilir");

        // Password icin gecerli bir data girilir
        action.sendKeys(alloverCommercePage.vendorRegistrationPasswordTextBox, "Emremm").perform();

        ExtentReportsListener.extentTestInfo("Password 'Weak' uyarısı doğrulanıyor");
        Assert.assertTrue(alloverCommercePage.weakTextBox.isDisplayed());

        // Confirm Password alanına, Password alanına yazılan şifrenin aynısı yazılır
        action.sendKeys(alloverCommercePage.vendorRegistrationConfirmPasswordTextBox, "Emremm").perform();

        // Driver kapatılır
        Driver.quitDriver();
    }
}