package Tests.US_07;

import Pages.ComparePage;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.*;

public class Compare_US07_TC03 {
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
    public void compareTest03_openComparePage() { //Seçtiği ürünleri karşılaştırabilme testi

        CompareHelperUS_07 compareHelperUS_07 = new CompareHelperUS_07();

        compareHelperUS_07.search("Bag");

        // 4 ürün ekle
        compareHelperUS_07.addProductsToCompare(4, 4);

        // Karsılastır ve dogrula
        comparePage.startCompareButton.click();
        Assert.assertTrue(comparePage.comparePage.isDisplayed());
    }
}
