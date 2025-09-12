package Tests.US_07;

import Pages.Compare_US_07_Page;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.*;

public class Compare_US07_TC03 {
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
    public void compareTest03_openComparePage() { //Seçtiği ürünleri karşılaştırabilme testi

        search("Bag");

        // 4 ürün ekle
        addProductsToCompare(4, 4);

        // Karsılastır ve dogrula
        compareUs07Page.startCompareButton.click();
        Assert.assertTrue(compareUs07Page.comparePage.isDisplayed());
    }
}
