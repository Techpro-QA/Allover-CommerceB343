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
        Driver.getDriver().get(ConfigReader.getProperty("alloverCommerceUrl"));
        comparePage =  new ComparePage();
    }

    @AfterMethod
    public void tearDown() {
        Driver.quitDriver();
    }



    @Test
    public void compareTest04_removeFromComparePage() {  //Karşılatırma ekranından ürünleri silebilme testi

        CompareHelperUS_07 compareHelperUS_07 = new CompareHelperUS_07();

        compareHelperUS_07.search("Bag");

        // 3 urun ekle ve karsılastırma ekranında 3 urun oldugunu dogrula
        compareHelperUS_07.addProductsToCompare(3,4);
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
