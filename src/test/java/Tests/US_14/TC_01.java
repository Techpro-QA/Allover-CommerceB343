package Tests.US_14;

import Pages.HomeVendorPage;
import Pages.StoreManagerPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.*;

import java.awt.geom.RectangularShape;
import java.util.List;

public class TC_01 {

    @BeforeClass
    public void setUp() {
        LoginUtils.loginAndNavigateToMyAccount(ConfigReader.getProperty("vendorUsername"), ConfigReader.getProperty("vendorPassword"));
    }

    @Test(description = "US14 - TC01 Urun turu seceneklerini dogrulama")
    public void test01() throws InterruptedException {

        HomeVendorPage homeVendorPage = new HomeVendorPage();
        StoreManagerPage storeManagerPage = new StoreManagerPage();

        ExtentReportsListener.extentTestInfo("Add Product a ulasıldigini dogrulama");
        homeVendorPage.storeManagerMenu.click();
        Assert.assertTrue(homeVendorPage.storeManagerTitle.isDisplayed());

        storeManagerPage.products.click();
        Assert.assertTrue(storeManagerPage.productsMenu.isDisplayed());

        storeManagerPage.addNewButton.click();
        Assert.assertTrue(storeManagerPage.addProductTitle.isDisplayed());


        WebElement dropDownMenu = storeManagerPage.dropDownMenu;
        storeManagerPage.dropDownMenu.click();
        ExtentReportsListener.addScreenshotToReport("Dropdown menude urun turu seceneklerinin gorunurlugunu dogrulama.");
        WaitUtils.waitForVisibility(dropDownMenu, 3);


        Select select = new Select(dropDownMenu);
        List<WebElement> dropDownMenuList = select.getOptions();

        for (WebElement w : dropDownMenuList) {
            Assert.assertTrue(w.isDisplayed(), "Secenek gorunur degil" + w.getText().trim());
        }

        ExtentReportsListener.extentTestInfo("Simple Product form kontrolu");
        select.selectByVisibleText("Simple Product");
        Assert.assertTrue(!storeManagerPage.variationsHeadForProduct.isDisplayed()
                & !storeManagerPage.groupedHeadForProduct.isDisplayed()
                & storeManagerPage.shippingHeadForProduct.isDisplayed());

        ExtentReportsListener.extentTestInfo("Variable Product form kontrolu");
        select.selectByVisibleText("Variable Product");
        Assert.assertTrue(storeManagerPage.variationsHeadForProduct.isDisplayed());

        ExtentReportsListener.extentTestInfo("Grouped Product form kontrolu");
        select.selectByVisibleText("Grouped Product");
        Assert.assertTrue(storeManagerPage.groupedHeadForProduct.isDisplayed());

        ExtentReportsListener.extentTestInfo("External/Affiliate Product form kontrolu");
        select.selectByVisibleText("External/Affiliate Product");
        Assert.assertTrue(!storeManagerPage.groupedHeadForProduct.isDisplayed() & !storeManagerPage.shippingHeadForProduct.isDisplayed());

        Driver.quitDriver();

    }
}
