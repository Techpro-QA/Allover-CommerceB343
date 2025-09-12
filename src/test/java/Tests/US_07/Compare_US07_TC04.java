package Tests.US_07;

import Pages.Compare_US_07_Page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.*;

import java.util.List;

public class Compare_US07_TC04 {
    Compare_US_07_Page compareUs07Page ;

    @BeforeMethod
    public void setUp() {
        Driver.getDriver().get(ConfigReader.getProperty("allowerceUrl"));
        compareUs07Page =  new Compare_US_07_Page();
    }

    @AfterMethod
    public void tearDown() {
        Driver.quitDriver();
    }

    // ---------- YARDIMCI METHODLAR ----------

    private void search(String keyword) {
        WaitUtils.waitFor(2);
        compareUs07Page.searchBox.clear();
        compareUs07Page.searchBox.sendKeys(keyword);
        compareUs07Page.searchClickButton.click();
    }

  private void addProductsToCompare(int count, int startIndex) {
      for (int i = 0; i < count; i++) {
          ActionsUtils.hoverOver(compareUs07Page.compareButtons.get(startIndex + i));
          ReusableMethods.click(compareUs07Page.compareButtons.get(startIndex + i));
          WaitUtils.waitFor(1);
          try {
              ReusableMethods.visibleWait(compareUs07Page.popUparea, 1);
              compareUs07Page.popUparea.click();
          } catch (Exception ignored) {}
      }
  }




    @Test
    public void compareTest04_removeFromComparePage() {  //Karşılatırma ekranından ürünleri silebilme testi

        search("Bag");
        addProductsToCompare(3,4);
        compareUs07Page.startCompareButton.click();
        Assert.assertEquals(compareUs07Page.removeFromComparePage.size(), 3);

        WaitUtils.waitFor(2);

        compareUs07Page.removeFromComparePage.get(1).click();
        WaitUtils.waitFor(3);
       //List<WebElement> updatedButtons =
       //        Driver.getDriver().findElements(By.xpath("//a[contains(@class,'remove_from_compare')]"));

        Assert.assertEquals(compareUs07Page.removeFromComparePage.size(), 2);

        WaitUtils.waitFor(2);

    }
}
