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

        // Test: Verify that the user can open the Compare page
        // and view the products selected for comparison.

        // Initialize the helper
        CompareHelperUS_07 compareHelperUS_07 = new CompareHelperUS_07();

        // Search for a product with the keyword "Bag"
        compareHelperUS_07.search("Bag");

        // Add 4 products to the comparison list
        compareHelperUS_07.addProductsToCompare(4, 4);

        // Click the "Start Compare" button
        comparePage.startCompareButton.click();

        // Verify that the Compare page is displayed
        Assert.assertTrue(comparePage.comparePage.isDisplayed());
    }
}
