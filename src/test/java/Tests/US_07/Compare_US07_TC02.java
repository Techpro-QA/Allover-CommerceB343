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


    @Test
    public void compareTest02_removeAndAddAgain() { //Karşılaştıracağı ürünleri silip yeni ürünleri ekleyebilme testi

        CompareHelperUS_07 compareHelperUS_07 = new CompareHelperUS_07();

        compareHelperUS_07.search("Bag");

        // 4 ürün ekle
        compareHelperUS_07.addProductsToCompare(4, 4);
        Assert.assertTrue(comparePage.assertionCount4.getText().contains("4"));

        // 2 ürünü sil
        comparePage.removeButton1.click();
        comparePage.removeButton2.click();
        Assert.assertTrue(comparePage.assertionCount2.getText().contains("2"));

        // Tekrar 2 ürün ekle
        compareHelperUS_07.addProductsToCompare(2, 0);
        Assert.assertTrue(comparePage.assertionCount4.getText().contains("4"));
    }
    }


