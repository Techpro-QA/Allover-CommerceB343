package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.util.List;

public class WishListPage {

    public WishListPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    // ==== Genel Kullanılan Elemanlar ====
    @FindBy(id = "menu-item-12993")
    public WebElement menOfOption;

    @FindBy(xpath = "//i[@class='w-icon-heart']")
    public WebElement iconHeart;

    @FindBy(xpath = "//input[@name='s']")
    public WebElement searchBox;

    @FindBy(xpath = "//h2[contains(text(),'Wishlist')]")
    public WebElement wishlistPageTitle;

    @FindBy(xpath = "//button[@class=\"mfp-close\"]")
    public WebElement escButton;

    // ==== Wishlist / Ürün Elemanları ====
    @FindBy(xpath = "//a[@data-product-id='31300']")
    public WebElement addToWishList;

    @FindBy(xpath = "(//span[@class='wishlist-in-stock'])[1]")
    public WebElement stockStatus;

    @FindBy(xpath = "//a[.='Read more']")
    public WebElement readMoreButton;

    @FindBy(xpath = "//button[@data-product='31300']")
    public WebElement quickViewButton;

    @FindBy(xpath = "//p[.='This product is currently out of stock and unavailable.']")
    public WebElement outOfStockText;

    @FindBy(xpath = "(//div[@data-fragment-ref=\"19895\"])[1]")
    public WebElement chessAboveIconOfWishList;

    @FindBy(xpath = "//h2[.='Cargo Pants']")
    public WebElement cargoPantsTitle;

    @FindBy(xpath = "//h2[@class=\"product_title entry-title\"]")
    public WebElement jacketTitle;

    // ==== Add to Cart / Sepet İşlemleri ====
    @FindBy(xpath = "//button[@name='add-to-cart']")
    public WebElement addToCartButton;

    @FindBy(xpath = "//a[@class='cart-toggle']")
    public WebElement cartButton;

    @FindBy(xpath = "//*[.='Shopping Cart']")
    public WebElement shoppingCart;

    @FindBy(xpath = "//a[.='Checkout']")
    public WebElement checkoutButton;

    @FindBy(xpath = "//h3[.='Billing details']")
    public WebElement billingDetails;

    @FindBy(xpath = "//th[.='Product']")
    public WebElement productText;



    // ==== Ürün Detayları (Sepette Görünen İsimler) ====
    @FindBy(xpath = "//span[.='Men’s Clothing']")
    public WebElement jacketInCart;

    @FindBy(xpath = "//span[.='Chess']")
    public WebElement chessInCart;

    @FindBy(xpath = "//span[.='Cargo Pants']")
    public WebElement cargoPantsInCart;

    @FindBy(xpath = "//a[@href=\"?add_to_wishlist=13206\"]")
    public WebElement jacket;

    @FindBy(xpath = "(//a[@href=\"https://allovercommerce.com/urun/chess/\"])[3]")
    public WebElement chess;

    @FindBy(xpath = "(//a[@href=\"https://allovercommerce.com/urun/cargo-pants/\"])[1]")
    public WebElement cargoPants;

    @FindBy(xpath = "//a[@href=\"?add_to_wishlist=21675\"]") public
    WebElement CargoPantsAboveIconOfWishList;

    // ==== QuickView Butonları (liste halinde) ====
    @FindBy(xpath = "//button[@class=\"btn btn-quickview btn-outline btn-default btn-rounded btn-sm mr-lg-2\"]")
    public List<WebElement> quickViewButtons;




}
