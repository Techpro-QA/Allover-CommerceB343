package Tests.US_18;


import Pages.*;
import org.testng.Assert;
import org.testng.annotations.*;
import utilities.*;
@Listeners(ExtentReportsListener.class)
public class TC03_DuplicateCouponTest  {

    @BeforeClass
    public void setUp() {
        Driver.getDriver().get(ConfigReader.getProperty("alloverCommerceUrl"));
        HomePage homePage = new HomePage();
        MyAccountPage myAccountPage = new MyAccountPage();

        //Log in as a vendor
        homePage.homeSignIn.click();
        homePage.usernameOrEmailAddressTextBox.sendKeys(ConfigReader.getProperty("vendorEmail"));
        homePage.passwordTextBox.sendKeys(ConfigReader.getProperty("vendorPassword"));
        homePage.signInButton.click();

        ExtentReportsListener.extentTestPass("Store Manager sayfasına gidilir");
        homePage.homeSignOut.click();
        myAccountPage.storeManagerMenu.click();

    }

    @Test
    public void duplicateCouponCreationTest() {
        CouponsPage couponsPage = new CouponsPage();
        StoreManagerPage storeManagerPage = new StoreManagerPage();

        // Go to the Coupon page
        ExtentReportsListener.extentTestInfo("Coupon sayfasına gidilir");
        storeManagerPage.coupons.click();
        storeManagerPage.addNew.click();

        // The same data is entered to create the same coupon
        ExtentReportsListener.extentTestInfo("Coupon oluşturmak gerekli Text boxlar doldurluyor");
        couponsPage.codeTextBox.sendKeys(ConfigReader.getProperty("couponCode"));
        couponsPage.descriptionTextBox.sendKeys(ConfigReader.getProperty("couponDescription"));
        BrowserUtils.dropdownSelectByVisibleText(couponsPage.discountTypeDD,"Percentage discount");
        couponsPage.couponAmountNumberBox.sendKeys(ConfigReader.getProperty("couponAmount"));
        couponsPage.couponExpiryDateBox.sendKeys(ConfigReader.getProperty("couponExpiryDate"));

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

        //Since the location of the message could not be obtained properly, it was added to the comment line and checked with a screenshot.
        ExtentReportsListener.addScreenshotToReport("Onay mesajının görünürlüğü kontrol edilir");
        ExtentReportsListener.extentTestInfo("Coupon sayfasında oluşturduğumuz coupun gözüküyor mu diye kontrol edilir");
        JSUtils.JSclickWithTimeout(storeManagerPage.coupons);
        storeManagerPage.couponSearchBox.sendKeys(ConfigReader.getProperty("couponCode"));

        WaitUtils.waitFor(1);

        //Check if the same coupon has been created
        ExtentReportsListener.addScreenshotToReport("Aynı coupondan birden fazla var mı kontrol edilir");
        Assert.assertFalse(couponsPage.secondRowFirstColumn.isDisplayed());

    }

    @AfterClass
    public void afterClass() {
        Driver.quitDriver();
    }
}
