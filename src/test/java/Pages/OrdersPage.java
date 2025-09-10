package Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class OrdersPage {
    public OrdersPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//a[text()='Orders']")
    public WebElement ordersTab;

    @FindBy(xpath = "(//a[text()='View'])[1]")
    public WebElement firstOrderViewButton;

    @FindBy(xpath = "//h2[text()='Order details']")
    public WebElement orderDetailsHeading;

    @FindBy(xpath = "//*[contains(text(),'Payment method')]")
    public WebElement paymentMethodInfo;

    @FindBy(xpath = "(//*[contains(text(),'Total')])[2]")
    public WebElement totalAmountInfo;
}
