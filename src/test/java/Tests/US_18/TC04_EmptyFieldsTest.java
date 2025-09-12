package Tests.US_18;


import Pages.Homepage;
import Pages.MyAccount;
import Pages.StoreManager;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.*;

public class TC04_EmptyFieldsTest {


    @BeforeClass
    public void setUp() {
        Driver.getDriver().get(ConfigReader.getProperty("alloverCommerceUrl"));

        Homepage homePage = new Homepage();
        MyAccount myAccount = new MyAccount();

        //Log in as a vendor
        homePage.signInButton.click();
        homePage.userNameOrEmail.sendKeys(ConfigReader.getProperty("vendorEmail"));
        homePage.passwordOnSingIn.sendKeys(ConfigReader.getProperty("vendorPassword"));
        homePage.signInButton.click();

        ExtentReportsListener.extentTestPass("Store Manager sayfasına gidilir");
        homePage.singOutButtonClickable.click();
        myAccount.storeManager.click();

    }

    @Test(dataProvider = "couponsNegativeData")
    public void createCouponWithEmptyFieldsTest() {

        StoreManager storeManager = new StoreManager();
        // Go to the Coupon page
        ExtentReportsListener.extentTestInfo("Coupon sayfasına gidilir");
        storeManager.coupons.click();
        storeManager.addNew.click();

        ActionsUtils.hoverOver(storeManager.allowFreeShippingCheckBox);

        //Check that all text boxes are empty and check boxes are unchecked.
        ExtentReportsListener.addScreenshotToReport("Tüm text Boxların boş olduğu ve check boxların işaretlenmemiş olduğu kontrol edilir");
        WaitUtils.waitFor(1);

        ActionsUtils.scrollEnd();
        // Scroll to the bottom of the page so the driver can see the Submit button
        JSUtils.JSclickWithTimeout(storeManager.couponSubmitButton);

        WaitUtils.waitFor(1);

        //Check if the message "Please insert atleast Coupon Title before submit." is displayed.
        ExtentReportsListener.addScreenshotToReport("Coupon oluşturulamadı mesajının görünürlüğü kontrol edilir");

        Assert.assertTrue(storeManager.couponNameCannotBeLeftBlankMessage.isDisplayed());

    }

    @AfterClass
    public void afterClass() {
        Driver.quitDriver();
    }
}
