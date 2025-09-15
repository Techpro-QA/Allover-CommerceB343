package Tests.US_08;

import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.WishListPage;
import utilities.*;
@Listeners(ExtentReportsListener.class)

public class TC_NEG_01 {

    @Test(description = "TC-NEG-01-Stokta olmayan ürünü wishlist'ten sepete eklemeye çalışmak")
    public void US_08_NegTest01() {


        WishListPage wishListPage = new WishListPage();

        // 1- Go to alloverCommerce.com
        Driver.getDriver().get(ConfigReader.getProperty("alloverCommerceUrl"));
        // 2- Search 'Peluş oyuncak' in the search box
        wishListPage.searchBox.sendKeys("Peluş oyuncak",Keys.ENTER);

        // 3- Verify the product is out of stock
        Assert.assertTrue(wishListPage.outOfStockText.isDisplayed(), "Ürün stokta değil yazısı görünmedi!");
        ReusableMethods.addScreenShotToReport();

        // 4- Add product to wishlist
        JSUtils.JSclickWithTimeout(wishListPage.addToWishList);
        WaitUtils.waitFor(3);
        Assert.assertTrue(wishListPage.iconHeart.isDisplayed());
        ReusableMethods.addScreenShotToReport();

        // 5- Click on wishlist icon and verify stock status is not 'In Stock'
        ActionsUtils.scrollUp();
       // Assert.assertNotEquals(wishListPage.stockStatus.getText(), "In Stock","Stok durumu hatalı!");
       // ReusableMethods.addScreenShotToReport();
        WaitUtils.waitForVisibility(wishListPage.outOfStockText, 3);
        ReusableMethods.takeScreenShotOfWebElement(wishListPage.outOfStockText);

        // 6- Verify 'Add To Cart' button is not displayed for out-of-stock product
        ExtentReportsListener.extentTestInfo("Stokta olmayan ürünün 'Add To Cart' seçeneğinin görünmediği doğrulanır");
        ReusableMethods.scroll(wishListPage.iconHeart);
        WaitUtils.waitFor(2);
        wishListPage.iconHeart.click();
        Assert.assertTrue(wishListPage.readMoreButton.isDisplayed(),"'Add To Cart' butonu görünmüyor!");
        ReusableMethods.takeScreenShotOfWebElement(wishListPage.readMoreButton);
        ReusableMethods.addScreenShotToReport();
        // 7- Verify via Quick View
        ExtentReportsListener.extentTestInfo("QuickView Butonuna tıklanır ve Add To Cart seceneğinin görünmemelidir.Quick View butonuna tıklanır ve Add To Cart seçeneğinin görünmediği doğrulanır");
        wishListPage.quickViewButton.click();
        ActionsUtils.scrollDown();
        WaitUtils.waitFor(2);
        ReusableMethods.takeScreenShotOfWebElement(wishListPage.outOfStockText);
        WaitUtils.waitForVisibility(wishListPage.outOfStockText, 3);
        Assert.assertTrue(wishListPage.outOfStockText.isDisplayed(),"Quick View ekranında 'Add To Cart' butonu görünmedi!");
        ReusableMethods.addScreenShotToReport();

    }


}