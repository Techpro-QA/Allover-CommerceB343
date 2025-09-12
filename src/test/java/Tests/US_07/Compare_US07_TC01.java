package Tests.US_07;


import Pages.ComparePage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.*;

public class Compare_US07_TC01 {
    ComparePage comparePage ;

    @BeforeMethod
    public void setUp() {
        Driver.getDriver().get(ConfigReader.getProperty("alloverCommerceUrl"));
        comparePage =  new ComparePage();
    }

  //@AfterMethod
  //public void tearDown() {
  //    Driver.quitDriver();
  //}

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

  // @Test
  // public void compareTest01_max4Products() { //Kullanıcının en fazla 4 ürünü karşılaştırmak için seçebilme testi

  //     search("Bag");
  //     addProductsToCompare(5, 4);  // 5 ürün eklemeye çalış
  //     // 4 üncü indeksten baslama sebebeim sitede 2.3 4 ürünün aynı olması

  //     ExtentReportsListener.addScreenshotToReport(
  //             "4. ürün eklendikten sonra 5. ürün eklenince 1. ürün siliniyor ve sonuç olarak en fazla 4 ürün karşılaştırılabiliyor."
  //     );

  //     Assert.assertTrue(comparePage.assertionCount4.getText().contains("4"));
  // }

    @Test
    public void compareTest01_max4Products() {
        // Kullanıcının en fazla 4 ürünü karşılaştırmaya ekleyebilmesi testi
        search("Bag");

        // Önce 4 ürün ekle
        addProductsToCompare(4, 4);
        Assert.assertEquals(comparePage.assertionCount4.getText(),"(4 Products)");


        // Ekran görüntüsü (4. ürün eklendiğinde)
        ExtentReportsListener.addScreenshotToReport("4 ürün eklendikten sonra ekran görüntüsü");

        // 5. ürünü eklemeye çalış
        addProductsToCompare(1, 8);

        // Hâlâ 4 ürün olduğundan emin ol
        Assert.assertEquals(comparePage.assertionCount4.getText(),"(4 Products)");

        // Ekran görüntüsü (5. ürün eklenmeye çalışıldıktan sonra)
        ExtentReportsListener.addScreenshotToReport("5. ürün eklenmeye çalışıldıktan sonra ekran görüntüsü");
    }

}
