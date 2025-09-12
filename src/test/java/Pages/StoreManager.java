package Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class StoreManager {
    public StoreManager() {
        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy(xpath = "//a[span[@class='text' and normalize-space()='Coupons']]")
    public WebElement coupons;

    @FindBy(xpath = "//span[.='Add New']")
    public WebElement addNew;

    @FindBy(xpath = "(//input[@type='search'])[2]")
    public WebElement couponSearchBox;

    @FindBy(linkText = "Addresses")
    public WebElement addressButton;



    //new coupons page
    @FindBy(id = "title")
    public WebElement codeTextBox;

    @FindBy(id = "description")
    public WebElement descriptionTextBox;

    @FindBy(id = "discount_type")
    public WebElement discountTypeDD;

    @FindBy(id = "coupon_amount")
    public WebElement couponAmountNumberBox;

    @FindBy(id = "expiry_date")
    public WebElement couponExpiryDateBox;

    @FindBy(id = "free_shipping")
    public WebElement allowFreeShippingCheckBox;

    @FindBy(id = "show_on_store")
    public WebElement showOnStoreCheckBox;

    @FindBy(id = "wcfm_coupon_manager_submit_button")
    public WebElement couponSubmitButton;

    //Coupons page
    @FindBy(xpath = "//table[1]//tbody//tr//td[1]")
    public WebElement lastCouponName;

    @FindBy(xpath = "(//tr[2]/td)[1]")
    public WebElement secondRowFirstColumn;

    @FindBy(xpath = "//*[contains(text(),'Coupon Successfully Published')]")
    public WebElement successMessage;

    @FindBy(xpath = "(//*[contains(text(),'Coupon code already exists')]")
    public WebElement alreadyExistsMessage;

     @FindBy(id = "wcfm_coupon_manager_submit")
    public WebElement couponNameCannotBeLeftBlankMessage;


}