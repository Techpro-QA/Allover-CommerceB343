package Tests.US_14;

import Pages.HomeVendorPage;
import Pages.StoreManagerPage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.*;

public class TC_02 {

    @BeforeClass
    public void setUp() {
        LoginUtils.loginAndNavigateToMyAccount(ConfigReader.getProperty("vendorUsername"), ConfigReader.getProperty("vendorPassword"));
    }

    @Test(description = "US14 - TC02 Sisteme basarili bir sekilde urun fotografi yuklenebildigini dogrulama")
    public void test01() throws InterruptedException {

        StoreManagerPage storeManagerPage = new StoreManagerPage();
        HomeVendorPage homeVendorPage = new HomeVendorPage();

        homeVendorPage.storeManagerMenu.click();
        Assert.assertTrue(homeVendorPage.storeManagerTitle.isDisplayed());

        ReusableMethods.scroll(storeManagerPage.products);
        storeManagerPage.products.click();
        Assert.assertTrue(storeManagerPage.productsMenu.isDisplayed());

        storeManagerPage.addNewButton.click();
        Assert.assertTrue(storeManagerPage.addProductTitle.isDisplayed());

        storeManagerPage.productImage.click();
        storeManagerPage.inputFileElement.sendKeys("C:\\Users\\VOHU MANAH\\Desktop\\kus.jpg");

        ReusableMethods.waitForSecond(4);
        Assert.assertEquals(storeManagerPage.imageDetailsTitle.getAttribute("value"),"kus");

        Driver.quitDriver();

    }
}
