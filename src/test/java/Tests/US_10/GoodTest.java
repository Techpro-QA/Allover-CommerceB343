package Tests.US_10;

import Pages.VendorRegistrationPage;
import com.github.javafaker.Faker;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ExtentReportsListener;
import utilities.ReusableMethods;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GoodTest {
    @Test
    public void goodTest() throws IOException {

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
        action.sendKeys(alloverCommercePage.vendorRegistrationPasswordTextBox, "57emre...").perform();

        ExtentReportsListener.extentTestInfo("Password 'Good' uyarısı doğrulanıyor");
        Assert.assertTrue(alloverCommercePage.goodTextBox.isDisplayed());

        //Good texti ekran görüntüsü reporta ekleniyor
        Files.createDirectories(Paths.get("target/screenShots"));
        String date = DateTimeFormatter.ofPattern("ddMMyyyy_HHmmss").format(LocalDateTime.now());
        String path = "target/screenShots/goodTextBox_" + date + ".jpeg";

        Files.write(Paths.get(path), alloverCommercePage.goodTextBox.getScreenshotAs(OutputType.BYTES));
        ExtentReportsListener.extentTest.addScreenCaptureFromPath(System.getProperty("user.dir") + "/" + path);


        //Confirm Password alanına, Password alanına yazılan şifrenin aynısı yazılır.
        action.sendKeys(alloverCommercePage.vendorRegistrationConfirmPasswordTextBox, "57emre...").perform();

        // Driver kapatılır
        Driver.quitDriver();

    }
}
