package Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class HomePage {

    public HomePage() {
        PageFactory.initElements(Driver.getDriver(),this);
    }

    //Home
    @FindBy(xpath = "//span[.='Sign In']")
    public WebElement homeSignIn;

    @FindBy(xpath = "//span[.='Sign Out']")
    public WebElement homeSignOut;

    //Sign In
    @FindBy(id = "username")
    public WebElement usernameOrEmailAddressTextBox;

    @FindBy(id = "password")
    public WebElement passwordTextBox;

    @FindBy(id = "rememberme")
    public WebElement rememberMeCheckBox;

    //Sing Out
    @FindBy(name = "login")
    public WebElement signInButton;

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

    @FindBy(xpath = "//span[.='Sign Out']")
    public WebElement singOutButton;

    @FindBy(xpath = "(//p[@class='submit-status'])[2]")
    public WebElement loginErrorMessage;

}
