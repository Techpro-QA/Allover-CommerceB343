package Tests.US_07;


import Pages.Compare_US_07_Page;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.*;

public class Compare_US07_TC01 {
   Compare_US_07_Page compareUs07Page ;

    @BeforeMethod
    public void setUp() {
        Driver.getDriver().get(ConfigReader.getProperty("allowerCommerceUrl"));
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

    // ---------- TESTS ----------
    @Test
    public void compareTest01_max4Products() {

        //Kullanıcı en fazla 4 ürünü karşılaştırmak için seçebilmeli

        search("Bag");
        addProductsToCompare(5, 4);  // 5 ürün eklemeye çalış
        // 4 üncü indeksten baslama sebebeim sitede 2.3 4 ürünün aynı olması

        ExtentReportsListener.addScreenshotToReport(
                "4. ürün eklendikten sonra 5. ürün eklenince 1. ürün siliniyor ve sonuç olarak en fazla 4 ürün karşılaştırılabiliyor."
        );

        Assert.assertTrue(compareUs07Page.assertionCount4.getText().contains("4"));
    }

}
