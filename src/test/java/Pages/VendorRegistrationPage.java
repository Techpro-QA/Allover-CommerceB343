package Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class VendorRegistrationPage {
    public VendorRegistrationPage() {
        PageFactory.initElements(Driver.getDriver(),this);
    }

    //password
    @FindBy(xpath = "//a[@class='register inline-type']")
    public WebElement registerButton;

    @FindBy(xpath = "//a[@class='register_as_vendor']")
    public WebElement signupAsAVendorButton;

    @FindBy(id = "user_email")
    public WebElement vendorRegistrationEmailTextBox;

    @FindBy(xpath = "//input[@value='Re-send Code']")
    public WebElement resendCodeButton;

    @FindBy(id = "passoword")
    public WebElement vendorRegistrationPasswordTextBox;

    @FindBy(id = "confirm_pwd")
    public WebElement vendorRegistrationConfirmPasswordTextBox;

    @FindBy(id = "wcfm_membership_register_button")
    public WebElement vendorRegistrationRegisterButton;

    @FindBy(xpath = "//div[.='Too short']")
    public WebElement tooShortTextBox;

    @FindBy(xpath = "//div[.='Weak']")
    public WebElement weakTextBox;

    @FindBy(xpath = "//div[.='Good']")
    public WebElement goodTextBox;


    @FindBy(xpath = "//div[.='Strong']")
    public WebElement strongTextBox;




}
