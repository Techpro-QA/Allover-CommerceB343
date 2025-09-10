package tests_userstory_11;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.HomeVendorPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.*;

public class VendorAuthenticationTests {
    private HomeVendorPage homeVendorPage;
    @BeforeMethod
    public void setup() {
        Driver.getDriver().get(ConfigReader.getProperty("allowerceUrl"));
        homeVendorPage = new HomeVendorPage();
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
        LoginVendorUtils.loginAndNavigateToMyAccount(homeVendorPage,ConfigReader.getProperty("vendorUsername"),ConfigReader.getProperty("vendorPassword"));
        WaitUtils.waitFor(3);
        ExtentReportsListener.extentTestInfo("My account a giris yapildigi dogrulanir");
        WaitUtils.waitForVisibility(homeVendorPage.myAccountTitle,2);
        ExtentReportsListener.addScreenshotToReport("My account sayfasi goruntusu");
        BrowserUtils.verifyElementDisplayed(homeVendorPage.myAccountTitle);


    }
    @Test(description = "TC003-Hatalı formatta email ile giriş yapılamamalı (Negative Scenario)")
    public void testLogin_Validation_InvalidEmailFormat() {
        ExtentReportsListener.extentTestInfo("Allover Commerce anasayfasına gidilir");
        homeVendorPage.signInRegisterButton.click();
        ExtentReportsListener.extentTestInfo("Username alanina gecersiz formatta email ile  bilgiler girilir");
        homeVendorPage.username.sendKeys(ConfigReader.getProperty("invalidFormatEmail"));
        ExtentReportsListener.extentTestInfo("Gecerli password girilir");
        homeVendorPage.password.sendKeys(ConfigReader.getProperty("vendorPassword"));
        homeVendorPage.signInButton.click();
        ExtentReportsListener.extentTestInfo("Giris butonuna bastiktan sonra basarisiz giris mesajinin yuklenmesini bekliyoruz");
        WaitUtils.waitForVisibility(homeVendorPage.errorMessage,3);
        ExtentReportsListener.addScreenshotToReport("Iyilestirme onerisi: Burada email formatinin hatali oldugu belirtilerek hatanin detaylari yazilabilirdi");
        ExtentReportsListener.extentTestInfo("Hatali veri girisi mesajinin goruntulendigini dogruluyoruz");
        ExtentReportsListener.addScreenshotToReport("Wrong username/password yazisi goruntusu");
        BrowserUtils.verifyExpectedAndActualTextMatch("Wrong username or password.", homeVendorPage.errorMessage);

    }
    @Test(description = "TC004-Hatalı email ile giriş yapılamamalı (Negative Scenario)")
    public void testLogin_Validation_InvalidEmail() {
        ExtentReportsListener.extentTestInfo("Allover Commerce anasayfasına gidilir");
        homeVendorPage.signInRegisterButton.click();
        ExtentReportsListener.extentTestInfo("Username alanina gecersiz email ile  bilgiler girilir");
        homeVendorPage.username.sendKeys(ConfigReader.getProperty("invalidEmail"));
        ExtentReportsListener.extentTestInfo("Gecerli password girilir");
        homeVendorPage.password.sendKeys(ConfigReader.getProperty("vendorPassword"));
        homeVendorPage.signInButton.click();
        WaitUtils.waitFor(3);
        ExtentReportsListener.extentTestInfo("Giris butonuna bastiktan sonra basarisiz giris mesajinin yuklenmesini bekliyoruz");
        WaitUtils.waitForVisibility(homeVendorPage.errorMessage,3);
        ExtentReportsListener.extentTestInfo("Hatali veri girisi mesajinin goruntulendigini dogruluyoruz");
        ExtentReportsListener.addScreenshotToReport("Wrong username mesaj goruntusu");
        BrowserUtils.verifyElementDisplayed(homeVendorPage.errorMessage);

    }
    @Test(description = "TC005-Gecersiz password ile giriş yapılamamalı (Negative Scenario)")
    public void testLogin_Validation_InvalidPassword() {
        ExtentReportsListener.extentTestInfo("Allover Commerce anasayfasına gidilir");
        homeVendorPage.signInRegisterButton.click();
        ExtentReportsListener.extentTestInfo(" Giris yapabilmek icin Sign in butonuna tiklanir");
        ExtentReportsListener.extentTestInfo("Username alanina gecerli/kayitli vendor username  bilgiler girilir");
        homeVendorPage.username.sendKeys(ConfigReader.getProperty("vendorUsername"));
        ExtentReportsListener.extentTestInfo("Gecersiz password girilir");
        homeVendorPage.password.sendKeys(ConfigReader.getProperty("invalidPassword"));
        ExtentReportsListener.addScreenshotToReport("Iyilestirme onerisi:Burada girilen passwordu gorme secenegi olabilirdi");
        homeVendorPage.signInButton.click();
        WaitUtils.waitFor(2);
        ExtentReportsListener.extentTestInfo("Giris butonuna bastiktan sonra basarisiz giris mesajinin yuklenmesini bekliyoruz");
        WaitUtils.waitForText(homeVendorPage.errorMessage,"Wrong username or password.",3);//mesaji gorebilmek icin waitutilse method ekledim
        ExtentReportsListener.addScreenshotToReport("Wrong username/password mesaji goruntusu");
        ExtentReportsListener.extentTestInfo("Hatali veri girisi mesajinin goruntulendigini, expected ve actual mesaji dogruluyoruz");
        BrowserUtils.verifyElementDisplayed(homeVendorPage.errorMessage);
        BrowserUtils.verifyExpectedAndActualTextMatch("Wrong username or password.", homeVendorPage.errorMessage);
    }
    @Test(description = "TC006-Email/username alani bos birakilarak giriş yapılamamalı (Negative Scenario)")
    public void testLogin_Validation_EmptyEmail() {
        ExtentReportsListener.extentTestInfo("Allover Commerce anasayfasına gidilir");
        homeVendorPage.signInRegisterButton.click();
        ExtentReportsListener.extentTestInfo("Sign in tiklanir");
        ExtentReportsListener.extentTestInfo("Username alani bos birakilir");
        JSUtils.JSsetValueBy(homeVendorPage.username, "");
        ExtentReportsListener.extentTestInfo("Gecerli password girilir");
        homeVendorPage.password.sendKeys(ConfigReader.getProperty("vendorPassword"));
        homeVendorPage.signInButton.click();
        ExtentReportsListener.extentTestInfo("Tarayicinin validation mesajini kontrol ediyoruz");
        String validationMessage = JSUtils.JSgetValidationMessage(homeVendorPage.username);
        ExtentReportsListener.extentTestInfo("Validation mesajı: " + validationMessage);
        Assert.assertEquals(validationMessage, "Lütfen bu alanı doldurun.");

    }
    @Test(description = "TC007-Password alani bos birakilarak giriş yapılamamalı (Negative Scenario)")
    public void testLogin_Validation_EmptyPassword() {
        ExtentReportsListener.extentTestInfo(" Giris yapabilmek icin Sign in butonuna tiklanir");
        homeVendorPage.signInRegisterButton.click();
        ExtentReportsListener.extentTestInfo("Gecerli username girilir");
        homeVendorPage.username.sendKeys(ConfigReader.getProperty("vendorPassword"));
        ExtentReportsListener.extentTestInfo("Password alani bos birakilir");
        JSUtils.JSsetValueBy(homeVendorPage.password, "");
        homeVendorPage.signInButton.click();
        ExtentReportsListener.extentTestInfo("Tarayicinin validation mesajini kontrol ediyoruz");
        String validationMessage = JSUtils.JSgetValidationMessage(homeVendorPage.password);
        ExtentReportsListener.extentTestInfo("Validation mesajı: " + validationMessage);
        Assert.assertEquals(validationMessage, "Lütfen bu alanı doldurun.");
        Driver.quitDriver();

    }

}
