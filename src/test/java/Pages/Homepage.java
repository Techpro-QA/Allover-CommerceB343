package Pages;



import utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class Homepage {
    public Homepage() {
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

    //login girişi yapıldıktan sonra açılan sayfadaki element
    @FindBy(xpath = "//span[.='Sign Out']")
    public List<WebElement> singOutButton;

}
