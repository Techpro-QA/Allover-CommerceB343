package Tests.US_18;


import Pages.*;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import utilities.*;


public class TC02_NegativeTests extends DataProviders {


    @BeforeClass
    public void setUp() {
        Driver.getDriver().get(ConfigReader.getProperty("alloverCommerceUrl"));
        HomePage homePage = new HomePage();
        MyAccountPage myAccountPage = new MyAccountPage();

        //Log in as a vendor
        homePage.signInButton.click();
        homePage.usernameOrEmailAddressTextBox.sendKeys(ConfigReader.getProperty("vendorEmail"));
        homePage.homeSignIn.sendKeys(ConfigReader.getProperty("vendorPassword"));
        homePage.signInButton.click();

        ExtentReportsListener.extentTestPass("Store Manager sayfasına gidilir");
        homePage.homeSignOut.click();
        myAccountPage.storeManagerMenu.click();
    }

    @Test(dataProvider = "couponsNegativeData")
    public void couponsNegativeTest(String codeName,String descriptionText,String couponAmount,String date) {

        CouponsPage couponsPage = new CouponsPage();
        StoreManagerPage storeManagerPage = new StoreManagerPage();
        SoftAssert softAssert =new SoftAssert();

        // Go to the Coupon page
        ExtentReportsListener.extentTestInfo("Coupon sayfasına gidilir");
        storeManagerPage.coupons.click();
        storeManagerPage.addNew.click();

        //All text boxes required to create a coupon are filled with incorrect data
        ExtentReportsListener.extentTestInfo("Coupon oluşturmak gerekli Text boxlar doldurluyor");
        couponsPage.codeTextBox.sendKeys(codeName);
        couponsPage.descriptionTextBox.sendKeys(descriptionText);
        BrowserUtils.dropdownSelectByVisibleText(couponsPage.discountTypeDD,"Percentage discount");
        couponsPage.couponAmountNumberBox.sendKeys(couponAmount);
        couponsPage.couponExpiryDateBox.sendKeys(date);

        ExtentReportsListener.extentTestPass("Text Boxlara yazı yazılabiliyor");

        ActionsUtils.hoverOver(couponsPage.allowFreeShippingCheckBox);

        if (!couponsPage.allowFreeShippingCheckBox.isSelected()) {
            couponsPage.allowFreeShippingCheckBox.click();
        }

        ActionsUtils.hoverOver(couponsPage.showOnStoreCheckBox);

        if (!couponsPage.showOnStoreCheckBox.isSelected()) {
            couponsPage.showOnStoreCheckBox.click();
        }
        ExtentReportsListener.addScreenshotToReport("Tüm text Boxların dolu olduğu ve check boxların işaretlenmiş olduğu kontrol edilir");
        WaitUtils.waitFor(1);

        // Scroll to the bottom of the page so the driver can see the Submit button
        ActionsUtils.scrollEnd();
        JSUtils.JSclickWithTimeout(couponsPage.couponSubmitButton);
        WaitUtils.waitFor(1);

        ExtentReportsListener.addScreenshotToReport("Coupon oluşturulamadı mesajının görünürlüğü kontrol edilir");
        WaitUtils.waitFor(2);
        //Check if the coupon we created is visible on the Coupon page
        ExtentReportsListener.extentTestInfo("Coupon sayfasında oluşturduğumuz coupunun gözülmediği kontrol edilir");
        JSUtils.JSclickWithTimeout(storeManagerPage.coupons);
        storeManagerPage.couponSearchBox.sendKeys(codeName);
        WaitUtils.waitFor(2);

        //I used soft assert because I knew that the tests would fail from manual testing.

        //Check if coupons can be created
        ExtentReportsListener.addScreenshotToReport("Oluşturulan kupon("+ codeName + ") ekranda görülememeli");
        softAssert.assertFalse(couponsPage.lastCouponName.isDisplayed());
        softAssert.assertAll();
    }

    @AfterClass
    public void afterClass() {
        Driver.quitDriver();
    }
}
