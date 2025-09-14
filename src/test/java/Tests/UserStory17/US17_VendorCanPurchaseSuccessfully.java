package Tests.UserStory17;

import Pages.*;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;
import utilities.*;

@Listeners(ExtentReportsListener.class)
public class US17_VendorCanPurchaseSuccessfully {


    @BeforeMethod
    public void setUp() {

        //1 siteye git
        ExtentReportsListener.extentTestInfo("Allover Commerce anasayfasina gidilir");
        Driver.getDriver().get(ConfigReader.getProperty("alloverceUrl"));
        // Vendor login
        ExtentReportsListener.extentTestInfo("Vendor hesabıyla sisteme giriş yapılir");

        HomePage home = new HomePage();
        MyAccountPage my = new MyAccountPage();
        OrdersPage orders = new OrdersPage();


        home.homeSignIn.click();
        home.usernameOrEmailAddressTextBox.sendKeys(ConfigReader.getProperty("vendorEmail"));
        home.passwordTextBox.sendKeys(ConfigReader.getProperty("vendorPassword"));
        home.signInButton.click();

        // My Account > Orders > Browse products
        ExtentReportsListener.extentTestInfo("GoShop varsayılan olarak gelmeli");
        WaitUtils.waitFor(2);
        JSUtils.JSscrollIntoView(my.accountvendorLink);
        WaitUtils.waitForVisibility(my.accountvendorLink, 2);
        JSUtils.JSclickWithTimeout(my.accountvendorLink);

        WaitUtils.waitFor(2);
        JSUtils.JSscrollIntoView(my.orders);
        WaitUtils.waitForVisibility(my.orders, 2);
        JSUtils.JSclickWithTimeout(my.orders);

        WaitUtils.waitFor(2);
        JSUtils.JSscrollIntoView(orders.GoShop);
        WaitUtils.waitForVisibility(orders.GoShop, 2);
        JSUtils.JSclickWithTimeout(orders.GoShop);


    }
    @Test(description = "Vendor basariyla alisveris yapabilmeli ")
    public void VendorCanPurchaseSuccessfullyTest() {

        ShopPage shop = new ShopPage();
        BillingDetailsPage billing = new BillingDetailsPage();
        ExtentReportsListener.extentTestInfo("Urun seçilir");

        JSUtils.JSclickWithTimeout(shop.productchoose);


        // Add to cart
        ExtentReportsListener.extentTestInfo("Urun sepete eklenir");

        JSUtils.JSclickWithTimeout(shop.addToCartButton);

        // Sepet
        ExtentReportsListener.extentTestInfo("Urun sepette goruntulenir");


        WaitUtils.waitFor(5);
        JSUtils.JSclickWithTimeout(shop.cartButton);

        //Checkout

        ExtentReportsListener.extentTestInfo("Checkout yapilir");

        JSUtils.JSclickWithTimeout(shop.checkOutButton);

        //Billing Detail bolumu kotrol edilir ve payment yontemi olarak kart secilir
        ExtentReportsListener.extentTestInfo("payment yontemi varsayilan WISE/EFT secilir");
        WaitUtils.waitFor(10);
        JSUtils.JSclickWithTimeout(billing.bacsCase);

        // Place order
        ExtentReportsListener.extentTestInfo("Place order butonuna tiklanir");
        WaitUtils.waitFor(3);
        JSUtils.JSclickWithTimeout(billing.placeOrderButton);
        // Order received
        ExtentReportsListener.extentTestInfo("Alisveris başarıyla tamamlanmali ve onay mesaji goruntulenmelidir.");
        WebElement received = WaitUtils.waitForVisibility(billing.orderReceivedMessage, 20);
        Assert.assertTrue(received.isDisplayed(), "Sipariş tamamlanamadı!");

        //BackToList Butonuna tiklanir
        ExtentReportsListener.extentTestInfo("My Aaccount Orders sayfasina geri donulur.");
        WaitUtils.waitFor(3);
        JSUtils.JSclickWithTimeout(shop.backToListButton);

        //My Account sayfasindan Orders'larda VIEw e tiklanir.
        OrdersPage orders = new OrdersPage();
        JSUtils.JSclickWithTimeout(orders.view);

        // 1) Order details bloğu görünmeli
        ExtentReportsListener.extentTestInfo("Verilen siparisin My Account/Orders bolumunde ayrintilarinin goruntulendigi dogrulanir.");
        WaitUtils.waitForVisibility(orders.orderDetails, 10);
        Assert.assertTrue(orders.orderDetails.isDisplayed(), "Order details bölümü görünmüyor!");


        ExtentReportsListener.extentTestInfo("Test tamamlandi");






    }


    @AfterMethod
    public void tearDown() {

        Driver.quitDriver();
    }


}








