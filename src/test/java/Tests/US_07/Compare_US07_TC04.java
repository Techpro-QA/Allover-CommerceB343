package Tests.US_07;

import Pages.ComparePage;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.*;

public class Compare_US07_TC04 {
    ComparePage comparePage;

    @BeforeMethod
    public void setUp() {
        Driver.getDriver().get(ConfigReader.getProperty("allowerceUrl"));
        comparePage =  new ComparePage();
    }

    @AfterMethod
    public void tearDown() {
        Driver.quitDriver();
    }

    // ---------- YARDIMCI METHODLAR ----------

    private void search(String keyword) {
        WaitUtils.waitFor(2);
        comparePage.searchBox.clear();
        comparePage.searchBox.sendKeys(keyword);
        comparePage.searchClickButton.click();
    }

  private void addProductsToCompare(int count, int startIndex) {
      for (int i = 0; i < count; i++) {
          ActionsUtils.hoverOver(comparePage.compareButtons.get(startIndex + i));
          ReusableMethods.click(comparePage.compareButtons.get(startIndex + i));
          WaitUtils.waitFor(1);
          try {
              ReusableMethods.visibleWait(comparePage.popUparea, 1);
              comparePage.popUparea.click();
          } catch (Exception ignored) {}
      }
  }


    @Test
    public void compareTest04_removeFromComparePage() {  //Karşılatırma ekranından ürünleri silebilme testi

        search("Bag");

        // 3 urun ekle ve karsılastırma ekranında 3 urun oldugunu dogrula
        addProductsToCompare(3,4);
        comparePage.startCompareButton.click();
        Assert.assertEquals(comparePage.removeFromComparePage.size(), 3);

        WaitUtils.waitFor(2);

        // 1 urun sil
        comparePage.removeFromComparePage.get(1).click();
        WaitUtils.waitFor(3);

        //  karsılastırma ekranında 2 urun kaldıgını dogrula
        Assert.assertEquals(comparePage.removeFromComparePage.size(), 2);

    }
}
