package Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class MyAccountPage {
    public MyAccountPage() {
        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy(xpath = "//a[text()='My Account']")
    public WebElement accountvendorLink;

    @FindBy(xpath ="//a[text()='Orders']")
    public WebElement orders;

    @FindBy(xpath = "//p[.='Account details']")
    public WebElement accountDetailsButton;

    @FindBy(xpath = "//*[@role='alert']")
    public WebElement alertMessage;

    //My Account menu locateleri
    @FindBy(xpath = "//a[text()='Dashboard']")
    public WebElement dashboardMenu;

    @FindBy(xpath = "//a[text()='Store Manager']")
    public WebElement storeManagerMenu;

    @FindBy(xpath = "//a[text()='Orders']")
    public WebElement ordersMenu;

    @FindBy(xpath = "//a[text()='Downloads']")
    public WebElement downloadsMenu;

    @FindBy(xpath = "//a[text()='Addresses']")
    public WebElement addressesMenu;

    @FindBy(xpath = "//a[text()='Account details']")
    public WebElement accountdetailsMenu;

    @FindBy(xpath = "(//a[text()='Wishlist'])[1]")
    public WebElement wishlistMenu;

    @FindBy(xpath = "//a[text()='Support Tickets']")
    public WebElement supportTicketsMenu;

    @FindBy(xpath = "//a[text()='Followings']")
    public WebElement followingsMenu;

    @FindBy(xpath = "//a[text()='Logout']")
    public WebElement logoutButton;

    @FindBy(xpath = "//h2[text()='Store Manager']")
    public WebElement storeManagerTitle;

    @FindBy(xpath = "//h2[text()='My Account']")
    public WebElement myAccountTitle;

    @FindBy(xpath = "//h4[text()='Orders']")
    public WebElement ordersTitle;

    @FindBy(xpath = "//h4[text()='Downloads']")
    public WebElement downloadsTitle;

    @FindBy(xpath = "//h4[text()='Addresses']")
    public WebElement addressesTitle;

    @FindBy(xpath = "//h4[text()='Account Details']")
    public WebElement accountDetailsTitle;

    @FindBy(xpath = "//h2[text()='Wishlist']")
    public WebElement wishlistTitle;

    @FindBy(css = "p.submit-status")
    public WebElement errorMessage;



}
