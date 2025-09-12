package Tests.US_16;

import Pages.Home;
import Pages.MyAccount;
import Pages.ProductPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;
import utilities.*;

import java.util.List;

public class US16_VendorProductTest {

    @BeforeClass
    public void setUp() {
        Driver.getDriver().get(ConfigReader.getProperty("alloverCommerceUrl"));

        // Vendor login işlemi
        Home home = new Home();
        MyAccount myAccount = new MyAccount();
        home.homeSignIn.click();
        home.usernameOrEmailAddressTextBox.sendKeys(ConfigReader.getProperty("vendorEmail"));
        home.passwordTextBox.sendKeys(ConfigReader.getProperty("vendorPassword"));
        home.signInButton.click();
        home.homeSignOut.click();
        myAccount.storeManager.click();
    }

    @BeforeMethod
    public void openAddProductPage() {
        Driver.getDriver().get("https://allovercommerce.com/store-manager/products-manage/");
        ProductPage productPage = new ProductPage();
        WaitUtils.waitFor(10);
        WaitUtils.waitForVisibility(productPage.addNewButton,10);
        JSUtils.JSclickWithTimeout(productPage.addNewButton);
        WaitUtils.waitFor(2);
    }

    @AfterClass
    public void tearDown() {
            Driver.getDriver().quit();
    }

    @DataProvider(name = "productData")
    public Object[][] productData() {
        return new Object[][] {
                {"Basic Cotton T-Shirt", "100", "80", "C:\\Users\\seyda\\Desktop\\allovercommerceimage\\Basic Cotton T-Shirt.webp"},
                {"Blue Denim Jeans", "150", "120", "C:\\Users\\seyda\\Desktop\\allovercommerceimage\\Blue Denim Jeans.webp"},
                {"Leather Jacket", "300", "250", "C:\\Users\\seyda\\Desktop\\allovercommerceimage\\Leather Jacket.webp"}
        };
    }

    @Test(dataProvider = "productData", description = "TC_POS_01: Vendor olarak başarılı bir şekilde Simple Product ekleyebilmeliyim")
    public void TC_POS_01_addSimpleProduct(String title, String price, String salePrice, String imagePath) {
        ExtentReportsListener.extentTestInfo("Allover Commerce anasayfasina gidilir");
        ExtentReportsListener.extentTestInfo("Vendor hesabıyla sisteme giriş yapılmış");
        ExtentReportsListener.extentTestInfo("Simple Product varsayılan olarak gelmeli");

        ProductPage productPage = new ProductPage();
        ExtentReportsListener.extentTestInfo("Virtual seçilir");
        WaitUtils.waitForVisibility(productPage.virtualCheckbox, 10);
        JSUtils.JSclickWithTimeout(productPage.virtualCheckbox);

        //Upload Files
        ExtentReportsListener.extentTestInfo("Downloadable seçilir");
        ActionsUtils.hoverOver(productPage.uploadFile);
        productPage.uploadFile.click();
        productPage.uploadFiles.click();
        productPage.selectFilesButton.click();
        ReusableMethods.uploadFilePath(imagePath);
        productPage.selectButton.click();

        WaitUtils.waitFor(2);

        ActionsUtils.hoverOver(productPage.subUploadFile);
        JSUtils.JSclickWithTimeout(productPage.subUploadFile);

        WaitUtils.waitFor(3);
        ActionsUtils.pressTab();
        ActionsUtils.pressTab();
        ActionsUtils.pressTab();
        ActionsUtils.pressTab();
        ActionsUtils.pressTab();
        ActionsUtils.pressTab();
        ActionsUtils.pressEnter();
        productPage.addToGallery.click();

        //DataBox
        ExtentReportsListener.extentTestInfo("Product Title girilir");
        WaitUtils.waitFor(2);
        productPage.productTitleBox.sendKeys(title);
        ExtentReportsListener.extentTestInfo("Price yazılır");
        productPage.priceBox.sendKeys(price);
        ExtentReportsListener.extentTestInfo("Sale Price yazılır");
        productPage.salePriceBox.sendKeys(salePrice);

        //Category
        ExtentReportsListener.extentTestInfo("Kategori seçilir");
        ActionsUtils.scrollDown();
        WaitUtils.waitFor(3);
        JSUtils.JSclickWithTimeout(productPage.clothingCategoryCheckbox);

        WaitUtils.waitFor(3);
        ActionsUtils.scrollEnd();

        ExtentReportsListener.extentTestInfo("Submit butonuna tıklanır");
        JSUtils.JSclickWithTimeout(productPage.submitButton);

        ExtentReportsListener.extentTestInfo("Ürün başarıyla eklenmeli ve ürün listesinde görünmelidir.");
        Assert.assertTrue(productPage.successMessage.isDisplayed());

    }

    //Negative Tests

    @DataProvider(name = "emptyFieldsData")
    public Object[][] emptyFieldData() {
        return new Object[][]{
                {"", "", "", false}, // Tüm alanlar boş
                {"", "100", "80", true}, // Başlık boş
                {"T-Shirt", "", "80", true}, // Fiyat boş
                {"T-Shirt", "100", "", true}, // Satış fiyatı boş
                {"T-Shirt", "100", "80", false}, // Kategori seçilmemiş
        };
    }

    @Test(dataProvider = "emptyFieldsData", description = "TC_NEG_01: Zorunlu alanlar boş bırakıldığında ürün eklenemediğini doğrulamak")
    public void TC_NEG_01_requiredFieldsEmpty(
            String title, String price, String salePrice, boolean selectCategory) {
        ExtentReportsListener.extentTestInfo("Allover Commerce anasayfasina gidilir");
        ExtentReportsListener.extentTestInfo("Vendor hesabıyla sisteme giriş yapılmış");
        ExtentReportsListener.extentTestInfo("Simple Product varsayılan olarak gelmeli");
        ProductPage productPage = new ProductPage();

        // Simple Product varsayılan olarak gelmeli
        ExtentReportsListener.extentTestInfo("Virtual boş bırakılır");

        ExtentReportsListener.extentTestInfo("Product Title boş bırakılır");
        if (!title.isEmpty()) productPage.productTitleBox.sendKeys(title);
        ExtentReportsListener.extentTestInfo("Price boş bırakılır");
        if (!price.isEmpty()) productPage.priceBox.sendKeys(price);
        ExtentReportsListener.extentTestInfo("Sale Price boş bırakılır");
        if (!salePrice.isEmpty()) productPage.salePriceBox.sendKeys(salePrice);
        ActionsUtils.scrollDown();
        ExtentReportsListener.extentTestInfo("Kategori seçilmez");
        if (selectCategory) JSUtils.JSclickWithTimeout(productPage.clothingCategoryCheckbox);

        WaitUtils.waitFor(2);
        ActionsUtils.scrollEnd();
        ExtentReportsListener.extentTestInfo("Submit butonuna tıklanır");
        JSUtils.JSclickWithTimeout(productPage.submitButton);

        WaitUtils.waitFor(2);
        ExtentReportsListener.extentTestInfo("“This field is required” gibi hata mesajları gösterilmeli, ürün eklenmemelidir.");
        WebElement errorMsg = Driver.getDriver().findElement(By.xpath("(//span[@class='wcicon-status-cancelled'])"));
        Assert.assertTrue(errorMsg.isDisplayed(),"'This field is required.' uyarısı görünmeli.");
    }

    @DataProvider(name = "invalidPriceData")
    public Object[][] invalidPriceData() {
        return new Object[][] {
                {"Ürün1", "abc"},
                {"Ürün2", "$$"},
                {"Ürün3", " "},
                {"Ürün4", "-50"}
        };
    }

    @Test(dataProvider = "invalidPriceData", description = "TC_NEG_02: Price alanına geçersiz veri girildiğinde sistemin hata verdiğini kontrol etmek")
    public void TC_NEG_02_invalidPriceFail(String title, String priceInput) {
        ExtentReportsListener.extentTestInfo("Allover Commerce anasayfasina gidilir");
        ExtentReportsListener.extentTestInfo("Vendor hesabıyla sisteme giriş yapılmış");
        ExtentReportsListener.extentTestInfo("Simple Product varsayılan olarak gelmeli");
        ProductPage productPage = new ProductPage();

        ExtentReportsListener.extentTestInfo("Virtual seçilir");
        JSUtils.JSclickWithTimeout(productPage.virtualCheckbox);
        ExtentReportsListener.extentTestInfo("Product Title girilir");
        productPage.productTitleBox.sendKeys(title);
        ExtentReportsListener.extentTestInfo("Price yazılır");
        productPage.priceBox.sendKeys(priceInput);
        ExtentReportsListener.extentTestInfo("Sale Price yazılır");
        productPage.salePriceBox.sendKeys("80");
        ActionsUtils.scrollDown();
        ExtentReportsListener.extentTestInfo("Kategori seçilir");
        JSUtils.JSclickWithTimeout(productPage.clothingCategoryCheckbox);

        WaitUtils.waitFor(2);
        ActionsUtils.scrollEnd();
        ExtentReportsListener.extentTestInfo("Submit butonuna tıklanır");
        JSUtils.JSclickWithTimeout(productPage.submitButton);

        WaitUtils.waitFor(2);
        ExtentReportsListener.extentTestInfo("Price must be numeric” ya da benzeri bir hata mesajı gösterilmeli, ürün eklenmemelidir.");
        WebElement errorMsg = Driver.getDriver().findElement(By.xpath("(//span[@class='wcicon-status-cancelled'])[1]"));
        Assert.assertTrue(errorMsg.isDisplayed());
    }

    @DataProvider(name = "noCategoryData")
    public Object[][] NoCategoryData() {
        return new Object[][]{
                {"Ürün Kategori Yok", "90", "80"},
                {"Deneme Ürün", "120", "100"}
        };
    }

    @Test(dataProvider = "noCategoryData", description = "TC_NEG_03: Kategori seçilmeden ürün eklenemediğini kontrol etmek")
    public void TC_NEG_03_noCategoryFail(
            String title, String price, String salePrice) {
        ExtentReportsListener.extentTestInfo("Allover Commerce anasayfasina gidilir");
        ExtentReportsListener.extentTestInfo("Vendor hesabıyla sisteme giriş yapılmış");
        ExtentReportsListener.extentTestInfo("Simple Product varsayılan olarak gelmeli");
        ProductPage productPage = new ProductPage();

        ExtentReportsListener.extentTestInfo("Virtual seçilir");
        JSUtils.JSclickWithTimeout(productPage.virtualCheckbox);
        ExtentReportsListener.extentTestInfo("Product Title girilir");
        productPage.productTitleBox.sendKeys(title);
        ExtentReportsListener.extentTestInfo("Price yazılır");
        productPage.priceBox.sendKeys(price);
        ExtentReportsListener.extentTestInfo("Sale Price yazılır");
        productPage.salePriceBox.sendKeys(salePrice);

        ExtentReportsListener.extentTestInfo("Kategori seçilmez");
        WaitUtils.waitFor(2);
        ActionsUtils.scrollEnd();
        ExtentReportsListener.extentTestInfo("Submit butonuna tıklanır");
        JSUtils.JSclickWithTimeout(productPage.submitButton);

        WaitUtils.waitFor(2);
        ExtentReportsListener.extentTestInfo("“Please select a category” gibi bir uyarı gösterilmeli, ürün kaydı gerçekleşmemelidir.");
        WebElement errorMsg = Driver.getDriver().findElement(By.xpath("(//span[@class='wcicon-status-cancelled'])[3]"));
        Assert.assertTrue(errorMsg.isDisplayed(), "'Please select a category' uyarısı görünmeli.");
    }

    @DataProvider(name = "invalidSalePriceData")
    public Object[][] invalidSalePriceData() {
        return new Object[][]{
                {"Geçersiz Fiyat 1", "100", "150"},
        };
    }

    @Test(dataProvider = "invalidSalePriceData", description = "TC_NEG_04: Sale Price değeri, Price değerinden yüksek olduğunda sistem uyarı vermelidir"
            +" "+ "BUG: Sale price > price allowed")
    public void TC_NEG_04_salePriceFail(
            String title, String price, String salePrice) {
        ExtentReportsListener.extentTestInfo("Allover Commerce anasayfasina gidilir");
        ExtentReportsListener.extentTestInfo("Vendor hesabıyla sisteme giriş yapılmış");
        ExtentReportsListener.extentTestInfo("Simple Product varsayılan olarak gelmeli");
        ProductPage productPage = new ProductPage();

        ExtentReportsListener.extentTestInfo("Virtual seçilir");
        JSUtils.JSclickWithTimeout(productPage.virtualCheckbox);
        ExtentReportsListener.extentTestInfo("Product Title girilir");
        productPage.productTitleBox.sendKeys(title);
        ExtentReportsListener.extentTestInfo("Price yazılır");
        productPage.priceBox.sendKeys(price);
        ExtentReportsListener.extentTestInfo("Sale Price yazılır");
        productPage.salePriceBox.sendKeys(salePrice);
        ActionsUtils.scrollDown();
        ExtentReportsListener.extentTestInfo("Kategori seçilir");
        JSUtils.JSclickWithTimeout(productPage.clothingCategoryCheckbox);

        WaitUtils.waitFor(2);
        ActionsUtils.scrollEnd();
        ExtentReportsListener.extentTestInfo("Submit butonuna tıklanır");
        JSUtils.JSclickWithTimeout(productPage.submitButton);

        WaitUtils.waitFor(2);
        ExtentReportsListener.extentTestInfo("BUG: Satış fiyatı, normal fiyattan büyük olduğunda hata mesajı çıkmalı ama çıkmadı.");
        ExtentReportsListener.addScreenshotToReport("BUG: Satış fiyatı, normal fiyattan büyük olduğunda hata mesajı çıkmalı ama çıkmadı.");
        List<WebElement> errorMsg = Driver.getDriver().findElements(By.xpath("//span[contains(text(),'Sale price must be less')]"));
        Assert.assertTrue(!errorMsg.isEmpty(), "BUG: Satış fiyatı, normal fiyattan büyük olduğunda hata mesajı çıkmalı ama çıkmadı.");
    }



}
