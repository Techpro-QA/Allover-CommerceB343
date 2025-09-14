package Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class OrdersPage {
    public OrdersPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    // "Browse Products" linki (Orders sayfasında)
    @FindBy(xpath = "//a[@class='btn btn-dark btn-rounded btn-icon-right continue-shopping mb-4 mt-6']")
    public WebElement GoShop;

    @FindBy(xpath = "//a[@class='woocommerce-button btn btn-default btn-rounded btn-outline btn-sm btn-block view']")
    public WebElement view;

    @FindBy(xpath = "//h2[@class='woocommerce-order-details__title']")
    public WebElement orderDetails;

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
