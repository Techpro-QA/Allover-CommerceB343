package Tests.UserStory17;

import Pages.*;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;
import utilities.*;

public class US17_AddressNegativeTests {

    HomePage home;
    MyAccountPage my;
    OrdersPage orders;
    ShopPage shop;
    BillingDetailsPage billing;

    @BeforeClass(alwaysRun = true)
    public void setUp() {
        ExtentReportsListener.extentTestInfo("[SETUP] Allover Commerce anasayfasina gidilir");
        Driver.getDriver().get(ConfigReader.getProperty("alloverceUrl"));

        home   = new HomePage();
        my     = new MyAccountPage();
        orders = new OrdersPage();
        shop   = new ShopPage();
        billing= new BillingDetailsPage();

        ExtentReportsListener.extentTestInfo("[LOGIN] Vendor girisi yapiliyor");
        home.homeSignIn.click();
        home.usernameOrEmailAddressTextBox.sendKeys(ConfigReader.getProperty("vendorEmail"));
        home.passwordTextBox.sendKeys(ConfigReader.getProperty("vendorPassword"));
        home.signInButton.click();
        ExtentReportsListener.extentTestPass("[LOGIN] Giris basarili");
    }

    @BeforeMethod(alwaysRun = true)
    public void openGoShopPage() {
        ExtentReportsListener.extentTestInfo("[NAV] My Account > Orders > GO SHOP");
        JSUtils.JSscrollIntoView(my.accountvendorLink);
        WaitUtils.waitForVisibility(my.accountvendorLink, 10);
        JSUtils.JSclickWithTimeout(my.accountvendorLink);

        WaitUtils.waitForVisibility(my.orders, 10);
        JSUtils.JSclickWithTimeout(my.orders);

        WaitUtils.waitForVisibility(orders.GoShop, 10);
        JSUtils.JSclickWithTimeout(orders.GoShop);
        ExtentReportsListener.extentTestPass("[NAV] GO SHOP acildi");
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        ExtentReportsListener.extentTestInfo("[TEARDOWN] Driver kapatiliyor");
        Driver.quitDriver();
    }

    // ---------- SADECE ADRES NEGATIFLERI ----------
    // caseName, streetAddressValue
    // NOT: "" -> tamamen sil; " " -> tek bosluk; "§" -> tek ozel karakter
    @DataProvider(name = "invalidAddressData")
    public Object[][] invalidAddressData() {
        return new Object[][]{
                {"EMPTY_ADDRESS",        ""},   // 1) adres boş
                {"SPACE_ONLY_ADDRESS",   " "},  // 2) adres tek boşluk
                {"SPECIAL_CHAR_ONLY",    "§"}   // 3) adres sadece tek özel karakter
        };
    }

    @Test(dataProvider = "invalidAddressData",
            description = "US_17 NEG – Adres alani gecersiz (bos/bosluk/tek ozel karakter) iken siparis tamamlanmamali")
    public void addressField_Negative(String caseName, String streetVal) {

        // 1) Ürün seç & sepete ekle
        ExtentReportsListener.extentTestInfo("[STEP] Urun secilir");
        WaitUtils.waitForVisibility(shop.productchoose, 10);
        JSUtils.JSclickWithTimeout(shop.productchoose);

        ExtentReportsListener.extentTestInfo("[STEP] Sepete eklenir");
        WaitUtils.waitForVisibility(shop.addToCartButton, 10);
        JSUtils.JSclickWithTimeout(shop.addToCartButton);

        ExtentReportsListener.extentTestInfo("[STEP] Sepete gidilir");
        WaitUtils.waitForVisibility(shop.cartButton, 10);
        JSUtils.JSclickWithTimeout(shop.cartButton);

        ExtentReportsListener.extentTestInfo("[STEP] Checkout'a gidilir");
        WaitUtils.waitForVisibility(shop.checkOutButton, 10);
        JSUtils.JSclickWithTimeout(shop.checkOutButton);

        // 2) Firstname / Lastname kontrolü
       WaitUtils.waitFor(3);
            billing.firstNameBox.sendKeys("TestFirst");
            ExtentReportsListener.extentTestInfo("[DATA] First name dolduruldu: TestFirst");

            billing.lastNameBox.sendKeys("TestLast");
            ExtentReportsListener.extentTestInfo("[DATA] Last name dolduruldu: TestLast");


        //  SADECE ADRES alanina negatif veri uygula
        ExtentReportsListener.extentTestInfo("[STEP] Street Address negatif veri uygulanir");
        billing.streetAddressBox.clear();
        if (!streetVal.isEmpty()) {
            billing.streetAddressBox.sendKeys(streetVal);
        }
        ExtentReportsListener.extentTestInfo("[DATA] street='" + streetVal + "'");


        //  Place order
        WaitUtils.waitFor(3);
        ExtentReportsListener.extentTestInfo("[STEP] Place order tiklanir");
        JSUtils.JSclickWithTimeout(billing.placeOrderButton);


        //  Ayrılmış doğrulama:
        WaitUtils.waitFor(2);
        if ("SPECIAL_CHAR_ONLY".equals(caseName)) {
            // Özel karakterle sipariş TAMAMLANMAMALI → “Order received” görünürse BUG
            boolean orderReceived = isDisplayed(billing.orderReceivedMessage);
            if (orderReceived) {
                ExtentReportsListener.extentTestFail("BUG: Tek ozel karakterle (\"" + streetVal + "\") siparis tamamlandi!");
                Assert.fail("BUG: Tek ozel karakterle siparis tamamlandi! (case=" + caseName + ")");
            }
            // Tamamlanmadıysa normal hata mesajını bekle
        }

        // Tüm vakalarda beklenen: checkout genel hata kutusu görünmeli
        WaitUtils.waitFor(3);
        WebElement errorBox = WaitUtils.waitForVisibility(billing.checkBoxErrorMessage, 10);
        Assert.assertTrue(errorBox.isDisplayed(),
                "[ASSERT] Hata mesaji gorunmedi! (case=" + caseName + ")");
        ExtentReportsListener.extentTestPass("[ASSERT] Hata mesaji gorundu (beklenen)");

        ExtentReportsListener.extentTestInfo("[CASE END] " + caseName);
    }

    // ------- küçük yardımcı -------
    private boolean isDisplayed(WebElement el) {
        try { return el != null && el.isDisplayed(); } catch (Exception e) { return false; }
    }
}

