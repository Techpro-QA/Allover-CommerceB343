package Tests.US_11;

import pages.HomeVendorPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utilities.*;

public class VendorDashboardNavigationTests {
    @Test(description = "TC-002 Dashboard altinda tum menuler gorulmeli")
    public void testDashboardMenusVisibility() {
        ExtentReportsListener.extentTestInfo("Olusturdugumuz loginmethod ile vendor girisini yapip dogruluyoruz");
        LoginUtils.loginAndNavigateToMyAccount(ConfigReader.getProperty("vendorUsername"),ConfigReader.getProperty("vendorPassword"));
        HomeVendorPage homeVendorPage=new HomeVendorPage();
        BrowserUtils.verifyElementDisplayed(homeVendorPage.textMyAccount);
        ExtentReportsListener.extentTestInfo("Herbir menunun gorunurlugu digerini etkilemeyecegi icin softassert kullanarak dogrulamamizi yapiyoruz");
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(homeVendorPage.accountdetailsMenu.isDisplayed());
        softAssert.assertTrue(homeVendorPage.supportTicketsMenu.isDisplayed());
        softAssert.assertTrue(homeVendorPage.followingsMenu.isDisplayed());
        softAssert.assertTrue(homeVendorPage.addressesMenu.isDisplayed());
        softAssert.assertTrue(homeVendorPage.downloadsMenu.isDisplayed());
        softAssert.assertTrue(homeVendorPage.ordersMenu.isDisplayed());
        softAssert.assertTrue(homeVendorPage.storeManagerMenu.isDisplayed());
        softAssert.assertTrue(homeVendorPage.accountdetailsMenu.isDisplayed());
        softAssert.assertTrue(homeVendorPage.logoutButton.isDisplayed());
        softAssert.assertAll();
        Driver.quitDriver();

    }


    @Test(description = "TC008 - Store Manager menusunun erisilebilirligini dogrulamak")
    public void testStoreManagerMenu() {
        LoginUtils.loginAndNavigateToMyAccount(ConfigReader.getProperty("vendorUsername"),ConfigReader.getProperty("vendorPassword"));
        ExtentReportsListener.extentTestInfo("Olusturdugumuz loginmethod ile vendor girisini yapip dogruluyoruz");
        WaitUtils.waitForPageToLoad(1);
        HomeVendorPage homeVendorPage=new HomeVendorPage();
        BrowserUtils.verifyElementClickable(homeVendorPage.storeManagerMenu);
        JSUtils.JSclickWithTimeout(homeVendorPage.storeManagerMenu);
        ExtentReportsListener.extentTestInfo("Acilan sayfanin basliginin Store Manager olup olmadigini dogruluyoruz");
        BrowserUtils.verifyElementDisplayed(homeVendorPage.storeManagerTitle);
        Driver.quitDriver();

    }
    @Test(description = "TC009 - Orders menüsü doğru şekilde açılmalı")
    public void testOrdersMenu() {
        LoginUtils.loginAndNavigateToMyAccount(ConfigReader.getProperty("vendorUsername"),ConfigReader.getProperty("vendorPassword"));
        ExtentReportsListener.extentTestInfo("Olusturdugumuz loginmethod ile vendor girisini yapip dogruluyoruz");
        ExtentReportsListener.extentTestInfo("Sayfanin acilmasini bekleyerek orders menusunun tiklanabilirligini kontrol ediyoruz");
        WaitUtils.waitForPageToLoad(1);
        HomeVendorPage homeVendorPage=new HomeVendorPage();
        BrowserUtils.verifyElementClickable(homeVendorPage.ordersMenu);
        ExtentReportsListener.extentTestInfo("Orders menusune tiklayip sayfanin basliginin orders oldugunu dogruluyoruz");
        JSUtils.JSclickWithTimeout(homeVendorPage.ordersMenu);
        BrowserUtils.verifyElementDisplayed(homeVendorPage.ordersTitle);
        Driver.quitDriver();

    }
    @Test(description = "TC010 - Downloads menusunun erisilebilirligini dogrulamak")
    public void testDownloadsMenu() {
        LoginUtils.loginAndNavigateToMyAccount(ConfigReader.getProperty("vendorUsername"),ConfigReader.getProperty("vendorPassword"));
        ExtentReportsListener.extentTestInfo("Olusturdugumuz loginmethod ile vendor girisini yapip dogruluyoruz");
        WaitUtils.waitForPageToLoad(1);
        HomeVendorPage homeVendorPage=new HomeVendorPage();
        BrowserUtils.verifyElementClickable(homeVendorPage.downloadsMenu);
        JSUtils.JSclickWithTimeout(homeVendorPage.downloadsMenu);
        ExtentReportsListener.extentTestInfo("Acilan sayfanin basliginin downloads olup olmadigini dogruluyoruz");
        BrowserUtils.verifyElementDisplayed(homeVendorPage.downloadsTitle);
        Driver.quitDriver();

    }

    @Test(description = "TC011- Address menusu dogru sekilde acilmali")
    public void testAddressMenu() {
        LoginUtils.loginAndNavigateToMyAccount(ConfigReader.getProperty("vendorUsername"),ConfigReader.getProperty("vendorPassword"));
        ExtentReportsListener.extentTestInfo("Olusturdugumuz loginmethod ile vendor girisini yapip dogruluyoruz");
        WaitUtils.waitForPageToLoad(1);
        HomeVendorPage homeVendorPage=new HomeVendorPage();
        ExtentReportsListener.extentTestInfo("Address menusunun tiklanabilir olup olmadigini kontrol ediyoruz");
        BrowserUtils.verifyElementClickable(homeVendorPage.addressesMenu);
        ExtentReportsListener.extentTestInfo("Address menusune tiklayarak dogru sekilde acilip acilmadigini kontrol ederek basligin adress basligi olup olmadigini kontrol ediyoruz");
        JSUtils.JSclickWithTimeout(homeVendorPage.addressesMenu);
        BrowserUtils.verifyElementDisplayed(homeVendorPage.addressesTitle);
        Driver.quitDriver();


    }
    @Test(description = "TC012- Account Details menusu dogru sekilde acilabilmeli ")
    public void testAccountDetailsMenu() {
        ExtentReportsListener.extentTestInfo("Olusturdugumuz loginmethod ile vendor girisini yapip dogruluyoruz");
        LoginUtils.loginAndNavigateToMyAccount(ConfigReader.getProperty("vendorUsername"),ConfigReader.getProperty("vendorPassword"));
        WaitUtils.waitForPageToLoad(1);
        HomeVendorPage homeVendorPage=new HomeVendorPage();
        ExtentReportsListener.extentTestInfo("Account details menusunun tiklanabilirligini kontrol ediyoruz");
        BrowserUtils.verifyElementClickable(homeVendorPage.accountdetailsMenu);
        ExtentReportsListener.extentTestInfo("Account details menusune tiklayarak dogru sekilde acilip acilmadigini kontrol ediyoruz");
        JSUtils.JSclickWithTimeout(homeVendorPage.accountdetailsMenu);
        BrowserUtils.verifyElementDisplayed(homeVendorPage.accountDetailsTitle);
        Driver.quitDriver();

    }
    @Test(description = "TC013 - Wishlist menüsü doğru şekilde açılmalı")
    public void testWishlistMenu() {
        LoginUtils.loginAndNavigateToMyAccount(ConfigReader.getProperty("vendorUsername"),ConfigReader.getProperty("vendorPassword"));
        ExtentReportsListener.extentTestInfo("Olusturdugumuz loginmethod ile vendor girisini yapip dogruluyoruz");
        WaitUtils.waitForPageToLoad(1);
        HomeVendorPage homeVendorPage=new HomeVendorPage();
        ExtentReportsListener.extentTestInfo("Wishlist menunun tiklanabilirligini dogruluyoruz ve tikliyoruz");
        BrowserUtils.verifyElementClickable(homeVendorPage.wishlistMenu);
        JSUtils.JSclickWithTimeout(homeVendorPage.wishlistMenu);
        ExtentReportsListener.extentTestInfo("Acilan sayfanin basliginin wishlist olup olmadigini dogruluyoruz");
        BrowserUtils.verifyElementDisplayed(homeVendorPage.wishlistTitle);
        Driver.quitDriver();

    }
    @Test(description = "TC014 - Support Tickets menüsü doğru şekilde açılmalı")
    public void testSupportTicketsMenu() {
        ExtentReportsListener.extentTestInfo("Olusturdugumuz loginmethod ile vendor girisini yapip dogruluyoruz");
        LoginUtils.loginAndNavigateToMyAccount(ConfigReader.getProperty("vendorUsername"),ConfigReader.getProperty("vendorPassword"));
        WaitUtils.waitForPageToLoad(1);
        HomeVendorPage homeVendorPage=new HomeVendorPage();
        BrowserUtils.verifyElementClickable(homeVendorPage.supportTicketsMenu);
        JSUtils.JSclickWithTimeout(homeVendorPage.supportTicketsMenu);
        ExtentReportsListener.extentTestInfo("Acilan sayfanin url'sinin support-tickets icerdigini dogruluyoruz");
        Assert.assertTrue(Driver.getDriver().getCurrentUrl().contains("support-tickets"));
        Driver.quitDriver();
    }
    @Test(description = "TC015 - Followings menusunun erisilebilirligini dogrulamak")
    public void testFollowingsMenu() {
        ExtentReportsListener.extentTestInfo("Olusturdugumuz loginmethod ile vendor girisini yapip dogruluyoruz");
        LoginUtils.loginAndNavigateToMyAccount(ConfigReader.getProperty("vendorUsername"),ConfigReader.getProperty("vendorPassword"));
        WaitUtils.waitForPageToLoad(1);
        HomeVendorPage homeVendorPage=new HomeVendorPage();
        ExtentReportsListener.extentTestInfo("Followings menusunun tiklanabilirligini dogruluyoruz ve tikliyoruz");
        BrowserUtils.verifyElementClickable(homeVendorPage.followingsMenu);
        JSUtils.JSclickWithTimeout(homeVendorPage.followingsMenu);
        ExtentReportsListener.extentTestInfo("Acilan sayfanin url'sinin followings icerdigini dogruluyoruz");
        Assert.assertTrue(Driver.getDriver().getCurrentUrl().contains("followings"));
        Driver.quitDriver();
    }

    @Test(description = "TC016 - Oturum sonlandırılmalı ve ana sayfaya yönlenmeli")
    public void testLogoutButton() {
        ExtentReportsListener.extentTestInfo("Olusturdugumuz loginmethod ile vendor girisini yapip dogruluyoruz");
        LoginUtils.loginAndNavigateToMyAccount(ConfigReader.getProperty("vendorUsername"),ConfigReader.getProperty("vendorPassword"));
        ExtentReportsListener.extentTestInfo("Sayfanin yuklenmesini bekleyerek tiklanabilirligini kontrol ediyoruz");
        WaitUtils.waitForPageToLoad(1);
        HomeVendorPage homeVendorPage=new HomeVendorPage();
        BrowserUtils.verifyElementClickable(homeVendorPage.logoutButton);
        JSUtils.JSclickWithTimeout(homeVendorPage.logoutButton);
        ExtentReportsListener.extentTestInfo("Logout butonuna bastiktan sonra giris sayfasina yonlendirildigimizi dogruluyoruz ");
        BrowserUtils.verifyElementDisplayed(homeVendorPage.signInRegisterButton);
        Driver.quitDriver();
    }
}
