package Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.util.List;

public class AccountDetailsPage {

    public AccountDetailsPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

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
