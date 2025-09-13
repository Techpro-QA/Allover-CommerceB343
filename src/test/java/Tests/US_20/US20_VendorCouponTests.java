package Tests.US_20;

import Pages.CartPage;
import Pages.CheckoutPage;
import Pages.Home;
import Pages.OrdersPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.*;

import java.util.List;

public class US20_VendorCouponTests {

    CartPage cartPage;
    CheckoutPage checkoutPage;
    OrdersPage ordersPage;
    Home home;

    @BeforeMethod
    public void setup() {
        cartPage = new CartPage();
        checkoutPage = new CheckoutPage();
        ordersPage = new OrdersPage();
        home = new Home();
        Driver.getDriver().get(ConfigReader.getProperty("alloverCommerceUrl"));
        home.homeSignIn.click();
        home.usernameOrEmailAddressTextBox.sendKeys(ConfigReader.getProperty("vendorEmail"));
        home.passwordTextBox.sendKeys(ConfigReader.getProperty("vendorPassword"));
        home.signInButton.click();
        ReusableMethods.navigateToMenu("//section[@data-id='8d64cda']");
        WaitUtils.waitForVisibility(By.xpath("//button[contains(text(),'Add to cart')][1]"), 10);
        ReusableMethods.clickAnyElement("//button[contains(text(),'Add to cart')][1]");
    }

    @AfterMethod()
    public void tearDown() {
        Driver.quitDriver();
    }

    @Test(description = "TC_01_POS: Vendor olarak ürün sepete eklenebilmeli")
    public void testAddProductToCart() {
        ExtentReportsListener.extentTestInfo("Vendor hesabıyla sisteme giriş yapılmış");
        ExtentReportsListener.extentTestInfo("Listelenen ürünlerden herhangi birinin 'Add to Cart' (Sepete Ekle) butonuna tıkla");
        ReusableMethods.clickAnyElement("//a[.='View Cart']");

        // Quantity değeri al ve int'e çevir
        String qtyValue = cartPage.quantityInput.getAttribute("value"); // örnek: "3"
        int qty = Integer.parseInt(qtyValue);

        // Assertion: quantity > 0
        ExtentReportsListener.extentTestInfo("Sağ üstteki sepete tıklayarak ürünün eklendiğini kontrol et");
        Assert.assertTrue(qty > 0, "Sepette ürün adedi 0 veya eksik!");
        System.out.println("Sepetteki ürün miktarı: " + qty);

        // Verify product name, price, quantity in cart preview
        ExtentReportsListener.extentTestInfo("Ürün adı, fiyatı ve miktarı sepet ön izlemesinde görünür.");
        Assert.assertTrue(ReusableMethods.isElementDisplayed("//td[@data-title='Product']"),
                "Ürün adı görünmeli");
        Assert.assertTrue(ReusableMethods.isElementDisplayed("//div[@class='quantity']"),
                "Ürün miktarı görünmeli");

    }

    @Test(description = "TC_02_POS: Sepet içerikleri doğru görüntülenmeli")
    public void testCartContentsDisplay() {
        ExtentReportsListener.extentTestInfo("Vendor hesabıyla sisteme giriş yapılmış");
        ActionsUtils.scrollHome();
        ExtentReportsListener.extentTestInfo("Sağ üst köşedeki 'Cart' simgesine tıkla");
        JSUtils.JSclickWithTimeout(cartPage.cartButton);
        ReusableMethods.clickAnyElement("//a[.='View cart']");

        ExtentReportsListener.extentTestInfo("Ürün adını, miktarını ve fiyatını kontrol et");
        Assert.assertTrue(ReusableMethods.isElementDisplayed("//td[@data-title='Product']"),
                "Sepet ürünü listelenmeli");
        Assert.assertTrue(ReusableMethods.isElementDisplayed("//div[@class='quantity']"),
                "Ürün miktarı doğru gösterilmeli");

        // Click Checkout button
        ExtentReportsListener.extentTestInfo("“Checkout” butonuna tıkla ve yönlendirildiğin sayfada ürün detaylarını kontrol et");
        JSUtils.JSclickWithTimeout(checkoutPage.proceedToCheckoutButton);
        WaitUtils.waitFor(2);

        // Verify Checkout page product details
        Assert.assertTrue(ReusableMethods.isElementDisplayed("//th[.='Product']"),
                "Checkout ürün adı görünmeli");
        Assert.assertTrue(ReusableMethods.isElementDisplayed("//strong[@class='product-quantity']"),
                "Checkout ürün miktarı görünmeli");

        ExtentReportsListener.extentTestInfo("Sepet içeriğinde tüm ürün bilgileri eksiksiz ve doğru bir şekilde görüntülenmeli");

    }

    @Test(description = "TC_03_POS: Fatura bilgileri eksiksiz doldurulmalı")
    public void testFillBillingDetails() {
        ExtentReportsListener.extentTestInfo("Vendor hesabıyla sisteme giriş yapılmış");
        ActionsUtils.scrollHome();
        JSUtils.JSclickWithTimeout(cartPage.cartButton);
        ReusableMethods.clickAnyElement("//a[.='View cart']");

        ExtentReportsListener.extentTestInfo("Checkout ekranında “Billing Details” alanını bul");
        ExtentReportsListener.extentTestInfo("Zorunlu alanlara bilgileri gir");
        JSUtils.JSclickWithTimeout(checkoutPage.proceedToCheckoutButton);
        checkoutPage.billingFirstName.clear();
        checkoutPage.billingFirstName.sendKeys("Akif");

        checkoutPage.billingLastName.clear();
        checkoutPage.billingLastName.sendKeys("Rencber");

        checkoutPage.billingAddress.clear();
        checkoutPage.billingAddress.sendKeys("Cevizli Mah. No:12");

        checkoutPage.billingCity.clear();
        checkoutPage.billingCity.sendKeys("İstanbul");

        checkoutPage.billingPostcode.clear();
        checkoutPage.billingPostcode.sendKeys("34846");

        checkoutPage.billingPhone.clear();
        checkoutPage.billingPhone.sendKeys("5555555555");

        checkoutPage.billingEmail.clear();
        checkoutPage.billingEmail.sendKeys("akif@example.com");


        ExtentReportsListener.extentTestInfo("Sayfanın altındaki ödeme yöntemine geç");
        Assert.assertTrue(checkoutPage.billingFirstName.getAttribute("value").length() > 0, "Ad alanı doldurulmalı");
        Assert.assertTrue(checkoutPage.billingLastName.getAttribute("value").length() > 0, "Soyad alanı doldurulmalı");
        ExtentReportsListener.extentTestInfo("Tüm bilgiler sorunsuz şekilde kaydedilir ve bir sonraki adıma geçilirken sistem hata vermez");
    }

    @Test(description = "TC_04_POS: Geçerli kupon kodu sepete uygulanmalı  -  BUG: Gecerli kupon basariyla uygulanamadi")
    public void testApplyValidCoupon() {
        ExtentReportsListener.extentTestInfo("Vendor hesabıyla sisteme giriş yapılmış");
        String couponCode = "SAVE10";
        JSUtils.JSclickWithTimeout(cartPage.cartButton);
        ExtentReportsListener.extentTestInfo("Kullanıcı Checkout sayfasına yönlendirilmiş olmalı");
        JSUtils.JSclickWithTimeout(checkoutPage.checkoutButton);

        ExtentReportsListener.extentTestInfo("“Enter your code” alanını bul");
        checkoutPage.enterYourCode.click();
        ExtentReportsListener.extentTestInfo("Geçerli kupon kodunu gir");
        checkoutPage.couponInputInCheckout.sendKeys(couponCode);
        ExtentReportsListener.extentTestInfo("“Apply Coupon” butonuna tıkla");
        checkoutPage.applyCouponBtnInCheckout.click();

        ExtentReportsListener.extentTestInfo("Sayfa yeniden yüklendiğinde indirim tutarını kontrol et");
        String expectedSuccessMessage = "Coupon code applied successfully";
        ExtentReportsListener.addScreenshotToReport("BUG: Gecerli kupon basariyla uygulanamadi");

        WebElement alertMessage = Driver.getDriver().findElement(By.xpath("//li[@class=' alert alert-simple alert-icon alert-close-top alert-danger']"));
        String actualMessage = alertMessage.getText();

        System.out.println("Coupon result message: " + actualMessage);


        if (!actualMessage.contains(expectedSuccessMessage)) {
            Assert.fail("BUG: Beklenen mesaj alınamadı. Alınan mesaj: '" + actualMessage + "'");
        }

        ExtentReportsListener.extentTestInfo("BUG: Beklenen mesaj alınamadı.");
        Assert.assertTrue(actualMessage.contains(expectedSuccessMessage),
                "Coupon applied successfully mesajı görünmeli");

    }

    @Test(description = "TC_05_POS: Sepette toplam ödeme tutarı doğru hesaplanmalı - BUG: Kupon uygulanmadığı için indirim görünmüyor.")
    public void testTotalAmountCalculation() {
        ExtentReportsListener.extentTestInfo("Vendor hesabıyla sisteme giriş yapılmış");
        // Burada manuel veya otomatik hesaplama yapılabilir.
        JSUtils.JSclickWithTimeout(cartPage.cartButton);
        JSUtils.JSclickWithTimeout(checkoutPage.checkoutButton);

        // Order Total ve Shipping görünmeli
        ExtentReportsListener.extentTestInfo("Checkout sayfasında ödeme özeti alanına git");
        Assert.assertTrue(checkoutPage.orderTotal.isDisplayed(), "Genel toplam görünmeli");
        Assert.assertTrue(checkoutPage.shippingRow.isDisplayed(), "Kargo ücreti görünmeli");

        // Kupon indirim satırı kontrolü
        ExtentReportsListener.addScreenshotToReport("BUG: Kupon uygulanmadığı için indirim görünmüyor.");
        List<WebElement> discountElements = Driver.getDriver().findElements(By.xpath("//tr[@class='cart-discount']"));
        ExtentReportsListener.addScreenshotToReport("BUG: Gecerli kupon basariyla uygulanamadi");

        if (discountElements.isEmpty()) {
            Assert.fail("BUG: Kupon uygulanmadığı için indirim satırı görünmüyor. Kupon sistemi düzgün çalışmıyor.");
        } else {
            WebElement discountRow = discountElements.get(0);
            Assert.assertTrue(discountRow.isDisplayed(), "İndirim satırı görünmeli");
            System.out.println("Discount: " + discountRow.getText());
        }
        ExtentReportsListener.extentTestInfo("BUG: Kupon uygulanmadığı için indirim satırı görünmüyor. Kupon sistemi düzgün çalışmıyor.");
    }

    @Test(description = "TC_06_POS: Ödeme yöntemi seçim alanı çalışmalı")
    public void testPaymentMethodSelection() {

        ExtentReportsListener.extentTestInfo("Vendor hesabıyla sisteme giriş yapılmış");
        JSUtils.JSclickWithTimeout(cartPage.cartButton);
        JSUtils.JSclickWithTimeout(checkoutPage.checkoutButton);
        ExtentReportsListener.extentTestInfo("“Payment Method” başlığı altındaki seçenekleri kontrol et");

        // Wire Transfer seç
        ExtentReportsListener.extentTestInfo("İlk olarak “Wire Transfer/EFT” seçeneğini işaretle");
        JSUtils.JSclickWithTimeout(checkoutPage.wireTransferOption);
        Assert.assertTrue(checkoutPage.wireTransferOption.isSelected(), "Wire Transfer seçilmeli");

        // Pay at the Door seç
        ExtentReportsListener.extentTestInfo("Ardından “Pay at the Door” seçeneğini dene");
        JSUtils.JSclickWithTimeout(checkoutPage.payAtDoorOption);
        Assert.assertTrue(checkoutPage.payAtDoorOption.isSelected(), "Pay at the Door seçilmeli");
        ExtentReportsListener.extentTestInfo("Her iki ödeme seçeneği de seçilebilir olmalı ve seçilen yöntem kayıt altına alınmalı ve sipariş özetinde görünmeli");
    }

    @Test(description = "TC_07_POS: Sipariş başarıyla tamamlanmalı - BUG: Kupon uygulanmadığı için sipariş başarıyla tamamlanamıyor.")
    public void testPlaceOrder() {
        ExtentReportsListener.extentTestInfo("Vendor hesabıyla sisteme giriş yapılmış");
        JSUtils.JSclickWithTimeout(cartPage.cartButton);
        JSUtils.JSclickWithTimeout(checkoutPage.checkoutButton);
        ActionsUtils.scrollDown();

        ExtentReportsListener.extentTestInfo("Checkout sayfasında “Place Order” butonuna tıkla");
        JSUtils.JSclickWithTimeout(checkoutPage.placeOrderButton);
        WaitUtils.waitFor(3);
        ActionsUtils.scrollHome();
        WaitUtils.waitFor(2);

        ExtentReportsListener.extentTestInfo("Yönlendirilen onay sayfasını kontrol et");
        ExtentReportsListener.addScreenshotToReport("BUG: Kupon uygulanmadan sipariş tamamlandı ya da sistem kontrol edilemiyor.");
        Assert.fail("BU BİLİNÇLİ HATA: TC_07_POS test senaryosunda kupon zorunlu olmalı ama uygulanmadan sipariş tamamlanabiliyor.");

    }

    @Test(description = "TC_08_POS: Sipariş geçmişi ve detayları görüntülenmeli- BUG: Kupon bilgisi sipariş detaylarında görünmüyor.")
    public void testOrderHistoryAndDetails() {
        ExtentReportsListener.extentTestInfo("Vendor hesabıyla sisteme giriş yapılmış");
        JSUtils.JSclickWithTimeout(home.homeSignOut);
        ExtentReportsListener.extentTestInfo("Kullanıcı üst menüden “My Account” > “Orders” sekmesine tıklar");
        ordersPage.ordersTab.click();
        ordersPage.firstOrderViewButton.click();

        ExtentReportsListener.extentTestInfo("Sipariş listesinde en son yapılan siparişin “View” (Görüntüle) butonuna tıkla");
        Assert.assertTrue(ordersPage.orderDetailsHeading.isDisplayed(), "Sipariş detayları görünmeli");
        Assert.assertTrue(ordersPage.paymentMethodInfo.isDisplayed(), "Ödeme yöntemi görünmeli");
        Assert.assertTrue(ordersPage.totalAmountInfo.isDisplayed(), "Toplam tutar görünmeli");

        List<WebElement> couponElements = Driver.getDriver().findElements(By.xpath("//*[contains(text(), 'Coupon') or contains(text(),'Discount')]"));

        if (couponElements.isEmpty()) {
            ExtentReportsListener.addScreenshotToReport("BUG: Kupon uygulanmadığı için sipariş başarıyla tamamlanamıyor.");
            Assert.fail("BUG: Sipariş detaylarında kupon bilgisi görünmüyor. Uygulanan kupon sistemde kaydedilmemiş olabilir.");
        } else {
            System.out.println("Kupon bilgisi bulundu: " + couponElements.get(0).getText());
        }
        ExtentReportsListener.extentTestInfo("BUG: Kupon bilgisi sipariş detaylarında görünmüyor.");
    }

    @Test(description = "TC_09_NEG: Geçersiz kupon kodu reddedilmeli")
    public void testInvalidCouponCode() {
        ExtentReportsListener.extentTestInfo("Vendor hesabıyla sisteme giriş yapılmış");
        String invalidCoupon = "XYZ123";
        JSUtils.JSclickWithTimeout(cartPage.cartButton);
        ExtentReportsListener.extentTestInfo("Checkout ekranına git");
        JSUtils.JSclickWithTimeout(checkoutPage.checkoutButton);

        ExtentReportsListener.extentTestInfo("“Enter your code” alanına rastgele bir kupon kodu gir");
        checkoutPage.enterYourCode.click();
        checkoutPage.couponInputInCheckout.sendKeys(invalidCoupon);
        ExtentReportsListener.extentTestInfo("“Apply” butonuna tıkla");
        checkoutPage.applyCouponBtnInCheckout.click();

        ExtentReportsListener.extentTestInfo("'Coupon code is invalid' mesajı görünmeli");
        Assert.assertTrue(cartPage.invalidCouponMessage.isDisplayed(),
                "'Coupon code is invalid' mesajı görünmeli");
    }

    @Test(description = "TC_10_NEG: Süresi geçmiş kupon kodu reddedilmeli")
    public void testExpiredCouponCode() {
        ExtentReportsListener.extentTestInfo("Vendor hesabıyla sisteme giriş yapılmış");
        String expiredCoupon = "SUMMER2023";
        JSUtils.JSclickWithTimeout(cartPage.cartButton);
        ExtentReportsListener.extentTestInfo("Checkout ekranına git");
        JSUtils.JSclickWithTimeout(checkoutPage.checkoutButton);

        ExtentReportsListener.extentTestInfo("“Enter your code” alanına kupon kodu gir");
        checkoutPage.enterYourCode.click();
        checkoutPage.couponInputInCheckout.sendKeys(expiredCoupon);
        ExtentReportsListener.extentTestInfo("“Apply” butonuna tıkla");
        checkoutPage.applyCouponBtnInCheckout.click();

        ExtentReportsListener.extentTestInfo("'Coupon has expired' mesajı görünmeli");
        Assert.assertTrue(cartPage.expiredCouponMessage.isDisplayed(),
                "'Coupon has expired' mesajı görünmeli");
    }

    @Test(description = "TC_11_NEG: Eksik fatura bilgileri ile sipariş reddedilmeli")
    public void testPlaceOrderWithMissingBillingInfo() {
        ExtentReportsListener.extentTestInfo("Vendor hesabıyla sisteme giriş yapılmış");
        JSUtils.JSclickWithTimeout(cartPage.cartButton);
        ExtentReportsListener.extentTestInfo("Checkout ekranına git");
        JSUtils.JSclickWithTimeout(checkoutPage.checkoutButton);

        ExtentReportsListener.extentTestInfo("Fatura bilgilerinden bir ya da birkaçını boş bırak ");
        checkoutPage.billingFirstName.clear(); // Eksik bilgi
        ExtentReportsListener.extentTestInfo("“Place Order” butonuna tıkla");
        WaitUtils.waitFor(3);
        ActionsUtils.scrollDown();
        WaitUtils.waitFor(3);
        JSUtils.JSclickWithTimeout(checkoutPage.placeOrderButton);

        WaitUtils.waitFor(2);
        ExtentReportsListener.extentTestInfo("'Billing First name is a required field.' hata mesajı görünmeli");
        Assert.assertTrue(ReusableMethods.isElementDisplayed("//*[@data-id='billing_first_name']"),
                "'Billing First name is a required field.' hata mesajı görünmeli");
    }

    @Test(description = "TC_12_NEG: Boş sepet ile ödeme ekranına erişim engellenmeli")
    public void testCheckoutWithEmptyCart() {
        ExtentReportsListener.extentTestInfo("Vendor hesabıyla sisteme giriş yapılmış");
        // Önce sepeti temizle veya boş sepetle başla
        JSUtils.JSclickWithTimeout(cartPage.cartButton);
        ReusableMethods.clickAnyElement("//a[.='View Cart']");

        ExtentReportsListener.extentTestInfo("'View Cart' ekranına git");
        ExtentReportsListener.extentTestInfo("'Clear Cart' butonuna tıkla");
        cartPage.clearCartButton.click();

        ExtentReportsListener.extentTestInfo("'Your cart is currently empty.' uyarısı görünmeli");
        Assert.assertTrue(ReusableMethods.isElementDisplayed("//p[contains(text(),'Your cart is currently empty.')]"),
                "'Your cart is currently empty.' uyarısı görünmeli");
    }

    @Test(description = "TC_13_NEG: Minimum harcama koşulu karşılanmayan kupon reddedilmeli")
    public void testCouponMinSpendRequirement() {
        ExtentReportsListener.extentTestInfo("Vendor hesabıyla sisteme giriş yapılmış");
        String coupon = "SAVE20";
        JSUtils.JSclickWithTimeout(cartPage.cartButton);
        ExtentReportsListener.extentTestInfo("Checkout ekranına git");
        JSUtils.JSclickWithTimeout(checkoutPage.checkoutButton);

        checkoutPage.enterYourCode.click();
        ExtentReportsListener.extentTestInfo("Checkout ekranına kodu gir");
        checkoutPage.couponInputInCheckout.sendKeys(coupon);
        ExtentReportsListener.extentTestInfo("“Apply” butonuna tıkla");
        checkoutPage.applyCouponBtnInCheckout.click();

        ExtentReportsListener.extentTestInfo("'The minimum spend for this coupon is $500.00.' uyarısı görünmeli");
        Assert.assertTrue(ReusableMethods.isElementDisplayed("//*[@class=' alert alert-simple alert-icon alert-close-top alert-danger']"),
                "'The minimum spend for this coupon is $500.00.' uyarısı görünmeli");
    }

    @Test(description = "TC_14_NEG: Tek kullanımlık kupon tekrar kullanım engellenmeli - BUG: Kupon sistemi tamamen çalışmıyor")
    public void testSingleUseCoupon() {
        ExtentReportsListener.extentTestInfo("Vendor hesabıyla sisteme giriş yapılmış");
        String coupon = "WELCOME15";
        JSUtils.JSclickWithTimeout(cartPage.cartButton);
        ExtentReportsListener.extentTestInfo("Checkout ekranına git");
        JSUtils.JSclickWithTimeout(checkoutPage.checkoutButton);

        checkoutPage.enterYourCode.click();
        ExtentReportsListener.extentTestInfo("Checkout ekranına kodu gir");
        checkoutPage.couponInputInCheckout.sendKeys(coupon);
        checkoutPage.applyCouponBtnInCheckout.click();

        ExtentReportsListener.extentTestInfo("BUG: Kupon hiç uygulanamıyor. 'This coupon has already been used' uyarısı beklenirken, sistem hiçbir mesaj göstermedi.");
        List<WebElement> warningMessages = Driver.getDriver().findElements(By.xpath("//*[contains(text(),'already been used')]"));

        if (warningMessages.isEmpty()) {
            ExtentReportsListener.addScreenshotToReport("BUG: Kupon sistemi tamamen çalışmıyor");
            Assert.fail("BUG: Kupon hiç uygulanamıyor. 'This coupon has already been used' uyarısı beklenirken, sistem hiçbir mesaj göstermedi.");
        } else {
            Assert.assertTrue(warningMessages.get(0).isDisplayed(),
                    "'This coupon has already been used' uyarısı görünmeli");
        }
    }




}
