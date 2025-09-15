package Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class StoreManagerPage {
    public StoreManagerPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//a[span[@class='text' and normalize-space()='Coupons']]")
    public WebElement coupons;

    @FindBy(xpath = "//span[.='Add New']")
    public WebElement addNew;

    @FindBy(xpath = "(//input[@type='search'])[2]")
    public WebElement couponSearchBox;

    @FindBy(linkText = "Addresses")
    public WebElement addressButton;

    @FindBy(xpath = "//a[@href='https://allovercommerce.com/store-manager/products/']")
    public WebElement products;

    @FindBy(xpath = "//span[@class='wcfm-page-heading-text' and text()='Products']")
    public WebElement productsMenu;

    @FindBy(xpath = "//a[@id='add_new_product_dashboard']")
    public WebElement addNewButton;

    @FindBy(xpath = "//h2[.='Add Product']")
    public WebElement addProductTitle;

    @FindBy(xpath = "//*[@id='product_type']")
    public WebElement dropDownMenu;

    @FindBy(id = "wcfm_products_manage_form_variations_head")
    public WebElement variationsHeadForProduct;

    @FindBy(id = "wcfm_products_manage_form_grouped_head")
    public WebElement groupedHeadForProduct;

    @FindBy(id = "wcfm_products_manage_form_shipping_head")
    public WebElement shippingHeadForProduct;

    @FindBy(id = "featured_img_display")
    public WebElement productImage;

    @FindBy(xpath = "//button[normalize-space()='Select Files']")
    public WebElement selectFilesButton;

    @FindBy(xpath = "//input[@type='file']")
    public WebElement inputFileElement;

    @FindBy(id = "gallery_img_gimage_0_display")
    public WebElement miniProductImage;

    @FindBy(xpath = "(//button[.='Add to Gallery'])[2]")
    public WebElement addToGalleryButton;

    @FindBy(xpath = "//button[normalize-space()='Select']")
    public WebElement selectButton;

   // @FindBy(xpath = "//ul[@id='__attachments-view-1138']//li[1]")
   // public WebElement lastUploadProductImage;

    @FindBy(xpath = "//button[@class='media-modal-close']")
    public WebElement exitMediaLibraryWindow;

    @FindBy(xpath = "(//*[@id='attachment-details-title'])[1]")
    public WebElement imageDetailsTitle;

    @FindBy(id = "pro_title")
    public WebElement productTitle;

    @FindBy(xpath = "//body[@id='tinymce' and @data-id='excerpt']")
    public WebElement shortDescriptionBox;

    @FindBy(xpath = "//body[@id='tinymce' and @data-id='description']")
    public WebElement descriptionBox;

    @FindBy(xpath = "//input[@data-super_parent='1555']")
    public WebElement categoriesCheckBox;

    @FindBy(xpath = "//input[@class='wcfm-checkbox checklist_type_product_brand ' and @value='298']")
    public WebElement productBrandsCheckBox;

    @FindBy(xpath = "//input[@id='wcfm_products_simple_submit_button']")
    public WebElement submitProductButton;

    @FindBy(xpath = "//span[.='Published']")
    public WebElement publishedTitle;

    @FindBy(xpath = "//span[@data-hasqtip='92']")
    public WebElement viewLastAddedProduct;




















}
