package Tests.US_07;

import Pages.Compare_US_07_Page;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.*;

public class Compare_US07_TC04 {
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
    @Test
    public void compareTest04_removeFromComparePage() {

        //Karşılatırma ekranından ürünleri silebilmeli
        search("Bag");
        addProductsToCompare(3, 4);  // 3 ürün ekle
        compareUs07Page.startCompareButton.click();

        compareUs07Page.removeFromCompareButton.click();
        // burada assert ekleyebilirsin: ürün sayısı azaldı mı?
    }
}
