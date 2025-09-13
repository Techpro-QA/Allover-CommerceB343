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

    //Sign Out
    @FindBy(name = "login")
    public WebElement signInButton;


}