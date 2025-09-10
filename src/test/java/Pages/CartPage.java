package Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class CartPage {
    public CartPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//input[@type='number']")
    public WebElement quantityInput;

    @FindBy(xpath = "(//i[@class='w-icon-cart'])[1]")
    public WebElement cartButton;

    @FindBy(xpath = "//*[@class=' alert alert-simple alert-icon alert-close-top alert-danger']")
    public WebElement invalidCouponMessage;

    @FindBy(xpath = "//*[@class=' alert alert-simple alert-icon alert-close-top alert-danger']")
    public WebElement expiredCouponMessage;

    @FindBy(xpath = "//button[.='Clear cart']")
    public WebElement clearCartButton;

}
