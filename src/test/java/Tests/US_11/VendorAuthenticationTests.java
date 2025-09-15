package Tests.US_11;

import Pages.HomePage;
import Pages.MyAccountPage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.*;

public class VendorAuthenticationTests {
    private HomePage homePage;
    private MyAccountPage myAccountPage;
    @BeforeMethod
    public void setup() {
        Driver.getDriver().get(ConfigReader.getProperty("alloverCommerceUrl"));
        homePage = new HomePage();
        myAccountPage = new MyAccountPage();
    }

    @AfterMethod
    public void tearDown() {
        Driver.quitDriver();
        ExtentReportsListener.extentTestInfo("Driver kapatıldı.");
    }
    @Test(description = "TC001-Vendor gecerli kimlik bilgileriyle vendor olarak Sign in yapabilmeli ")
    public void testVendorSuccessfulLogin() {
        ExtentReportsListener.extentTestInfo("Allover Commerce anasayfasına gidilir");
        ExtentReportsListener.extentTestInfo("Vendor olarak giriş yapılıyor...");
        LoginVendorUtils.loginAndNavigateToMyAccount(ConfigReader.getProperty("vendorEmail"),ConfigReader.getProperty("vendorPassword"));
        WaitUtils.waitFor(3);
        ExtentReportsListener.extentTestInfo("My account a giris yapildigi dogrulanir");
        WaitUtils.waitForVisibility(myAccountPage.myAccountTitle,2);
        ExtentReportsListener.addScreenshotToReport("My account sayfasi goruntusu");
        BrowserUtils.verifyElementDisplayed(myAccountPage.myAccountTitle);


    }
    @Test(description = "TC003-Hatalı formatta email ile giriş yapılamamalı (Negative Scenario)")
    public void testLogin_Validation_InvalidEmailFormat() {
        ExtentReportsListener.extentTestInfo("Allover Commerce anasayfasına gidilir");
        homePage.homeSignIn.click();
        ExtentReportsListener.extentTestInfo("Username alanina gecersiz formatta email ile  bilgiler girilir");
        homePage.usernameOrEmailAddressTextBox.sendKeys(ConfigReader.getProperty("invalidFormatEmail"));
        ExtentReportsListener.extentTestInfo("Gecerli password girilir");
        homePage.passwordTextBox.sendKeys(ConfigReader.getProperty("vendorPassword"));
        homePage.signInButton.click();
        WaitUtils.waitFor(2);
        ExtentReportsListener.extentTestInfo("Giris butonuna bastiktan sonra basarisiz giris mesajinin yuklenmesini bekliyoruz");
        WaitUtils.waitForVisibility(myAccountPage.errorMessage,3);
        ExtentReportsListener.addScreenshotToReport("Iyilestirme onerisi: Burada email formatinin hatali oldugu belirtilerek hatanin detaylari yazilabilirdi");
        ExtentReportsListener.extentTestInfo("Hatali veri girisi mesajinin goruntulendigini dogruluyoruz");
        ExtentReportsListener.addScreenshotToReport("Wrong username/password yazisi goruntusu");
        BrowserUtils.verifyExpectedAndActualTextMatch("Wrong username or password.", myAccountPage.errorMessage);

    }
    @Test(description = "TC004-Hatalı email ile giriş yapılamamalı (Negative Scenario)")
    public void testLogin_Validation_InvalidEmail() {
        ExtentReportsListener.extentTestInfo("Allover Commerce anasayfasına gidilir");
        homePage.homeSignIn.click();
        ExtentReportsListener.extentTestInfo("Username alanina gecersiz email ile  bilgiler girilir");
        homePage.usernameOrEmailAddressTextBox.sendKeys(ConfigReader.getProperty("invalidEmail"));
        ExtentReportsListener.extentTestInfo("Gecerli password girilir");
        homePage.passwordTextBox.sendKeys(ConfigReader.getProperty("vendorPassword"));
        homePage.signInButton.click();
        WaitUtils.waitFor(3);
        ExtentReportsListener.extentTestInfo("Giris butonuna bastiktan sonra basarisiz giris mesajinin yuklenmesini bekliyoruz");
        WaitUtils.waitForVisibility(myAccountPage.errorMessage,3);
        ExtentReportsListener.extentTestInfo("Hatali veri girisi mesajinin goruntulendigini dogruluyoruz");
        ExtentReportsListener.addScreenshotToReport("Wrong username mesaj goruntusu");
        BrowserUtils.verifyElementDisplayed(myAccountPage.errorMessage);

    }
    @Test(description = "TC005-Gecersiz password ile giriş yapılamamalı (Negative Scenario)")
    public void testLogin_Validation_InvalidPassword() {
        ExtentReportsListener.extentTestInfo("Allover Commerce anasayfasına gidilir");
        homePage.homeSignIn.click();
        ExtentReportsListener.extentTestInfo(" Giris yapabilmek icin Sign in butonuna tiklanir");
        ExtentReportsListener.extentTestInfo("Username alanina gecerli/kayitli vendor username  bilgiler girilir");
        homePage.usernameOrEmailAddressTextBox.sendKeys(ConfigReader.getProperty("vendorEmail"));
        ExtentReportsListener.extentTestInfo("Gecersiz password girilir");
        homePage.passwordTextBox.sendKeys(ConfigReader.getProperty("invalidPassword"));
        ExtentReportsListener.addScreenshotToReport("Iyilestirme onerisi:Burada girilen passwordu gorme secenegi olabilirdi");
        homePage.signInButton.click();
        WaitUtils.waitFor(2);
        ExtentReportsListener.extentTestInfo("Giris butonuna bastiktan sonra basarisiz giris mesajinin yuklenmesini bekliyoruz");
        WaitUtils.waitForText(myAccountPage.errorMessage,"Wrong username or password.",3);//mesaji gorebilmek icin waitutilse method ekledim
        ExtentReportsListener.addScreenshotToReport("Wrong username/password mesaji goruntusu");
        ExtentReportsListener.extentTestInfo("Hatali veri girisi mesajinin goruntulendigini, expected ve actual mesaji dogruluyoruz");
        BrowserUtils.verifyElementDisplayed(myAccountPage.errorMessage);
        BrowserUtils.verifyExpectedAndActualTextMatch("Wrong username or password.", myAccountPage.errorMessage);
    }
    @Test(description = "TC006-Email/username alani bos birakilarak giriş yapılamamalı (Negative Scenario)")
    public void testLogin_Validation_EmptyEmail() {
        ExtentReportsListener.extentTestInfo("Allover Commerce anasayfasına gidilir");
        homePage.homeSignIn.click();
        ExtentReportsListener.extentTestInfo("Sign in tiklanir");
        ExtentReportsListener.extentTestInfo("Username alani bos birakilir");
        JSUtils.JSsetValueBy(homePage.usernameOrEmailAddressTextBox, "");
        ExtentReportsListener.extentTestInfo("Gecerli password girilir");
        homePage.passwordTextBox.sendKeys(ConfigReader.getProperty("vendorPassword"));
        homePage.signInButton.click();
        ExtentReportsListener.extentTestInfo("Tarayicinin validation mesajini kontrol ediyoruz");
        String validationMessage = JSUtils.JSgetValidationMessage(homePage.usernameOrEmailAddressTextBox);
        ExtentReportsListener.extentTestInfo("Validation mesajı: " + validationMessage);
        Assert.assertEquals(validationMessage, "Lütfen bu alanı doldurun.");

    }
    @Test(description = "TC007-Password alani bos birakilarak giriş yapılamamalı (Negative Scenario)")
    public void testLogin_Validation_EmptyPassword() {
        ExtentReportsListener.extentTestInfo(" Giris yapabilmek icin Sign in butonuna tiklanir");
        homePage.homeSignIn.click();
        ExtentReportsListener.extentTestInfo("Gecerli username girilir");
        homePage.usernameOrEmailAddressTextBox.sendKeys(ConfigReader.getProperty("vendorPassword"));
        ExtentReportsListener.extentTestInfo("Password alani bos birakilir");
        JSUtils.JSsetValueBy(homePage.passwordTextBox, "");
        homePage.signInButton.click();
        ExtentReportsListener.extentTestInfo("Tarayicinin validation mesajini kontrol ediyoruz");
        String validationMessage = JSUtils.JSgetValidationMessage(homePage.passwordTextBox);
        ExtentReportsListener.extentTestInfo("Validation mesajı: " + validationMessage);
        Assert.assertEquals(validationMessage, "Lütfen bu alanı doldurun.");
        Driver.quitDriver();

    }

}
