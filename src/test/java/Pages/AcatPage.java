package Pages;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class AcatPage {

    public AcatPage() {
        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy(xpath = "//input[@aria-label='Search']")
    public WebElement searchBox;

    @FindBy(xpath = "//button[@aria-label='Search Button']")
    public WebElement searchClickButton;

    //@FindBy(xpath = "(//a[@title='Compare'])[2]")
    //public WebElement firstChoice;

    @FindBy(xpath = "(//a[@class='compare btn-product-icon'])[1]")
    public WebElement firstChoice;

    @FindBy(xpath = "//a[@data-product_id='44481']")
    public WebElement secondChoice;

    @FindBy(xpath = "//a[@data-product_id='36429']")
    public WebElement thirdChoice;

    @FindBy(xpath = "//a[@data-product_id='43117']")
    public WebElement fourthChoice;



    // US_01 Webelements
    @FindBy(linkText = "Register")
    public WebElement register;

    @FindBy(id = "reg_username")
    public WebElement usernameBox;

    @FindBy(id = "reg_email")
    public WebElement emailBox;

    @FindBy(id = "reg_password")
    public WebElement passwordBox;

    @FindBy(id = "register-policy")
    public WebElement privacyPolicyBox;

    @FindBy(xpath = "//button[.='Sign In']")
    public WebElement signUp;







}
