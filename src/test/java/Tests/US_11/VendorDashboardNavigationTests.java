package Tests.US_11;

import Pages.HomePage;
import Pages.MyAccountPage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utilities.*;

public class VendorDashboardNavigationTests {
    private HomePage homePage;
    private MyAccountPage myAccountPage;

    @BeforeMethod
    public void setup() {
        Driver.getDriver().get(ConfigReader.getProperty("alloverCommerceUrl"));
        homePage = new HomePage();
        myAccountPage = new MyAccountPage();
        LoginVendorUtils.loginAndNavigateToMyAccount(ConfigReader.getProperty("vendorEmail"), ConfigReader.getProperty("vendorPassword"));
        WaitUtils.waitForPageToLoad(1);
        BrowserUtils.verifyElementDisplayed(myAccountPage.myAccountTitle);

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
        softAssert.assertTrue(myAccountPage.accountdetailsMenu.isDisplayed());
        softAssert.assertTrue(myAccountPage.supportTicketsMenu.isDisplayed());
        softAssert.assertTrue(myAccountPage.followingsMenu.isDisplayed());
        softAssert.assertTrue(myAccountPage.addressesMenu.isDisplayed());
        softAssert.assertTrue(myAccountPage.downloadsMenu.isDisplayed());
        softAssert.assertTrue(myAccountPage.ordersMenu.isDisplayed());
        softAssert.assertTrue(myAccountPage.storeManagerMenu.isDisplayed());
        softAssert.assertTrue(myAccountPage.accountdetailsMenu.isDisplayed());
        softAssert.assertTrue(myAccountPage.logoutButton.isDisplayed());
        softAssert.assertAll();
    }


    @Test(description = "TC008 - Store Manager menusunun erisilebilirligini dogrulamak")
    public void testStoreManagerMenu() {
        ExtentReportsListener.extentTestInfo("Sayfanin acilmasini bekleyerek storemanager menusunun tiklanabilirligini kontrol ediyoruz");
        BrowserUtils.verifyElementClickable(myAccountPage.storeManagerMenu);
        JSUtils.JSclickWithTimeout(myAccountPage.storeManagerMenu);
        ExtentReportsListener.extentTestInfo("Acilan sayfanin basliginin Store Manager olup olmadigini dogruluyoruz");
        BrowserUtils.verifyElementDisplayed(myAccountPage.storeManagerTitle);


    }
    @Test(description = "TC009 - Orders menüsü doğru şekilde açılmalı")
    public void testOrdersMenu() {
        ExtentReportsListener.extentTestInfo("Sayfanin acilmasini bekleyerek orders menusunun tiklanabilirligini kontrol ediyoruz");
        BrowserUtils.verifyElementClickable(myAccountPage.ordersMenu);
        ExtentReportsListener.extentTestInfo("Orders menusune tiklayip sayfanin basliginin orders oldugunu dogruluyoruz");
        JSUtils.JSclickWithTimeout(myAccountPage.ordersMenu);
        BrowserUtils.verifyElementDisplayed(myAccountPage.ordersTitle);

    }
    @Test(description = "TC010 - Downloads menusunun erisilebilirligini dogrulamak")
    public void testDownloadsMenu() {
        ExtentReportsListener.extentTestInfo("Sayfanin acilmasini bekleyerek downloads menusunun tiklanabilirligini kontrol ediyoruz");
        BrowserUtils.verifyElementClickable(myAccountPage.downloadsMenu);
        JSUtils.JSclickWithTimeout(myAccountPage.downloadsMenu);
        ExtentReportsListener.extentTestInfo("Acilan sayfanin basliginin downloads olup olmadigini dogruluyoruz");
        BrowserUtils.verifyElementDisplayed(myAccountPage.downloadsTitle);
    }

    @Test(description = "TC011- Address menusu dogru sekilde acilmali")
    public void testAddressMenu() {
        ExtentReportsListener.extentTestInfo("Address menusunun tiklanabilir olup olmadigini kontrol ediyoruz");
        BrowserUtils.verifyElementClickable(myAccountPage.addressesMenu);
        ExtentReportsListener.extentTestInfo("Address menusune tiklayarak dogru sekilde acilip acilmadigini kontrol ederek basligin adress basligi olup olmadigini kontrol ediyoruz");
        JSUtils.JSclickWithTimeout(myAccountPage.addressesMenu);
        BrowserUtils.verifyElementDisplayed(myAccountPage.addressesTitle);

    }
    @Test(description = "TC012- Account Details menusu dogru sekilde acilabilmeli ")
    public void testAccountDetailsMenu() {
        ExtentReportsListener.extentTestInfo("Account details menusunun tiklanabilirligini kontrol ediyoruz");
        BrowserUtils.verifyElementClickable(myAccountPage.accountdetailsMenu);
        ExtentReportsListener.extentTestInfo("Account details menusune tiklayarak dogru sekilde acilip acilmadigini kontrol ediyoruz");
        JSUtils.JSclickWithTimeout(myAccountPage.accountdetailsMenu);
        BrowserUtils.verifyElementDisplayed(myAccountPage.accountDetailsTitle);
    }
    @Test(description = "TC013 - Wishlist menüsü doğru şekilde açılmalı")
    public void testWishlistMenu() {
        ExtentReportsListener.extentTestInfo("Wishlist menunun tiklanabilirligini dogruluyoruz ve tikliyoruz");
        BrowserUtils.verifyElementClickable(myAccountPage.wishlistMenu);
        JSUtils.JSclickWithTimeout(myAccountPage.wishlistMenu);
        ExtentReportsListener.extentTestInfo("Acilan sayfanin basliginin wishlist olup olmadigini dogruluyoruz");
        BrowserUtils.verifyElementDisplayed(myAccountPage.wishlistTitle);
    }
    @Test(description = "TC014 - Support Tickets menüsü doğru şekilde açılmalı")
    public void testSupportTicketsMenu() {
        BrowserUtils.verifyElementClickable(myAccountPage.supportTicketsMenu);
        JSUtils.JSclickWithTimeout(myAccountPage.supportTicketsMenu);
        ExtentReportsListener.extentTestInfo("Acilan sayfanin url'sinin support-tickets icerdigini dogruluyoruz");
        ExtentReportsListener.extentTestFail("Support tickets sayfasinin goruntusu");
        Assert.assertTrue(Driver.getDriver().getCurrentUrl().contains("support-tickets"));

    }
    @Test(description = "TC015 - Followings menusunun erisilebilirligini dogrulamak")
    public void testFollowingsMenu() {
        ExtentReportsListener.extentTestInfo("Followings menusunun tiklanabilirligini dogruluyoruz ve tikliyoruz");
        BrowserUtils.verifyElementClickable(myAccountPage.followingsMenu);
        JSUtils.JSclickWithTimeout(myAccountPage.followingsMenu);
        ExtentReportsListener.extentTestInfo("Acilan sayfanin url'sinin followings icerdigini dogruluyoruz");
        Assert.assertTrue(Driver.getDriver().getCurrentUrl().contains("followings"));
    }

    @Test(description = "TC016 - Oturum sonlandırılmalı ve ana sayfaya yönlenmeli")
    public void testLogoutButton() {
        BrowserUtils.verifyElementClickable(myAccountPage.logoutButton);
        JSUtils.JSclickWithTimeout(myAccountPage.logoutButton);
        ExtentReportsListener.extentTestInfo("Logout butonuna bastiktan sonra giris sayfasina yonlendirildigimizi dogruluyoruz ");
        ExtentReportsListener.addScreenshotToReport("Giris/sign in/sign up sayfasinda oldugumuzun goruntusu");
        BrowserUtils.verifyElementDisplayed(homePage.homeSignIn);
    }
}
