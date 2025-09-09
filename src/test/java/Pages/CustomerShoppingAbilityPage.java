package Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.ConfigReader;
import utilities.Driver;

public class CustomerShoppingAbilityPage {

    public CustomerShoppingAbilityPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }


    @FindBy(xpath = "(//input[@type='search'])[1]")
    public WebElement searchBoxArea;

    @FindBy(xpath = "//button[@aria-label='Search Button']")
    public WebElement searchBoxButton;

    @FindBy(xpath = "//div[@data-product-id='62727']")
    public WebElement bookElement;

    @FindBy(xpath = "//div[@data-product-id='15766']")
    public WebElement basketballElement;

    @FindBy(xpath = "//div[@data-product-id='21945']")
    public WebElement pencilElement;

    @FindBy(xpath = "//div[@data-product-id='263443']")
    public WebElement laptopElement;

    @FindBy(xpath = "//div[@data-product-id='54240']")
    public WebElement bagElement;


    @FindBy(xpath = "//button[@value='62727']")
    public WebElement addCartBook;


    @FindBy(xpath = "//button[@value='263443']")
    public WebElement addCartLaptop;


    @FindBy(xpath = "//button[@value='54240']")
    public WebElement addCartBag;


    @FindBy(xpath = "//a[@class='cart-toggle']")
    public WebElement cartButton;


    @FindBy(xpath = "//a[@class='button wc-forward']")
    public WebElement viewCartButton;


    @FindBy(xpath = "(//td)[2]")
    public WebElement bookList;

    @FindBy(xpath = "(//td)[7]")
    public WebElement laptopList;

    @FindBy(xpath = "(//td)[12]")
    public WebElement bagList;


    @FindBy(xpath = "(//button[@title='Minus'])[1]")
    public WebElement bookMinus;


    @FindBy(xpath = "(//button[@title='Plus'])[2]")
    public WebElement laptopPlus;


    @FindBy(xpath = "//button[@name='update_cart']")
    public WebElement updateCartButton;


 //  @FindBy(xpath = "(//td)[10]")
 //  public WebElement laptopPrice;


 //  @FindBy(xpath = "(//td)[5]")
 //  public WebElement bookPrice;


    @FindBy(xpath = "(//bdi)[4]")
    public WebElement oneBookPrice;

    @FindBy(xpath = "(//bdi)[5]")
    public WebElement subtotalBookPrice;

    @FindBy(xpath = "(//bdi)[6]")
    public WebElement oneLaptopPrice;


    @FindBy(xpath = "(//bdi)[7]")
    public WebElement subtotalLaptopPrice;


    @FindBy(xpath = "//a[@class='checkout-button button alt wc-forward']")
    public WebElement proceedToCheckoutButton;



    @FindBy(xpath = "//h3[.='Billing details']")
    public WebElement billingDetailText;



    @FindBy(id = "billing_first_name")
    public WebElement firstNameArea;



    @FindBy(id = "billing_last_name")
    public WebElement lastNameArea;


    @FindBy(id = "billing_country")
    public WebElement countryArea;


    @FindBy(id = "billing_address_1")
    public WebElement streetAddressArea;


    @FindBy(id = "billing_city")
    public WebElement townArea;


    @FindBy(id = "billing_state")
    public WebElement stateArea;


    @FindBy(id = "billing_postcode")
    public WebElement zipCodeArea;


    @FindBy(id = "billing_phone")
    public WebElement phoneArea;


    @FindBy(id = "billing_email")
    public WebElement emailArea;


    @FindBy(xpath = "//li[@class=' alert alert-simple alert-icon alert-close-top alert-danger']")
    public WebElement noShippingText;


    @FindBy(id = "payment_method_cod")
    public WebElement payAtTheDoorButton;


    @FindBy(id = "place_order")
    public WebElement placeOrderButton;


}