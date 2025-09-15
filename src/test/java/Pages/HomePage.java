package Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.util.List;

public class HomePage {

    public HomePage() {
        PageFactory.initElements(Driver.getDriver(),this);
    }

    //Home
    @FindBy(xpath = "//span[.='Sign In']")
    public WebElement homeSignIn;

    @FindBy(xpath = "//span[.='Sign Out']")
    public WebElement homeSignOut;

    //register butonuna basıldıktan sonraki elementler
    @FindBy(xpath = "//span[.='Register']")
    public WebElement registerButton;

    @FindBy(css = "#reg_username")
    public WebElement registerUserName;

    @FindBy(css = "#reg_email")
    public WebElement registerEmail;

    @FindBy(css = "#reg_password")
    public WebElement registerPassword;

    @FindBy(css = "#register-policy")
    public WebElement registerPrivacyPolicyCheckBox;

    @FindBy(css = "[name='register']")
    public WebElement registerSingUpButton;

    @FindBy(xpath = "//*[.='Please provide a valid email address.']")
    public WebElement emailErrorMessage;

    @FindBy(xpath = "//*[.='Please provide a valid email address.']")
    public List<WebElement> bugEmailErrorMessage;

    @FindBy(xpath = "//p[.='Please wait...']")
    public WebElement loginSuccessfulMessage;

    @FindBy(xpath = "//*[.='An account is already registered with that username. Please choose another.']")
    public WebElement sameUserNameErrorMessage;

    @FindBy(xpath = "(//*[@class='submit-status'])[2]")
    public WebElement sameEmailErrorMessage;

    @FindBy(xpath = "//p[.='An account is already registered with your email address. Please log in.']")
    public WebElement registerFailMessage;


    //Sign In
    @FindBy(id = "username")
    public WebElement usernameOrEmailAddressTextBox;

    @FindBy(id = "password")
    public WebElement passwordTextBox;

    @FindBy(id = "rememberme")
    public WebElement rememberMeCheckBox;

    @FindBy(name = "login")
    public WebElement signInButton;

    @FindBy(css = "[title='Close (Esc)']")
    public WebElement signInCloseButton;

    @FindBy(xpath = "//p[.='Wrong username or password.']")
    public List<WebElement> wrongUserNameOrPassword;

    @FindBy(xpath = "//span[.='Sign Out']")
    public List<WebElement> singOutButton;

    //Browse Products
    //Nerde olduğunu bulamadık
    @FindBy(xpath = "//a[text()='Browse Products']")
    public WebElement browseProducts;

    @FindBy(xpath = "(//a[contains(text(),'Cart') or @title='View your shopping cart'])[1]")
    public WebElement cartIcon;

    //Footer Locateleri
    @FindBy(xpath = "//a[.='My Account']")
    public WebElement textMyAccount;

    @FindBy(xpath = "//a[text()='My Account']")
    public WebElement accountvendorLink;

}