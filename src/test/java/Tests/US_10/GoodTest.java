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

public class GoodTest {

    @Test(dataProvider = "GOOD",dataProviderClass = DataProvidersUS10.class)
    public void goodTest(String data) throws IOException {

        ExtentReportsListenerUS_10.extentTestInfo("Allover Commerce sayfasina gidilir");

        // Siteye gidilir
        // Navigate to the site
        Driver.getDriver().get(ConfigReader.getProperty("alloverCommerceUrl"));

        VendorRegistrationPage alloverCommercePage = new VendorRegistrationPage();

        // Register butonuna tıklanır
        // Click register button
        alloverCommercePage.registerButton.click();

        // Acılan popup formunda "Signup as a vendor?" a tıklanır
        // In the popup, click on "Signup as a vendor?"
        alloverCommercePage.signupAsAVendorButton.click();

        Faker faker = new Faker();
        Actions action = new Actions(Driver.getDriver());

        ExtentReportsListenerUS_10.extentTestInfo("Driverin locateleri tam görebilmesi icin sayfa scroll edilir");

        // Sayfa scroll edilir
        // Scroll page to make locators visible
        action.scrollToElement(alloverCommercePage.vendorRegistrationRegisterButton).perform();

        ExtentReportsListenerUS_10.extentTestInfo("Email girisi yapilir");

        // Email için geçerli bir data girilir
        // Enter a valid email
        ReusableMethods.sendKeysJS(alloverCommercePage.vendorRegistrationEmailTextBox, faker.internet().emailAddress());

        // "RE-SEND CODE" a tıklanır
        // Click on "RE-SEND CODE"
        alloverCommercePage.resendCodeButton.click();

        ExtentReportsListenerUS_10.extentTestInfo("Password girisi yapilir");
        ExtentReportsListenerUS_10.extentTestInfo("Girilen password ==>>  " + data);

        // Password için geçerli bir data girilir
        // Enter a valid password
        action.sendKeys(alloverCommercePage.vendorRegistrationPasswordTextBox, data).perform();

        ExtentReportsListenerUS_10.extentTestInfo("Password 'Good' uyarısı doğrulanır");
        Assert.assertTrue(alloverCommercePage.goodTextBox.isDisplayed());

        // "Good" textinin görüntülendiği doğrulanır
        // Verify that "Good" text is displayed
        Tests.US_10.US10ScreenUtils.captureScreen("goodTextBox görüntülenir");

        // Confirm Password alanına, Password alanına yazılan şifrenin aynısı yazılır
        // Enter the same password again into Confirm Password field
        action.sendKeys(alloverCommercePage.vendorRegistrationConfirmPasswordTextBox, data).perform();

        // Driver kapatılır
        // Close the driver
        Driver.quitDriver();

    }
}
