package Tests.US_18;

import Pages.HomePage;
import Pages.MyAccount;
import Pages.StoreManager;
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
        MyAccount myAccount = new MyAccount();

        //Log in as a vendor
        homePage.homeSignIn.click();
        homePage.usernameOrEmailAddressTextBox.sendKeys(ConfigReader.getProperty("vendorEmail"));
        homePage.passwordTextBox.sendKeys(ConfigReader.getProperty("vendorPassword"));
        homePage.signInButton.click();

        ExtentReportsListener.extentTestPass("Store Manager sayfasına gidilir");
        homePage.homeSignOut.click();
        myAccount.storeManager.click();

    }

    @Test(dataProvider = "couponsPositiveData")
    public void couponsPositiveTest(String codeName,String descriptionText,String couponAmount,String date) {


        StoreManager storeManager = new StoreManager();

        // Go to the Coupon page
        ExtentReportsListener.extentTestPass("Coupon sayfasına gidilir");
        storeManager.coupons.click();
        storeManager.addNew.click();

        // Log that all necessary text boxes for creating a coupon are being filled
        ExtentReportsListener.extentTestInfo("Coupon oluşturmak gerekli Text boxlar doldurluyor");
        storeManager.codeTextBox.sendKeys(codeName);
        storeManager.descriptionTextBox.sendKeys(descriptionText);
        BrowserUtils.dropdownSelectByVisibleText(storeManager.discountTypeDD,"Percentage discount");
        storeManager.couponAmountNumberBox.sendKeys(couponAmount);
        storeManager.couponExpiryDateBox.sendKeys(date);

        ExtentReportsListener.extentTestPass("Text Boxlara yazı yazılabiliyor");
        ActionsUtils.hoverOver(storeManager.allowFreeShippingCheckBox);

        // Click on the checkboxes
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

        //Check if the coupon we created is visible on the Coupon page
        Assert.assertTrue(storeManager.successMessage.isDisplayed());
        ExtentReportsListener.addScreenshotToReport("Onay mesajının görünürlüğü kontrol edilir");

        WaitUtils.waitFor(2);

        ExtentReportsListener.extentTestInfo("Coupon sayfasında oluşturduğumuz coupun gözüküyor mu diye kontrol edilir");
        JSUtils.JSclickWithTimeout(storeManager.coupons);
        storeManager.couponSearchBox.sendKeys(codeName);
        Assert.assertEquals(storeManager.lastCouponName.getText(),codeName);
    }

    @AfterClass
    public void afterClass() {
        Driver.quitDriver();
    }

}
