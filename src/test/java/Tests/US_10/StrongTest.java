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

public class StrongTest {
    @Test
    public void strongTest() {
        ExtentReportsListener.extentTestInfo("Allover Commerce sayfasina gidilir");
        Driver.getDriver().get(ConfigReader.getProperty("alloverCommerceUrl"));
        VendorRegistrationPage alloverCommercePage = new VendorRegistrationPage();
        alloverCommercePage.registerButton.click();
        alloverCommercePage.signupAsAVendorButton.click();
        Faker faker = new Faker();
        Actions action = new Actions(Driver.getDriver());

        ExtentReportsListener.extentTestInfo("Driverin locateleri tam görebilmesi icin sayfa scroll edilir");
        action.scrollToElement(alloverCommercePage.vendorRegistrationRegisterButton).perform();

        ExtentReportsListener.extentTestInfo("Email girisi yapilir");
        ReusableMethods.sendKeysJS(alloverCommercePage.vendorRegistrationEmailTextBox, faker.internet().emailAddress());
        alloverCommercePage.resendCodeButton.click();
        ExtentReportsListener.extentTestInfo("Password girisi yapilir");
        action.sendKeys(alloverCommercePage.vendorRegistrationPasswordTextBox, "Emre57*").perform();
        ExtentReportsListener.extentTestInfo("Password 'Strong' uyarısı doğrulanıyor");
        Assert.assertTrue(alloverCommercePage.strongTextBox.isDisplayed());

        action.sendKeys(alloverCommercePage.vendorRegistrationConfirmPasswordTextBox, "Emre57*").perform();
        Driver.quitDriver();

    }
}
