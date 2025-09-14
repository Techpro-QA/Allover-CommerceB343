package Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.Driver;

public class ShopPage {
    public ShopPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//*[@id=\"main\"]/div/div/div/div[4]/ul/li[11]/div/figure/a/img")
    public WebElement productchoose;

    @FindBy(xpath = "//button[@name='add-to-cart']")
    public WebElement addToCartButton;

    @FindBy(xpath = "(//i[@class='w-icon-cart'])[1]")
    public WebElement cartButton;

    @FindBy(xpath = "//a[@class='button checkout wc-forward']")
    public WebElement checkOutButton;

    @FindBy(xpath = "//p[@class='woocommerce-mini-cart__empty-message empty-msg']")
    public WebElement emptyCartMessage;

    @FindBy(xpath = "//a[@class='woocommerce-button button wc-action-btn back-to-list btn btn-md btn-dark btn-rounded btn-icon-left']")
    public WebElement backToListButton;

    @FindBy(xpath = "//div[@data-product-id='14635']")
    public WebElement productOutOffStock;

    @FindBy(xpath = "//div[@data-product-id='35660']")
    public WebElement noPriceProduct;

    @FindBy(xpath = "//i[@class='fas fa-times']")
    public WebElement deleteIcons ;


}
