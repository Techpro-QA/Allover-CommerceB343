package Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class AddressesPage {

    public AddressesPage() {
        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy(xpath = "//a[.='Edit Your Shipping Address']")
    public WebElement editYourShippingAddress;

    @FindBy(id = "shipping_first_name")
    public WebElement firstNameTextBox;

    @FindBy(id = "shipping_last_name")
    public WebElement lastNameTextBox;

    @FindBy(id = "shipping_company")
    public WebElement companyNameTextBox;

    @FindBy(id = "select2-shipping_country-container")
    public WebElement countryDropDown;

    @FindBy(xpath = "//input[@role='combobox']")
    public WebElement countryDropDownTextBox;

    @FindBy(id = "shipping_address_1")
    public WebElement streetAddressTextBox;

    @FindBy(id = "shipping_address_2")
    public WebElement streetAddress2TextBox;

    @FindBy(id = "shipping_postcode")
    public WebElement postCodeTextBox;

    @FindBy(id = "shipping_city")
    public WebElement cityNameTextBox;

    @FindBy(name = "save_address")
    public WebElement saveAddressButton;

    @FindBy(xpath = "//div[@role='alert']")
    public WebElement succesMessage;

    @FindBy(id = "select2-shipping_state-container")
    public WebElement provinceDropDown;

    @FindBy(xpath = "//input[@role='combobox']")
    public WebElement provinceDropDownTextBox;

    //fail mesajları locateleri

    @FindBy(xpath = "//li[@data-id='shipping_first_name']")
    public WebElement firstNameFailMessage;

    @FindBy(xpath = "//li[@data-id='shipping_last_name']")
    public WebElement lastNameFailMessage;

    @FindBy(xpath = "//li[@data-id='shipping_address_1']")
    public WebElement streetAddressFailMessage;

    @FindBy(xpath = "//li[@data-id='shipping_postcode']")
    public WebElement postCodeFailMessage;

    @FindBy(xpath = "//li[@data-id='shipping_city']")
    public WebElement townCityFailMessage;








}