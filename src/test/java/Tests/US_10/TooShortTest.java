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

public class TooShortTest {

    @Test(dataProvider = "TOOSHORT", dataProviderClass = DataProvidersUS10.class)
    public void tooShortText(String data) throws IOException {

        ExtentReportsListenerUS_10.extentTestInfo("Allover Commerce sayfasina gidilir");

        // Siteye gidilir
        // Navigate to the site
        Driver.getDriver().get(ConfigReader.getProperty("alloverCommerceUrl"));

        VendorRegistrationPage alloverCommercePage = new VendorRegistrationPage();

        // Register butonuna tıklanır
        // Click register button
        alloverCommercePage.registerButton.click();

        // Açılan popup formunda "Signup as a vendor?" a tıklanır
        // In the popup, click on "Signup as a vendor?"
        alloverCommercePage.signupAsAVendorButton.click();

        Faker faker = new Faker();
        Actions action = new Actions(Driver.getDriver());

        ExtentReportsListenerUS_10.extentTestInfo("Driverin locateleri tam görebilmesi için sayfa scroll edilir");

        // Sayfa scroll edilir
        // Scroll page to make locators visible
        action.scrollToElement(alloverCommercePage.vendorRegistrationRegisterButton).perform();

        ExtentReportsListenerUS_10.extentTestInfo("Email girişi yapılır");

        // Email için geçerli bir data girilir
        // Enter a valid email
        ReusableMethods.sendKeysJS(alloverCommercePage.vendorRegistrationEmailTextBox, faker.internet().emailAddress());

        // "RE-SEND CODE" a tıklanır
        // Click on "RE-SEND CODE"
        alloverCommercePage.resendCodeButton.click();

        ExtentReportsListenerUS_10.extentTestInfo("Password girişi yapılır");
        ExtentReportsListenerUS_10.extentTestInfo("Girilen password ==>>  " + data);

        // Password için kısa bir data girilir
        // Enter a short password
        action.sendKeys(alloverCommercePage.vendorRegistrationPasswordTextBox, data).perform();

        ExtentReportsListenerUS_10.extentTestInfo("Password 'Too short' uyarısı doğrulanır");

        // "Too short" textinin görüntülendiği doğrulanır
        // Verify that "Too short" text is displayed
        Assert.assertTrue(alloverCommercePage.tooShortTextBox.isDisplayed());

        // "Too short" texti ekran görüntüsü rapora ekleniyor
        // Capture screenshot of "Too short" text and add to report
        Tests.US_10.US10ScreenUtils.captureScreen("tooShortTextBox görüntülenir");

        // Confirm Password alanına, Password alanına yazılan şifrenin aynısı yazılır
        // Enter the same password again into Confirm Password field
        action.sendKeys(alloverCommercePage.vendorRegistrationConfirmPasswordTextBox, data).perform();

        // Driver kapatılır
        // Close the driver
        Driver.quitDriver();
    }
}