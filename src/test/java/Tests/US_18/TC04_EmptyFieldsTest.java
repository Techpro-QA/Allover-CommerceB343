package Tests.US_18;


import Pages.*;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.*;

public class TC04_EmptyFieldsTest{


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

    @Test(dataProvider = "couponsNegativeData")
    public void createCouponWithEmptyFieldsTest() {

        CouponsPage couponsPage = new CouponsPage();
        StoreManagerPage storeManagerPage = new StoreManagerPage();

        // Go to the Coupon page
        ExtentReportsListener.extentTestInfo("Coupon sayfasına gidilir");
        storeManagerPage.coupons.click();
        storeManagerPage.addNew.click();

        ActionsUtils.hoverOver(couponsPage.allowFreeShippingCheckBox);

        //Check that all text boxes are empty and check boxes are unchecked.
        ExtentReportsListener.addScreenshotToReport("Tüm text Boxların boş olduğu ve check boxların işaretlenmemiş olduğu kontrol edilir");
        WaitUtils.waitFor(1);

        ActionsUtils.scrollEnd();
        // Scroll to the bottom of the page so the driver can see the Submit button
        JSUtils.JSclickWithTimeout(couponsPage.couponSubmitButton);

        WaitUtils.waitFor(1);

        //Check if the message "Please insert atleast Coupon Title before submit." is displayed.
        ExtentReportsListener.addScreenshotToReport("Coupon oluşturulamadı mesajının görünürlüğü kontrol edilir");

        Assert.assertTrue(couponsPage.couponNameCannotBeLeftBlankMessage.isDisplayed());

    }

    @AfterClass
    public void afterClass() {
        Driver.quitDriver();
    }
}
