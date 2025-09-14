package Tests.US_14;

import Pages.HomeVendorPage;
import Pages.StoreManagerPage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.*;

public class TC_03 {
    @BeforeClass
    public void setUp() {
        LoginUtils.loginAndNavigateToMyAccount(ConfigReader.getProperty("vendorUsername"), ConfigReader.getProperty("vendorPassword"));
    }

    @Test(description = "US14 - TC03 Urun baslik ve aciklamalarinin girilebildigini dogrulama")
    public void test01() throws InterruptedException {
        HomeVendorPage homeVendorPage = new HomeVendorPage();
        StoreManagerPage storeManagerPage = new StoreManagerPage();

        homeVendorPage.storeManagerMenu.click();
        ActionsUtils.scrollDown();
        Assert.assertTrue(homeVendorPage.storeManagerTitle.isDisplayed());

        ActionsUtils.scrollDown();
        ReusableMethods.scroll(storeManagerPage.products);
        storeManagerPage.products.click();
        Assert.assertTrue(storeManagerPage.productsMenu.isDisplayed());

        storeManagerPage.addNewButton.click();
        ActionsUtils.scrollDown();
        Assert.assertTrue(storeManagerPage.addProductTitle.isDisplayed());

        storeManagerPage.productTitle.sendKeys(ConfigReader.getProperty("addedProductTitle"));
        storeManagerPage.productImage.click();
        storeManagerPage.inputFileElement.sendKeys("C:\\Users\\VOHU MANAH\\Desktop\\kus.jpg");
        ReusableMethods.visibleWait(storeManagerPage.selectButton, 3);
        ReusableMethods.waitForSecond(3);
        ReusableMethods.click(storeManagerPage.selectButton);


        storeManagerPage.miniProductImage.click();
        ReusableMethods.waitForSecond(2);
        ActionsUtils.pressTab();
        ActionsUtils.pressTab();
        ActionsUtils.pressTab();
        ActionsUtils.pressTab();
        ActionsUtils.pressTab();
        ActionsUtils.pressTab();
        ActionsUtils.pressEnter();
        ReusableMethods.visibleWait(storeManagerPage.addToGalleryButton, 3);
        ReusableMethods.click(storeManagerPage.addToGalleryButton);
        WaitUtils.waitFor(2);


        BrowserUtils.frameSwitchTo(0);
        storeManagerPage.shortDescriptionBox.sendKeys("TC_03 deneme shortDescriptionBox");
        BrowserUtils.switchToDefault();
        BrowserUtils.frameSwitchTo(1);
        storeManagerPage.descriptionBox.sendKeys("TC_03 deneme descriptionBox");
        BrowserUtils.switchToDefault();
        ExtentReportsListener.addScreenshotToReport("ShortDescription ve Description ");

        ReusableMethods.visibleWait(storeManagerPage.categoriesCheckBox,2);
        storeManagerPage.categoriesCheckBox.click(); //@
        JSUtils.JSscrollIntoView(storeManagerPage.productBrandsCheckBox);
        storeManagerPage.productBrandsCheckBox.click(); //Apple


        ReusableMethods.click(storeManagerPage.submitProductButton);
        Assert.assertTrue(storeManagerPage.publishedTitle.isDisplayed());

        storeManagerPage.products.click();
        ExtentReportsListener.addScreenshotToReport("Categories ve Product brands isaretlenen checkboxlar doğru secildigi kontrol edilir.");

        storeManagerPage.viewLastAddedProduct.click();
        ReusableMethods.waitForSecond(2);
        BrowserUtils.windowSwitchTo(1);
        ReusableMethods.waitForSecond(2);
        ExtentReportsListener.addScreenshotToReport("Urun basligi ,ShortDescription ve Description urun bilgilerinde goruldugu kontrol edilir.");
        Driver.quitDriver();








    }
}
