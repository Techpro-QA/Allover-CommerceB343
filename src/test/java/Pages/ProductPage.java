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

    @FindBy(id = "menu-item-browse")
    public WebElement mediaLibraryTab;

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

 @FindBy(xpath = "//h2[.='Add Product']")
 public WebElement addProductText;

 @FindBy(id = "pro_title")
 public WebElement productTitle;

 @FindBy(xpath = "//select[@class=\"attachment-filters\"]")
 public WebElement filterMediaOptions;

 @FindBy(xpath = "//li[@data-id=\"708101\"]")
 public WebElement emoji;

 @FindBy(xpath = "(//button[@class='media-modal-close'])[2]")
 public WebElement mediaClose;

 @FindBy(xpath = "//input[@data-super_parent='510']")
 public WebElement educationCheckBox;

 @FindBy(xpath = "//input[@data-super_parent='350']")
 public WebElement giftIdeasCheckBox;

//add new

    @FindBy(xpath = "//div[@id=\"wcfm_products_manage_form_inventory_head\"]")
    public WebElement inventoryMenu;


    @FindBy(xpath = "//div[@id=\"wcfm_products_manage_form_shipping_head\"]")
    public WebElement shippingMenu;

    @FindBy(xpath = "//input[@id=\"sku\"]")
    public WebElement numberOfSKU;


    @FindBy(xpath = "//input[@id=\"manage_stock\"]")
    public WebElement manageStockCheckBox;

    @FindBy(xpath = "//input[@id=\"stock_qty\"]")
    public WebElement stockQty;

    @FindBy(xpath = "//select[@id=\"backorders\"]")
    public WebElement allowBackorders;

    @FindBy(xpath = "//input[@id=\"sold_individually\"]")
    public WebElement soldIndividuallyCheckBox;


    // ================== SHIPPING SECTION ==================

    @FindBy(xpath = "//input[@id=\"weight\"]")
    public WebElement weight;

    @FindBy(xpath = "//input[@id=\"length\"]")
    public WebElement length;

    @FindBy(xpath = "//input[@id=\"width\"]")
    public WebElement width;

    @FindBy(xpath = "//input[@id=\"height\"]")
    public WebElement height;

    @FindBy(xpath = "//select[@id=\"_wcfmmp_processing_time\"]")
    public WebElement processingTime;

    // ================== ATTRIBUTES SECTION ==================

    @FindBy(xpath = "//div[@id=\"wcfm_products_manage_form_attribute_head\"]")
    public WebElement attributeMenu;

    @FindBy(xpath = "//input[@id=\"attributes_is_active_1\"]")
    public WebElement color;

    @FindBy(xpath = "//button[@class=\"button wcfm_add_attribute_term wcfm_select_all_attributes\"]")
    public WebElement colorOptions;

    @FindBy(xpath = "//input[@id=\"attributes_is_visible_1\"]")
    public WebElement visiblePageCheckBox;

    @FindBy(xpath = "//input[@id=\"attributes_is_active_2\"]")
    public WebElement sizeCheckBox;

    @FindBy(xpath = "//button[@class=\"button wcfm_add_attribute_term wcfm_select_all_attributes\"]")
    public WebElement sizeOptions;

    @FindBy(xpath = "(//span[@title=\"Toggle Block\"])[2]")
    public WebElement backArrow1;


    @FindBy(xpath = "(//span[@title=\"Toggle Block\"])[3]")
    public WebElement backArrow2;

    @FindBy(xpath = "//*[contains(text(),'Product SKU must be unique.')]")
    public WebElement skuUniqueMessage;

}
