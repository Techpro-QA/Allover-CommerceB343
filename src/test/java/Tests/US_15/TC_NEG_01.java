package Tests.US_15;

import Pages.*;

import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.*;

public class TC_NEG_01 {

    @Test(description = "Vendor hesabıyla giriş yaparak, Shipping kısmına ürün eklemeye başlanır")
    public void testAddShipping() {

               // 1- Go to AlloverCommerce page
        Driver.getDriver().get(ConfigReader.getProperty("alloverCommerceUrl"));
        HomePage homePage = new HomePage();
        ProductPage productPage = new ProductPage();
        MyAccountPage myAccountPage = new MyAccountPage();
        StoreManagerPage storeManagerPage = new StoreManagerPage();

        // 2- Sign in
        homePage.homeSignIn.click();
        homePage.usernameOrEmailAddressTextBox.sendKeys(ConfigReader.getProperty("vendorEmail"));
        homePage.passwordTextBox.sendKeys(ConfigReader.getProperty("vendorPassword"));
        homePage.signInButton.click();
        WaitUtils.waitFor(2);
        ExtentReportsListener.extentTestInfo("Vendor hesabıyla sisteme giriş yapıldı.");
        ReusableMethods.addScreenShotToReport();
        // 3- My Account → Store Manager → Products → Add New
        JSUtils.JSscrollIntoView(homePage.textMyAccount);
        WaitUtils.waitForVisibility(homePage.textMyAccount, 2);
        JSUtils.JSclickWithTimeout(homePage.textMyAccount);

        myAccountPage.storeManagerMenu.click();
        storeManagerPage.productsMenu.click();
        storeManagerPage.addNewButton.click();

        // 4- Verify Shipping section visibility
        ActionsUtils.scrollDown();
        JSUtils.JSscrollIntoView(productPage.shippingMenu);
        WaitUtils.waitForVisibility(productPage.shippingMenu, 2);
        JSUtils.JSclickWithTimeout(productPage.shippingMenu);

        // 5- Verify Add Product page
        JSUtils.JSscrollIntoView(productPage.addProductText);
        Assert.assertTrue(productPage.addProductText.isDisplayed());

        // 6- Virtual CheckBox

        WaitUtils.waitFor(3);
        ActionsUtils.scrollHome();
        JSUtils.JSscrollIntoView(productPage.virtualCheckbox);
        if (!productPage.virtualCheckbox.isSelected()) {
            productPage.virtualCheckbox.click();
        }
        ActionsUtils.pressTab();

        // 7- Enter product information
        productPage.productTitle.sendKeys("Pencil");
        productPage.priceBox.sendKeys("3");
        productPage.salePriceBox.sendKeys("4.5");

        // 8- Upload product image
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
        ReusableMethods.takeScreenShot();
        // 9- Select categories
        if (!productPage.educationCheckBox.isSelected()) {
            JSUtils.JSclickWithTimeout(productPage.educationCheckBox);
        }

        ReusableMethods.visibleWait(productPage.giftIdeasCheckBox, 3);
        WaitUtils.waitFor(3);

        if (!productPage.giftIdeasCheckBox.isSelected()) {
            productPage.giftIdeasCheckBox.click();
        }
        // 10- Submit
        JSUtils.JSclickWithTimeout(productPage.submitButton);
    }
}
