package Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class CheckoutPage {
    public CheckoutPage() {

        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy(linkText = "Store Manager")
    public WebElement checkout;

    @FindBy(xpath = "//a[contains(@class, 'checkout-button')]")
    public WebElement proceedToCheckoutButton;

    @FindBy(xpath = "//a[.='Checkout']")
    public WebElement checkoutButton;

    // Billing details
    @FindBy(id = "billing_first_name")
    public WebElement billingFirstName;

    @FindBy(id = "billing_last_name")
    public WebElement billingLastName;

    @FindBy(id = "billing_address_1")
    public WebElement billingAddress;

    @FindBy(id = "billing_city")
    public WebElement billingCity;

    @FindBy(id = "billing_postcode")
    public WebElement billingPostcode;

    @FindBy(id = "billing_phone")
    public WebElement billingPhone;

    @FindBy(id = "billing_email")
    public WebElement billingEmail;

    @FindBy(id = "place_order")
    public WebElement placeOrderButton;

    @FindBy(xpath = "//*[contains(text(),'Thank you. Your order has been received.')]")
    public WebElement orderSuccessMessage;

    // Coupon in checkout page
    @FindBy(xpath = "//a[.='Enter your code']")
    public WebElement enterYourCode;

    @FindBy(name = "coupon_code")
    public WebElement couponInputInCheckout;

    @FindBy(name = "apply_coupon")
    public WebElement applyCouponBtnInCheckout;

    @FindBy(xpath = "(//span[@class='woocommerce-Price-amount amount'])[6]")
    public WebElement orderTotal ;

    @FindBy(xpath = "//label[@for='shipping_method_0_flat_rate6']")
    public WebElement shippingRow ;

    // Payment methods
    @FindBy(id = "payment_method_bacs")
    public WebElement wireTransferOption;

    @FindBy(id = "payment_method_cod")
    public WebElement payAtDoorOption;

}
