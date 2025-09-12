package Tests.US_07;


import Pages.ComparePage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.*;

public class Compare_US07_TC02 {
    ComparePage comparePage;

    @BeforeMethod
    public void setUp() {
        Driver.getDriver().get(ConfigReader.getProperty("alloverCommerceUrl"));
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
    public void compareTest02_removeAndAddAgain() { //Karşılaştıracağı ürünleri silip yeni ürünleri ekleyebilme testi

        search("Bag");

        // 4 ürün ekle
        addProductsToCompare(4, 4);
        Assert.assertTrue(comparePage.assertionCount4.getText().contains("4"));

        // 2 ürünü sil
        comparePage.removeButton1.click();
        comparePage.removeButton2.click();
        Assert.assertTrue(comparePage.assertionCount2.getText().contains("2"));

        // Tekrar 2 ürün ekle
        addProductsToCompare(2, 0);
        Assert.assertTrue(comparePage.assertionCount4.getText().contains("4"));
    }
    }


