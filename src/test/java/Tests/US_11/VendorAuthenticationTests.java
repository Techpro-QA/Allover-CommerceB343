package Tests.US_11;

import pages.HomeVendorPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.*;

public class VendorAuthenticationTests {
    @Test(description = "TC001-Vendor gecerli kimlik bilgileriyle vendor olarak Sign in yapabilmeli ")
    public void testVendorSuccessfulLogin() {
        LoginUtils.loginAndNavigateToMyAccount(ConfigReader.getProperty("vendorUsername"),ConfigReader.getProperty("vendorPassword"));
        HomeVendorPage homeVendorPage=new HomeVendorPage();
        ExtentReportsListener.extentTestInfo("My account a giris yapildigi dogrulanir");
        Assert.assertTrue(homeVendorPage.myAccountTitle.isDisplayed());
        Driver.quitDriver();

    }
    @Test(description = "TC003-Hatalı formatta email ile giriş yapılamamalı (Negative Scenario)")
    public void testLogin_Validation_InvalidEmailFormat() {
        ExtentReportsListener.extentTestInfo("Allover Commerce sayfasina gidilir");
        Driver.getDriver().get(ConfigReader.getProperty("allowerceUrl"));
        HomeVendorPage homeVendorPage = new HomeVendorPage();
        ExtentReportsListener.extentTestInfo(" Giris yapabilmek icin Sign in butonuna tiklanir");
        homeVendorPage.signInRegisterButton.click();
        ExtentReportsListener.extentTestInfo("Username alanina gecersiz formatta email ile  bilgiler girilir");
        homeVendorPage.username.sendKeys(ConfigReader.getProperty("invalidFormatEmail"));
        ExtentReportsListener.extentTestInfo("Gecerli password girilir");
        homeVendorPage.password.sendKeys(ConfigReader.getProperty("vendorPassword"));
        homeVendorPage.signInButton.click();
        ExtentReportsListener.extentTestInfo("Giris butonuna bastiktan sonra basarisiz giris mesajinin yuklenmesini bekliyoruz");
        WaitUtils.waitFor(2);
        WaitUtils.waitForVisibility(homeVendorPage.errorMessage,3);
        ExtentReportsListener.extentTestInfo("Hatali veri girisi mesajinin goruntulendigini dogruluyoruz");
        BrowserUtils.verifyElementDisplayed(homeVendorPage.errorMessage);
        BrowserUtils.verifyExpectedAndActualTextMatch("Wrong username or password.", homeVendorPage.errorMessage);
        Driver.quitDriver();

    }
    @Test(description = "TC004-Hatalı email ile giriş yapılamamalı (Negative Scenario)")
    public void testLogin_Validation_InvalidEmail() {
        ExtentReportsListener.extentTestInfo("Allover Commerce sayfasina gidilir");
        Driver.getDriver().get(ConfigReader.getProperty("allowerceUrl"));
        HomeVendorPage homeVendorPage = new HomeVendorPage();
        ExtentReportsListener.extentTestInfo(" Giris yapabilmek icin Sign in butonuna tiklanir");
        homeVendorPage.signInRegisterButton.click();
        ExtentReportsListener.extentTestInfo("Username alanina gecersiz email ile  bilgiler girilir");
        homeVendorPage.username.sendKeys(ConfigReader.getProperty("invalidEmail"));
        ExtentReportsListener.extentTestInfo("Gecerli password girilir");
        homeVendorPage.password.sendKeys(ConfigReader.getProperty("vendorPassword"));
        homeVendorPage.signInButton.click();
        ExtentReportsListener.extentTestInfo("Giris butonuna bastiktan sonra basarisiz giris mesajinin yuklenmesini bekliyoruz");
        WaitUtils.waitFor(2);
        WaitUtils.waitForVisibility(homeVendorPage.errorMessage,3);
        ExtentReportsListener.extentTestInfo("Hatali veri girisi mesajinin goruntulendigini dogruluyoruz");
        BrowserUtils.verifyElementDisplayed(homeVendorPage.errorMessage);
        BrowserUtils.verifyExpectedAndActualTextMatch("Wrong username or password.", homeVendorPage.errorMessage);
        Driver.quitDriver();

    }
    @Test(description = "TC005-Gecersiz password ile giriş yapılamamalı (Negative Scenario)")
    public void testLogin_Validation_InvalidPassword() {
        ExtentReportsListener.extentTestInfo("Allover Commerce sayfasina gidilir");
        Driver.getDriver().get(ConfigReader.getProperty("allowerceUrl"));
        HomeVendorPage homeVendorPage = new HomeVendorPage();
        ExtentReportsListener.extentTestInfo(" Giris yapabilmek icin Sign in butonuna tiklanir");
        homeVendorPage.signInRegisterButton.click();
        ExtentReportsListener.extentTestInfo("Username alanina gecerli/kayitli vendor username  bilgiler girilir");
        homeVendorPage.username.sendKeys(ConfigReader.getProperty("vendorUsername"));
        ExtentReportsListener.extentTestInfo("Gecersiz password girilir");
        homeVendorPage.password.sendKeys(ConfigReader.getProperty("invalidPassword"));
        homeVendorPage.signInButton.click();
        ExtentReportsListener.extentTestInfo("Giris butonuna bastiktan sonra basarisiz giris mesajinin yuklenmesini bekliyoruz");
        WaitUtils.waitFor(2);
        WaitUtils.waitForVisibility(homeVendorPage.errorMessage,3);
        ExtentReportsListener.extentTestInfo("Hatali veri girisi mesajinin goruntulendigini, expected ve actual mesaji dogruluyoruz");
        BrowserUtils.verifyElementDisplayed(homeVendorPage.errorMessage);
        BrowserUtils.verifyExpectedAndActualTextMatch("Wrong username or password.", homeVendorPage.errorMessage);
        Driver.quitDriver();

    }
    @Test(description = "TC006-Email/username alani bos birakilarak giriş yapılamamalı (Negative Scenario)")
    public void testLogin_Validation_EmptyEmail() {
        ExtentReportsListener.extentTestInfo("Allover Commerce sayfasina gidilir");
        Driver.getDriver().get(ConfigReader.getProperty("allowerceUrl"));
        HomeVendorPage homeVendorPage = new HomeVendorPage();
        ExtentReportsListener.extentTestInfo(" Giris yapabilmek icin Sign in butonuna tiklanir");
        homeVendorPage.signInRegisterButton.click();
        ExtentReportsListener.extentTestInfo("Username alani bos birakilir");
        JSUtils.JSsetValueBy(homeVendorPage.username, "");
        ExtentReportsListener.extentTestInfo("Gecerli password girilir");
        homeVendorPage.password.sendKeys(ConfigReader.getProperty("vendorPassword"));
        homeVendorPage.signInButton.click();
        ExtentReportsListener.extentTestInfo("Tarayicinin validation mesajini kontrol ediyoruz");
        String validationMessage = JSUtils.JSgetValidationMessage(homeVendorPage.username);
        ExtentReportsListener.extentTestInfo("Validation mesajı: " + validationMessage);
        Assert.assertEquals(validationMessage, "Lütfen bu alanı doldurun.");
        Driver.quitDriver();

    }
    @Test(description = "TC007-Password alani bos birakilarak giriş yapılamamalı (Negative Scenario)")
    public void testLogin_Validation_EmptyPassword() {
        ExtentReportsListener.extentTestInfo("Allover Commerce sayfasina gidilir");
        Driver.getDriver().get(ConfigReader.getProperty("allowerceUrl"));
        HomeVendorPage homeVendorPage = new HomeVendorPage();
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
