package Pages;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.util.List;

public class ComparePage {

    public ComparePage() {
        PageFactory.initElements(Driver.getDriver(),this);
    }

   @FindBy(xpath = "//input[@aria-label='Search']")
   public WebElement searchBox;

   @FindBy(xpath = "//button[@aria-label='Search Button']")
   public WebElement searchClickButton;

   @FindBy(xpath = "//div[@class='minipopup-area']")
   public WebElement popUparea;

   @FindBy(xpath = "//a[contains(@class,'compare') and contains(@class,'btn-product-icon')]")
   public List<WebElement> compareButtons;

   @FindBy(xpath = "//a[.='Start Compare !']")
   public WebElement startCompareButton;

   @FindBy(xpath = "(//a[@class='btn-remove remove_from_compare fas fa-times'])[1]")
   public WebElement removeButton1;

   @FindBy(xpath = "(//a[@class='btn-remove remove_from_compare fas fa-times'])[2]")
   public WebElement removeButton2;

   @FindBy(xpath = "//p[contains(., '4') and contains(., 'Products')]")
   public WebElement assertionCount4;

   @FindBy(xpath = "//p[contains(., '2') and contains(., 'Products')]")
   public WebElement assertionCount2;

   @FindBy(tagName = "h2")
   public WebElement comparePage;

   @FindBy(xpath = "//a[@class='compare-action remove_from_compare']")
   public List<WebElement> removeFromComparePage;







}
