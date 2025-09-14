package Tests.US_18;


import Pages.Homepage;
import Pages.MyAccount;
import Pages.StoreManager;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import utilities.*;


public class TC02_NegativeTests {
    @DataProvider()
    public static Object[][] couponsNegativeData() {
        return new Object[][]{
                //Negative test

                //"Coupon cannot be created by entering a past date.(Negative Scenario)"
                {"kartanesi","kış indirimi","200","1925-04-09"}

                //"If the Code TextBox is left blank, no coupon should be created.(Negative Scenario)"
                ,{"","kış indirimi","1000","2026-08-09"}

                //"If the Coupon Amount Textbox is left blank, the coupon should not be created.(Negative Scenario)"
                ,{"ilkbahar","ilkbahar indirimi","","2026-08-09"}

                //"If the Coupon expiry date textbox is left blank, the coupon should not be created.(Negative Scenario)"
                ,{"sonbahar00","sonbahar indirimi","5",""}

                //"Coupon cannot be created when numbers that do not specify a date are entered in the Coupon expiry date textbox.(Negative Scenario)"
                ,{"kutulu30","kutu oyunu indirimi indirimi","25","1234567"}

        };
    }

    @BeforeClass
    public void setUp() {
        Driver.getDriver().get(ConfigReader.getProperty("alloverCommerceUrl"));

        Homepage homePage = new Homepage();
        MyAccount myAccount = new MyAccount();

        //Log in as a vendor
        homePage.signInButton.click();
        homePage.userNameOrEmail.sendKeys(ConfigReader.getProperty("vendorEmail"));
        homePage.passwordOnSingIn.sendKeys(ConfigReader.getProperty("vendorPassword"));
        homePage.signInButton2.click();

        ExtentReportsListener.extentTestPass("Store Manager sayfasına gidilir");
        homePage.singOutButtonClickable.click();
        myAccount.storeManager.click();
    }

    @Test(dataProvider = "couponsNegativeData")
    public void couponsNegativeTest(String codeName,String descriptionText,String couponAmount,String date) {

        StoreManager storeManager = new StoreManager();
        SoftAssert softAssert = new SoftAssert();

        // Go to the Coupon page
        ExtentReportsListener.extentTestInfo("Coupon sayfasına gidilir");
        storeManager.coupons.click();
        storeManager.addNew.click();

        //All text boxes required to create a coupon are filled with incorrect data
        ExtentReportsListener.extentTestInfo("Coupon oluşturmak gerekli Text boxlar doldurluyor");
        storeManager.codeTextBox.sendKeys(codeName);
        storeManager.descriptionTextBox.sendKeys(descriptionText);
        BrowserUtils.dropdownSelectByVisibleText(storeManager.discountTypeDD,"Percentage discount");
        storeManager.couponAmountNumberBox.sendKeys(couponAmount);
        storeManager.couponExpiryDateBox.sendKeys(date);

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

        ExtentReportsListener.addScreenshotToReport("Coupon oluşturulamadı mesajının görünürlüğü kontrol edilir");
        WaitUtils.waitFor(2);
        //Check if the coupon we created is visible on the Coupon page
        ExtentReportsListener.extentTestInfo("Coupon sayfasında oluşturduğumuz coupunun gözülmediği kontrol edilir");
        JSUtils.JSclickWithTimeout(storeManager.coupons);
        storeManager.couponSearchBox.sendKeys(codeName);
        WaitUtils.waitFor(2);

        //I used soft assert because I knew that the tests would fail from manual testing.

        //Check if coupons can be created
        ExtentReportsListener.addScreenshotToReport("Oluşturulan kupon("+ codeName + ") ekranda görülememeli");
        softAssert.assertFalse(storeManager.lastCouponName.isDisplayed());
        softAssert.assertAll();
    }

    @AfterClass
    public void afterClass() {
        Driver.quitDriver();
    }
}
