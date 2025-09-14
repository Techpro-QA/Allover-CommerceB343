package Pages;


import utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class Homeee {
    public Homeee() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    //register butonuna basıldıktan sonraki elementler
    @FindBy(xpath = "//span[.='Register']")
    public WebElement registerButton;

    @FindBy(css = "#reg_username")
    public WebElement userName;

    @FindBy(css = "#reg_email")
    public WebElement email;

    @FindBy(css = "#reg_password")
    public WebElement password;

    @FindBy(css = "#register-policy")
    public WebElement checkBox;

    @FindBy(css = "[name='register']")
    public WebElement singUpButton;

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

    @FindBy(xpath = "//span[.='Sign In']")
    public WebElement signInButton;

    // sing in butonuna basıldıktan sonraki elementler
    @FindBy(css = "#username")
    public WebElement userNameOrEmail;

    @FindBy(css = "#password")
    public WebElement passwordOnSingIn;

    @FindBy(css = "#rememberme")
    public WebElement rememberMeCheckBox;

    @FindBy(css = "[name='login']")
    public WebElement signInButton2;

    @FindBy(xpath = "//p[.='An account is already registered with your email address. Please log in.']")
    public WebElement registerFailMessage;


    @FindBy(css = "[title='Close (Esc)']")
    public WebElement signInCloseButton;

    @FindBy(xpath = "//p[.='Wrong username or password.']")
    public List<WebElement> wrongUserNameOrPassword;


    //login girişi yapıldıktan sonra açılan sayfadaki elementler
    @FindBy(xpath = "//span[.='Sign Out']")
    public List<WebElement> singOutButton;

    @FindBy(xpath = "//span[.='Sign Out']")
    public WebElement singOutButtonClickable;

    //My Account sayfası
    @FindBy(xpath = "//p[.='Account details']")
    public WebElement accountDetailsButton;

    @FindBy(xpath = "//*[@role='alert']")
    public WebElement alertMessage;

    //Account details e tıklandıktan sonra açılan My Account sayfası
    @FindBy(css = "#account_first_name")
    public WebElement firstNameAccountDetails;

    @FindBy(css = "#account_last_name")
    public WebElement lastNameAccountDetails;

    @FindBy(css = "#account_display_name")
    public WebElement displayNameAccountDetails;

    @FindBy(css = "#account_email")
    public WebElement emailAccountDetails;

    @FindBy(name = "save_account_details")
    public WebElement saveChangesAccountDetails;

    @FindBy(xpath = "//div[@role='alert']")
    public WebElement alertAccountDetails;

    @FindBy(xpath = "//div[@role='alert']")
    public List<WebElement> alertListAccountDetails;

    @FindBy(xpath = "//button/i[@class='close-icon']")
    public WebElement alertShutAccountDetails;

    @FindBy(css = "#tinymce") //bu element iframe içindedir
    public WebElement biographyAccountDetails;

    @FindBy(css = "#password_current")
    public WebElement passwordCurrentAccountDetails;

    @FindBy(css = "#password_1")
    public WebElement passwordNewAccountDetails;

    @FindBy(css = "#password_2")
    public WebElement passwordConfirmAccountDetails;

    @FindBy(xpath = "//*[.='Hint: The password should be at least twelve characters long. To make it stronger, use upper and lower case letters, numbers, and symbols like ! \" ? $ % ^ & ).']")
    public WebElement passwordEssencialsMessage;

    @FindBy(xpath = "//ul[@role='alert']")
    public WebElement passwordMissingMessagesAccountDetails;

    @FindBy(xpath = "//*[@data-id='account_first_name']")
    public WebElement firstNameMissingMessagesAccountDetails;

    @FindBy(xpath = "//*[@data-id='account_last_name']")
    public WebElement lastNameMissingMessagesAccountDetails;

    @FindBy(xpath = "//*[@data-id='account_display_name']")
    public WebElement displayNameMissingMessagesAccountDetails;

    @FindBy(xpath = "//*[@data-id='account_email']")
    public WebElement emailMissingMessagesAccountDetails;







}