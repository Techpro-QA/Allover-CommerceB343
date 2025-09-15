package Tests.US_15;

import Pages.*;
import com.github.javafaker.Faker;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.*;

public class Vendor2Test {


    @Test(description = "US_15 Vendor, ürün ekleme sayfasına erişim sağlamalı ve ürün bilgilerini düzenleyebilmelidir.")
    public void testUS_15() {
        Driver.getDriver().get(ConfigReader.getProperty("alloverCommerceUrl"));
        HomePage homePage = new HomePage();
        ProductPage productPage = new ProductPage();
        MyAccountPage myAccountPage = new MyAccountPage();
        StoreManagerPage storeManagerPage = new StoreManagerPage();

      // Login
        homePage.homeSignIn.click();
        homePage.usernameOrEmailAddressTextBox.sendKeys(ConfigReader.getProperty("vendorEmail"));
        homePage.passwordTextBox.sendKeys(ConfigReader.getProperty("vendorPassword"));
        homePage.signInButton.click();
        WaitUtils.waitFor(2);

        // My Account
        JSUtils.JSscrollIntoView(homePage.textMyAccount);
        WaitUtils.waitForVisibility(homePage.textMyAccount, 2);
        JSUtils.JSclickWithTimeout(homePage.textMyAccount);

        // Store Manager > Products > Add New
        myAccountPage.storeManagerMenu.click();
        storeManagerPage.productsMenu.click();
        storeManagerPage.addNewButton.click();

        // Add Product sayfası doğrulama
        Assert.assertTrue(productPage.addProductText.isDisplayed());
        ReusableMethods.addScreenShotToReport();

        //Verify menu section visibility
        Assert.assertTrue(productPage.inventoryMenu.isDisplayed());
        Assert.assertTrue(productPage.shippingMenu.isDisplayed());
        Assert.assertTrue(productPage.attributeMenu.isDisplayed());
        ReusableMethods.takeScreenShot();

        // Product Information
        ReusableMethods.visibleWait(productPage.addNewButton, 3);
        ReusableMethods.scroll(productPage.addNewButton);
        ActionsUtils.pressTab();
        productPage.productTitle.sendKeys("Leather Jacket");

        // Add Product image
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
        ReusableMethods.takeScreenShot();

        // Select categories
        if (!productPage.educationCheckBox.isSelected()) {
            JSUtils.JSclickWithTimeout(productPage.educationCheckBox);
        }

        // Inventory
        ReusableMethods.scroll(productPage.inventoryMenu);
        ReusableMethods.waitForSecond(2);
        productPage.inventoryMenu.click();
        //SKU unique bir değer alması gerektiği için Faker classı kullanıldı.
        Faker faker = new Faker();
        String sku = faker.number().digits(6);
        productPage.numberOfSKU.sendKeys("SKU"+sku);
        productPage.manageStockCheckBox.click();
        productPage.stockQty.sendKeys(sku);
        ReusableMethods.ddmIndex(productPage.allowBackorders, 2);
        productPage.soldIndividuallyCheckBox.click();
        ReusableMethods.addScreenShotToReport();

        // Shipping
        productPage.shippingMenu.click();
        productPage.weight.sendKeys("43");
        ActionsUtils.pressTab();
        productPage.length.sendKeys("34");
        ActionsUtils.pressTab();
        productPage.width.sendKeys("34");
        ActionsUtils.pressTab();
        productPage.height.sendKeys("34");
        ActionsUtils.pressTab();
        ActionsUtils.pressTab();
        ReusableMethods.ddmIndex(productPage.processingTime, 2);
        ReusableMethods.takeScreenShot();

        // Attributes
        ExtentReportsListener.extentTestInfo("Attributes alanında renk ve beden seçenekleri girilir.");
        productPage.attributeMenu.click();
        if (!productPage.color.isSelected()) {
            productPage.color.click();
        }

        productPage.colorOptions.click();
        productPage.backArrow1.click();

        if (!productPage.visiblePageCheckBox.isSelected()) {
            productPage.visiblePageCheckBox.click();
        }

        if (!productPage.sizeCheckBox.isSelected()) {
            productPage.sizeCheckBox.click();
        }

        productPage.sizeOptions.click();
        productPage.backArrow2.click();
        // Submit
        ReusableMethods.scrollEnd();
        productPage.submitButton.click();
        WaitUtils.waitFor(5);
        Assert.assertTrue(productPage.successMessage.getText().contains("Product Successfully Published"));
        WaitUtils.waitFor(3);
        ReusableMethods.addScreenShotToReport();
      	Driver.quitDriver();

    }
}
