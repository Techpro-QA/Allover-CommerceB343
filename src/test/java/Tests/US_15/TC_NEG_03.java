package Tests.US_15;

import Pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import utilities.*;

public class TC_NEG_03 {
    @Test(description = "Geçersiz veri girildiğinde ürün eklenemediğini kontrol edilir.")
    public void testVendorInventory() {

        // 1-Go to AlloverCommerce sayfasına gidilir
      
        // 1-Go to AlloverCommerce sayfasına gidilir
        ExtentReportsListener.extentTestInfo("Allover Commerce sayfasina gidilir");
        Driver.getDriver().get(ConfigReader.getProperty("alloverCommerceUrl"));
        HomePage homePage = new HomePage();
        ProductPage productPage = new ProductPage();
        MyAccountPage myAccountPage = new MyAccountPage();
        StoreManagerPage storeManagerPage = new StoreManagerPage();

        // 2- Sign in with vendor account
        ExtentReportsListener.extentTestInfo("Kullanici adiyla giris yapabilmek icin Sign in butonuna tiklanir");
        homePage.homeSignIn.click();

        ExtentReportsListener.extentTestInfo("Username alanina kayitli vendor hesabiyla gecerli bilgiler girilir");
        homePage.usernameOrEmailAddressTextBox.sendKeys(ConfigReader.getProperty("vendorEmail"));
        homePage.passwordTextBox.sendKeys(ConfigReader.getProperty("vendorPassword"));
        homePage.signInButton.click();
        WaitUtils.waitFor(2);
        ExtentReportsListener.extentTestInfo("Vendor hesabıyla sisteme giriş yapıldı.");
        ReusableMethods.addScreenShotToReport();

        JSUtils.JSscrollIntoView(homePage.textMyAccount);
        WaitUtils.waitForVisibility(homePage.textMyAccount, 2);
        JSUtils.JSclickWithTimeout(homePage.textMyAccount);
        // 3- My Account → Store Manager → Products → Add New

        myAccountPage.storeManagerMenu.click();
        storeManagerPage.productsMenu.click();
        storeManagerPage.addNewButton.click();

        ReusableMethods.visibleWait(productPage.addNewButton, 3);
        ReusableMethods.scroll(productPage.addNewButton);

        ActionsUtils.pressTab();
        productPage.productTitle.sendKeys("Leather Jacket");

        // 4- Upload product image
        ExtentReportsListener.extentTestInfo("Ürün görseli eklenir");
        productPage.uploadFile.click();
        productPage.uploadFiles.click();
        BrowserUtils.dropdownSelectByVisibleText(productPage.filterMediaOptions, "July 2025");
        productPage.emoji.click();
        productPage.selectButton.click();

        JSUtils.JSclickWithTimeout(productPage.subUploadFile);
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
        WaitUtils.waitFor(2);
        productPage.addToGallery.click();
        JSUtils.JSclickWithTimeout(productPage.mediaClose);
        // 5- Select categories

        if (!productPage.educationCheckBox.isSelected()) {
            JSUtils.JSclickWithTimeout(productPage.educationCheckBox);
        }

        // Inventory
        ReusableMethods.scroll(productPage.inventoryMenu);
        ReusableMethods.waitForSecond(2);

        productPage.inventoryMenu.click();
        productPage.manageStockCheckBox.click();
        productPage.numberOfSKU.sendKeys("{SKU}");
        productPage.stockQty.sendKeys("120");
        ReusableMethods.ddmIndex(productPage.allowBackorders, 2);
        if (!productPage.soldIndividuallyCheckBox.isSelected()) {
            productPage.soldIndividuallyCheckBox.click();
        }
        // Submit
        productPage.submitButton.click();
        WaitUtils.waitFor(5);
        Assert.assertTrue(productPage.skuUniqueMessage.getText().contains("Product SKU must be unique."));
        WaitUtils.waitFor(3);
        ReusableMethods.addScreenShotToReport();
    }
//----------------------------------------------------------------
    @Test(description = "SKU değeri boş bırakıldağında ve stok miktarı negatif girildiğinde ürün eklenemediğini kontrol edilir.")
    public void testSkuBosVeStockQtyNegatif() {

        // 1-Go to AlloverCommerce sayfasına gidilir
        ExtentReportsListener.extentTestInfo("Allover Commerce sayfasina gidilir");
        Driver.getDriver().get(ConfigReader.getProperty("alloverCommerceUrl"));
        HomePage homePage = new HomePage();
        ProductPage productPage = new ProductPage();
        MyAccountPage myAccountPage = new MyAccountPage();
        StoreManagerPage storeManagerPage = new StoreManagerPage();

        // 2- Sign in with vendor account
        ExtentReportsListener.extentTestInfo("Kullanici adiyla giris yapabilmek icin Sign in butonuna tiklanir");
        homePage.homeSignIn.click();

        ExtentReportsListener.extentTestInfo("Username alanina kayitli vendor hesabiyla gecerli bilgiler girilir");
        homePage.usernameOrEmailAddressTextBox.sendKeys(ConfigReader.getProperty("vendorEmail"));
        homePage.passwordTextBox.sendKeys(ConfigReader.getProperty("vendorPassword"));
        homePage.signInButton.click();
        WaitUtils.waitFor(2);
        ExtentReportsListener.extentTestInfo("Vendor hesabıyla sisteme giriş yapıldı.");
        ReusableMethods.addScreenShotToReport();

        JSUtils.JSscrollIntoView(homePage.textMyAccount);
        WaitUtils.waitForVisibility(homePage.textMyAccount, 2);
        JSUtils.JSclickWithTimeout(homePage.textMyAccount);
        // 3- My Account → Store Manager → Products → Add New

        myAccountPage.storeManagerMenu.click();
        storeManagerPage.productsMenu.click();
        storeManagerPage.addNewButton.click();

        ReusableMethods.visibleWait(productPage.addNewButton, 3);
        ReusableMethods.scroll(productPage.addNewButton);

        ActionsUtils.pressTab();
        productPage.productTitle.sendKeys("Leather Jacket");

        // 4- Upload product image
        ExtentReportsListener.extentTestInfo("Ürün görseli eklenir");
        productPage.uploadFile.click();
        productPage.mediaLibraryTab.click();
        BrowserUtils.dropdownSelectByVisibleText(productPage.filterMediaOptions, "July 2025");
        productPage.emoji.click();
        productPage.selectButton.click();

        JSUtils.JSclickWithTimeout(productPage.subUploadFile);
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
        WaitUtils.waitFor(2);
        productPage.addToGallery.click();
        JSUtils.JSclickWithTimeout(productPage.mediaClose);
        // 5- Select categories

        if (!productPage.educationCheckBox.isSelected()) {
            JSUtils.JSclickWithTimeout(productPage.educationCheckBox);
        }

        // Inventory
        ReusableMethods.scroll(productPage.inventoryMenu);
        ReusableMethods.waitForSecond(2);

        productPage.inventoryMenu.click();
        productPage.manageStockCheckBox.click();
        productPage.stockQty.sendKeys("-120");
        ReusableMethods.ddmIndex(productPage.allowBackorders, 2);
        if (!productPage.soldIndividuallyCheckBox.isSelected()) {
            productPage.soldIndividuallyCheckBox.click();
        }
        // Submit
        ReusableMethods.scrollEnd();
        productPage.submitButton.click();
        WaitUtils.waitFor(5);
        Assert.assertFalse(productPage.successMessage.isDisplayed());
        WaitUtils.waitFor(3);
        ReusableMethods.addScreenShotToReport();

    }
}
