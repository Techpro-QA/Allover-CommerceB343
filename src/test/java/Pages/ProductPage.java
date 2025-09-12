package Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class ProductPage  {
public ProductPage() { PageFactory.initElements(Driver.getDriver(),this); }

//Products
@FindBy(xpath = "(//a[@class='wcfm_menu_item '])[3]")
public WebElement productsLink;

@FindBy(xpath = "//span[.='Add New']")
public WebElement addNewButton;

@FindBy(id = "is_virtual")
public WebElement virtualCheckbox;

@FindBy(id = "pro_title")
public WebElement productTitleBox;

@FindBy(id = "regular_price")
public WebElement priceBox;

@FindBy(id = "sale_price")
public WebElement salePriceBox;

@FindBy(xpath = "(//input[@class='wcfm-checkbox checklist_type_product_cat '])[132]")
public WebElement clothingCategoryCheckbox;

@FindBy(id = "featured_img_display")
public WebElement uploadFile;

@FindBy(id = "menu-item-upload")
public WebElement uploadFiles;

@FindBy(id = "__wp-uploader-id-1")
public WebElement selectFilesButton;

@FindBy(xpath = "//button[.='Select']")
public WebElement selectButton;

@FindBy(id = "gallery_img_gimage_0_display")
public WebElement subUploadFile;

@FindBy(xpath = "(//button[@class='button media-button button-primary button-large media-button-select'])[2]")
public WebElement addToGallery;

@FindBy(id = "wcfm_products_simple_submit_button")
public WebElement submitButton;

@FindBy(xpath = "//div[.='Product Successfully Published.']")
public WebElement successMessage;

}
