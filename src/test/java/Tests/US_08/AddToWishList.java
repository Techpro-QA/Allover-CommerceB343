package Tests.US_08;

import org.testng.annotations.Listeners;
import pages.WishListPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.*;

@Listeners(ExtentReportsListener.class)
public class AddToWishList{

    WishListPage wishList = new WishListPage();
    Actions action = new Actions(Driver.getDriver());

    @Test(description = "Kullanıcı üç ürünü (Jacket, Chess, Cargo Pants) favorilere ekleyebilmeli")
    public void testUS_08() {

        // 1- Navigate to alloverCommerce.com
        Driver.getDriver().get(ConfigReader.getProperty("alloverCommerceUrl"));
        ReusableMethods.waitForSecond(3);
        // 2- Select Jacket product and add to wishlist
        action.scrollToElement(wishList.jacket).perform();
        wishList.jacket.click();
        ReusableMethods.waitForSecond(2);
        // 3- Select Chess product and add to wishlist
        ReusableMethods.scroll(wishList.chess);
        action.moveToElement(wishList.chess).perform();
        wishList.chessAboveIconOfWishList.click();

        // 4- Select Cargo Pants product and add to wishlist
        ReusableMethods.scroll(wishList.menOfOption);
        ReusableMethods.waitForSecond(2);
        wishList.menOfOption.click();
        action.moveToElement(wishList.cargoPants).perform();
        wishList.CargoPantsAboveIconOfWishList.click();

        // 5- Navigate to Wishlist page
        wishList.iconHeart.click();
        Assert.assertTrue(wishList.wishlistPageTitle.isDisplayed(), "Wishlist sayfası açılmadı!");
        ReusableMethods.takeScreenShot();
        // 6- Add all wishlist products to cart
        for (WebElement w : wishList.quickViewButtons) {
            w.click();
            wishList.addToCartButton.click();
            wishList.escButton.click();
            ReusableMethods.scroll(wishList.productText);
        }
        ReusableMethods.waitForSecond(5);
        ReusableMethods.scroll(wishList.searchBox);
        ReusableMethods.waitForSecond(3);
        // 7- Navigate to Cart
        wishList.cartButton.click();
        Assert.assertTrue(wishList.shoppingCart.isDisplayed(), "Sepet açılmadı!");
        ReusableMethods.takeScreenShot();
        // 8- Verify products are listed in Cart
        Assert.assertTrue(wishList.jacketInCart.isDisplayed(),"Cargo Pants sepette yok!");
        Assert.assertTrue(wishList.cargoPantsInCart.isDisplayed(), "Cargo Pants sepette yok!");
        Assert.assertTrue(wishList.chessInCart.isDisplayed(), "Chess sepette yok!");
        // 9- Navigate to Checkout
        wishList.checkoutButton.click();
        Assert.assertTrue(wishList.billingDetails.isDisplayed(), "Checkout sayfası açılmadı!");
        ReusableMethods.takeScreenShot();
    }
}