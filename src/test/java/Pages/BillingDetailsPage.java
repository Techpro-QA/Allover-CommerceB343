package Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class BillingDetailsPage {
    public BillingDetailsPage() {
        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy(id = "billing_first_name")
    public WebElement firstNameBox ;

    @FindBy(id= "billing_last_name")
    public WebElement lastNameBox ;

    @FindBy(id="billing_address_1")
    public  WebElement streetAddressBox;

    @FindBy(id="payment_method_bacs")
    public  WebElement bacsCase;

     @FindBy(id="payment_method_cod")
    public  WebElement codCase;

    @FindBy(id="place_order")
    public  WebElement placeOrderButton;

   @FindBy(xpath = "//i[@class='fas fa-check']")
    public WebElement orderReceivedMessage ;

   @FindBy(xpath = "//li[@data-id='billing_first_name']")
    public WebElement firstNameFieldError;

   @FindBy(xpath = "//*[@id=\"main\"]/div/div/div/div/div/form[2]/div[1]/ul/li")
    public WebElement checkBoxErrorMessage;


}
