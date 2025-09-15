package Tests.US_18;


import Pages.*;
import org.testng.Assert;
import org.testng.annotations.*;
import utilities.*;


public class TC01_PositiveTest {

    @DataProvider()
    public static Object[][] couponsPositiveData() {

        return new Object[][]{
                //positive test

                //After filling in the required fields to create a coupon, the coupon can be created.
                //(Positive Scenario)"
                {"karne05","karnesini alan herkes bu kodla %10 indirim kazanır","10","2026-08-15"}

        };
    }

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

    @Test(dataProvider = "couponsPositiveData")
    public void couponsPositiveTest(String codeName,String descriptionText,String couponAmount,String date) {


        CouponsPage couponsPage = new CouponsPage();
        StoreManagerPage storeManagerPage = new StoreManagerPage();

        // Go to the Coupon page
        ExtentReportsListener.extentTestPass("Coupon sayfasına gidilir");
        storeManagerPage.coupons.click();
        storeManagerPage.addNew.click();

        // Log that all necessary text boxes for creating a coupon are being filled
        ExtentReportsListener.extentTestInfo("Coupon oluşturmak gerekli Text boxlar doldurluyor");
        couponsPage.codeTextBox.sendKeys(codeName);
        couponsPage.descriptionTextBox.sendKeys(descriptionText);
        BrowserUtils.dropdownSelectByVisibleText(couponsPage.discountTypeDD,"Percentage discount");
        couponsPage.couponAmountNumberBox.sendKeys(couponAmount);
        couponsPage.couponExpiryDateBox.sendKeys(date);
        ExtentReportsListener.extentTestPass("Text Boxlara yazı yazılabiliyor");
        ActionsUtils.hoverOver(couponsPage.allowFreeShippingCheckBox);

        // Click on the checkboxes
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

        //Check if the coupon we created is visible on the Coupon page
        Assert.assertTrue(couponsPage.successMessage.isDisplayed());
        ExtentReportsListener.addScreenshotToReport("Onay mesajının görünürlüğü kontrol edilir");

        WaitUtils.waitFor(2);

        ExtentReportsListener.extentTestInfo("Coupon sayfasında oluşturduğumuz coupun gözüküyor mu diye kontrol edilir");
        JSUtils.JSclickWithTimeout(storeManagerPage.coupons);
        storeManagerPage.couponSearchBox.sendKeys(codeName);
        Assert.assertEquals(couponsPage.lastCouponName.getText(),codeName);
    }

    @AfterClass
    public void afterClass() {
        Driver.quitDriver();
    }

}
