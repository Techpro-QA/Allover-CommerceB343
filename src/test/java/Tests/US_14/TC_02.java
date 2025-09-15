package Tests.US_14;

import Pages.HomePage;
import Pages.MyAccountPage;
import Pages.StoreManagerPage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.*;

public class TC_02 {

    @BeforeClass
    public void setUp() {
        LoginVendorUtils.loginAndNavigateToMyAccount(ConfigReader.getProperty("vendorEmail"), ConfigReader.getProperty("vendorPassword"));
    }

    @Test(description = "US14 - TC02 Sisteme basarili bir sekilde urun fotografi yuklenebildigini dogrulama")
    public void test01(){

        StoreManagerPage storeManagerPage = new StoreManagerPage();
        MyAccountPage myAccountPage = new MyAccountPage();

        myAccountPage.storeManagerMenu.click();
        Assert.assertTrue(myAccountPage.storeManagerTitle.isDisplayed());

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
