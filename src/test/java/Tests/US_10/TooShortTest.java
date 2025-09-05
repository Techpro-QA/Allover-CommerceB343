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

public class TooShortTest {
    @Test
    public void tooShortText() {

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
        action.sendKeys(alloverCommercePage.vendorRegistrationPasswordTextBox, "Emre1").perform();
        ExtentReportsListener.extentTestInfo("Password 'Too short' uyarısı doğrulanıyor");
        Assert.assertTrue(alloverCommercePage.tooShortTextBox.isDisplayed());

        action.sendKeys(alloverCommercePage.vendorRegistrationConfirmPasswordTextBox, "Emre1").perform();
        Driver.quitDriver();


        //alloverCommercePage.vendorRegistrationRegisterButton.click();
        //siteye kayit mümkün olamadigindan dolayi hata veriyor

    }
}
