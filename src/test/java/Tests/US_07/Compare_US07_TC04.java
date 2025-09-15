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

        // Test: Verify that the user can remove products from the Compare page.

        // Initialize the helper
        CompareHelperUS_07 compareHelperUS_07 = new CompareHelperUS_07();

        // Search for a product with the keyword "Bag"
        compareHelperUS_07.search("Bag");

        // 3 products to the comparison list click the compare button
        // and verify that 3 products appear on the Compare page
        compareHelperUS_07.addProductsToCompare(3,4);
        comparePage.startCompareButton.click();
        Assert.assertEquals(comparePage.removeFromComparePage.size(), 3);

        // Wait for the elements to load
        WaitUtils.waitFor(2);

        // Remove 1 product from the Compare page
        comparePage.removeFromComparePage.get(1).click();
        WaitUtils.waitFor(3);

        // Verify that only 2 products remain on the Compare page
        Assert.assertEquals(comparePage.removeFromComparePage.size(), 2);

    }
}
