package tests_userstory_11;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.HomeVendorPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utilities.*;

public class VendorDashboardNavigationTests {
    private HomeVendorPage homeVendorPage;

    @BeforeMethod
    public void setup() {
        Driver.getDriver().get(ConfigReader.getProperty("allowerceUrl"));
        homeVendorPage = new HomeVendorPage();
        LoginVendorUtils.loginAndNavigateToMyAccount(homeVendorPage, ConfigReader.getProperty("vendorUsername"), ConfigReader.getProperty("vendorPassword"));
        WaitUtils.waitForPageToLoad(1);
        BrowserUtils.verifyElementDisplayed(homeVendorPage.myAccountTitle);

    }

    @AfterMethod
    public void tearDown() {
        Driver.quitDriver();
        ExtentReportsListener.extentTestInfo("Driver kapatıldı.");
    }
    @Test(description = "TC-002 Dashboard altinda tum menuler gorulmeli")
    public void testDashboardMenusVisibility() {
        ExtentReportsListener.extentTestInfo("My Account'a başarılı bir şekilde giriş yapıldığı doğrulandı.");
        ExtentReportsListener.addScreenshotToReport("Dashboard altinda menulerin gorunurlugu");
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
    }


    @Test(description = "TC008 - Store Manager menusunun erisilebilirligini dogrulamak")
    public void testStoreManagerMenu() {
        ExtentReportsListener.extentTestInfo("Sayfanin acilmasini bekleyerek storemanager menusunun tiklanabilirligini kontrol ediyoruz");
        BrowserUtils.verifyElementClickable(homeVendorPage.storeManagerMenu);
        JSUtils.JSclickWithTimeout(homeVendorPage.storeManagerMenu);
        ExtentReportsListener.extentTestInfo("Acilan sayfanin basliginin Store Manager olup olmadigini dogruluyoruz");
        BrowserUtils.verifyElementDisplayed(homeVendorPage.storeManagerTitle);


    }
    @Test(description = "TC009 - Orders menüsü doğru şekilde açılmalı")
    public void testOrdersMenu() {
        ExtentReportsListener.extentTestInfo("Sayfanin acilmasini bekleyerek orders menusunun tiklanabilirligini kontrol ediyoruz");
        BrowserUtils.verifyElementClickable(homeVendorPage.ordersMenu);
        ExtentReportsListener.extentTestInfo("Orders menusune tiklayip sayfanin basliginin orders oldugunu dogruluyoruz");
        JSUtils.JSclickWithTimeout(homeVendorPage.ordersMenu);
        BrowserUtils.verifyElementDisplayed(homeVendorPage.ordersTitle);

    }
    @Test(description = "TC010 - Downloads menusunun erisilebilirligini dogrulamak")
    public void testDownloadsMenu() {
        ExtentReportsListener.extentTestInfo("Sayfanin acilmasini bekleyerek downloads menusunun tiklanabilirligini kontrol ediyoruz");
        BrowserUtils.verifyElementClickable(homeVendorPage.downloadsMenu);
        JSUtils.JSclickWithTimeout(homeVendorPage.downloadsMenu);
        ExtentReportsListener.extentTestInfo("Acilan sayfanin basliginin downloads olup olmadigini dogruluyoruz");
        BrowserUtils.verifyElementDisplayed(homeVendorPage.downloadsTitle);
    }

    @Test(description = "TC011- Address menusu dogru sekilde acilmali")
    public void testAddressMenu() {
        ExtentReportsListener.extentTestInfo("Address menusunun tiklanabilir olup olmadigini kontrol ediyoruz");
        BrowserUtils.verifyElementClickable(homeVendorPage.addressesMenu);
        ExtentReportsListener.extentTestInfo("Address menusune tiklayarak dogru sekilde acilip acilmadigini kontrol ederek basligin adress basligi olup olmadigini kontrol ediyoruz");
        JSUtils.JSclickWithTimeout(homeVendorPage.addressesMenu);
        BrowserUtils.verifyElementDisplayed(homeVendorPage.addressesTitle);

    }
    @Test(description = "TC012- Account Details menusu dogru sekilde acilabilmeli ")
    public void testAccountDetailsMenu() {
        ExtentReportsListener.extentTestInfo("Account details menusunun tiklanabilirligini kontrol ediyoruz");
        BrowserUtils.verifyElementClickable(homeVendorPage.accountdetailsMenu);
        ExtentReportsListener.extentTestInfo("Account details menusune tiklayarak dogru sekilde acilip acilmadigini kontrol ediyoruz");
        JSUtils.JSclickWithTimeout(homeVendorPage.accountdetailsMenu);
        BrowserUtils.verifyElementDisplayed(homeVendorPage.accountDetailsTitle);
    }
    @Test(description = "TC013 - Wishlist menüsü doğru şekilde açılmalı")
    public void testWishlistMenu() {
        ExtentReportsListener.extentTestInfo("Wishlist menunun tiklanabilirligini dogruluyoruz ve tikliyoruz");
        BrowserUtils.verifyElementClickable(homeVendorPage.wishlistMenu);
        JSUtils.JSclickWithTimeout(homeVendorPage.wishlistMenu);
        ExtentReportsListener.extentTestInfo("Acilan sayfanin basliginin wishlist olup olmadigini dogruluyoruz");
        BrowserUtils.verifyElementDisplayed(homeVendorPage.wishlistTitle);
    }
    @Test(description = "TC014 - Support Tickets menüsü doğru şekilde açılmalı")
    public void testSupportTicketsMenu() {
        BrowserUtils.verifyElementClickable(homeVendorPage.supportTicketsMenu);
        JSUtils.JSclickWithTimeout(homeVendorPage.supportTicketsMenu);
        ExtentReportsListener.extentTestInfo("Acilan sayfanin url'sinin support-tickets icerdigini dogruluyoruz");
        ExtentReportsListener.extentTestFail("Support tickets sayfasinin goruntusu");
        Assert.assertTrue(Driver.getDriver().getCurrentUrl().contains("support-tickets"));

    }
    @Test(description = "TC015 - Followings menusunun erisilebilirligini dogrulamak")
    public void testFollowingsMenu() {
        ExtentReportsListener.extentTestInfo("Followings menusunun tiklanabilirligini dogruluyoruz ve tikliyoruz");
        BrowserUtils.verifyElementClickable(homeVendorPage.followingsMenu);
        JSUtils.JSclickWithTimeout(homeVendorPage.followingsMenu);
        ExtentReportsListener.extentTestInfo("Acilan sayfanin url'sinin followings icerdigini dogruluyoruz");
        Assert.assertTrue(Driver.getDriver().getCurrentUrl().contains("followings"));
    }

    @Test(description = "TC016 - Oturum sonlandırılmalı ve ana sayfaya yönlenmeli")
    public void testLogoutButton() {
        BrowserUtils.verifyElementClickable(homeVendorPage.logoutButton);
        JSUtils.JSclickWithTimeout(homeVendorPage.logoutButton);
        ExtentReportsListener.extentTestInfo("Logout butonuna bastiktan sonra giris sayfasina yonlendirildigimizi dogruluyoruz ");
        ExtentReportsListener.addScreenshotToReport("Giris/sign in/sign up sayfasinda oldugumuzun goruntusu");
        BrowserUtils.verifyElementDisplayed(homeVendorPage.signInRegisterButton);
    }
}
