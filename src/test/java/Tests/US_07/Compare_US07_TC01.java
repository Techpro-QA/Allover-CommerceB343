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

  @AfterMethod
  public void tearDown() {
      Driver.quitDriver();
  }


    @Test
    public void compareTest01_max4Products() {

        CompareHelperUS_07 compareHelperUS_07 = new CompareHelperUS_07();

        // Kullanıcının en fazla 4 ürünü karşılaştırmaya ekleyebilmesi testi
        compareHelperUS_07.search("Bag");

        // Önce 4 ürün ekle
        compareHelperUS_07.addProductsToCompare(4, 4);
        Assert.assertEquals(comparePage.assertionCount4.getText(),"(4 Products)");


        // Ekran görüntüsü (4. ürün eklendiğinde)
        ExtentReportsListener.addScreenshotToReport("4 ürün eklendikten sonra ekran görüntüsü");

        // 5. ürünü eklemeye çalış
        compareHelperUS_07.addProductsToCompare(1, 8);

        // Hâlâ 4 ürün olduğundan emin ol
        Assert.assertEquals(comparePage.assertionCount4.getText(),"(4 Products)");

        // Ekran görüntüsü (5. ürün eklenmeye çalışıldıktan sonra)
        ExtentReportsListener.addScreenshotToReport("5. ürün eklenmeye çalışıldıktan sonra ekran görüntüsü");
    }

}
