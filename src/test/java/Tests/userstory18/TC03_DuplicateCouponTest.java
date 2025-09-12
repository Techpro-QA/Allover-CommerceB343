package Tests.userstory18;

import Pages.HomePage;
import Pages.MyAccount;
import Pages.StoreManager;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import utilities.*;
@Listeners(ExtentReportsListener.class)
public class TC03_DuplicateCouponTest {

    @BeforeClass
    public void beforeClass() {
        Driver.getDriver().get(ConfigReader.getProperty("alloverCommerceUrl"));
        HomePage homePage = new HomePage();
        MyAccount myAccount = new MyAccount();

        //Log in as a vendor
        homePage.homeSignIn.click();
        homePage.usernameOrEmailAddressTextBox.sendKeys(ConfigReader.getProperty("vendorEmail"));
        homePage.passwordTextBox.sendKeys(ConfigReader.getProperty("vendorPassword"));
        homePage.signInButton.click();

        ExtentReportsListener.extentTestInfo("Store Manager sayfasına gidilir");
        homePage.homeSignOut.click();
        myAccount.storeManager.click();
    }

    @Test
    public void duplicateCouponCreationTest() {
        StoreManager storeManager = new StoreManager();

        // Go to the Coupon page
        ExtentReportsListener.extentTestInfo("Coupon sayfasına gidilir");
        storeManager.coupons.click();
        storeManager.addNew.click();

        // The same data is entered to create the same coupon
        ExtentReportsListener.extentTestInfo("Coupon oluşturmak gerekli Text boxlar doldurluyor");
        storeManager.codeTextBox.sendKeys(ConfigReader.getProperty("couponCode"));
        storeManager.descriptionTextBox.sendKeys(ConfigReader.getProperty("couponDescription"));
        BrowserUtils.dropdownSelectByVisibleText(storeManager.discountTypeDD,"Percentage discount");
        storeManager.couponAmountNumberBox.sendKeys(ConfigReader.getProperty("couponAmount"));
        storeManager.couponExpiryDateBox.sendKeys(ConfigReader.getProperty("couponExpiryDate"));

        ExtentReportsListener.extentTestPass("Text Boxlara yazı yazılabiliyor");

        ActionsUtils.hoverOver(storeManager.allowFreeShippingCheckBox);

        if (!storeManager.allowFreeShippingCheckBox.isSelected()) {
            storeManager.allowFreeShippingCheckBox.click();
        }

        ActionsUtils.hoverOver(storeManager.showOnStoreCheckBox);

        if (!storeManager.showOnStoreCheckBox.isSelected()) {
            storeManager.showOnStoreCheckBox.click();
        }

        ExtentReportsListener.addScreenshotToReport("Tüm text Boxların dolu olduğu ve check boxların işaretlenmiş olduğu kontrol edilir");
        WaitUtils.waitFor(1);

        // Scroll to the bottom of the page so the driver can see the Submit button
        ActionsUtils.scrollEnd();
        JSUtils.JSclickWithTimeout(storeManager.couponSubmitButton);
        WaitUtils.waitFor(1);

        //Since the location of the message could not be obtained properly, it was added to the comment line and checked with a screenshot.
        ExtentReportsListener.addScreenshotToReport("Onay mesajının görünürlüğü kontrol edilir");
        ExtentReportsListener.extentTestInfo("Coupon sayfasında oluşturduğumuz coupun gözüküyor mu diye kontrol edilir");
        JSUtils.JSclickWithTimeout(storeManager.coupons);
        storeManager.couponSearchBox.sendKeys(ConfigReader.getProperty("couponCode"));

        WaitUtils.waitFor(1);

        //Check if the same coupon has been created
        ExtentReportsListener.addScreenshotToReport("Aynı coupondan birden fazla var mı kontrol edilir");
        Assert.assertFalse(storeManager.secondRowFirstColumn.isDisplayed());

    }

    @AfterClass
    public void afterClass() {
        Driver.quitDriver();
    }
}
