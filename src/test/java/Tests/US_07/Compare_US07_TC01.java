package Tests.US_07;


import Pages.ComparePage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.*;

public class Compare_US07_TC01 {

    // Verify that a user can add a maximum of 4 products to the comparison list.

    ComparePage comparePage;

    @BeforeMethod
    public void setUp() {
        // Open the application
        // Launch the browser and navigate to the Allover Commerce URL
        Driver.getDriver().get(ConfigReader.getProperty("alloverCommerceUrl"));
        comparePage = new ComparePage();
    }

    @AfterMethod
    public void tearDown() {
        // Close the browser and clean up the test session
        Driver.quitDriver();
    }


    @Test
    public void compareTest01_max4Products() {

        // Initialize the helper
        CompareHelperUS_07 compareHelperUS_07 = new CompareHelperUS_07();

        // Search for a product with keyword "Bag"
        compareHelperUS_07.search("Bag");

        // Add 4 products to the comparison list
        // Verify that the counter displays "(4 Products)"
        compareHelperUS_07.addProductsToCompare(4, 4);
        Assert.assertEquals(comparePage.assertionCount4.getText(), "(4 Products)");

        // Capture a screenshot after adding 4 products
        ExtentReportsListener.addScreenshotToReport("4 ürün eklendikten sonra ekran görüntüsü");

        // Attempt to add a 5th product
        compareHelperUS_07.addProductsToCompare(1, 8);

        // Verify that only 4 products remain in the comparison list
        Assert.assertEquals(comparePage.assertionCount4.getText(), "(4 Products)");

        // Capture a screenshot after attempting to add the 5th product
        ExtentReportsListener.addScreenshotToReport("5. ürün eklenmeye çalışıldıktan sonra ekran görüntüsü");
    }

}
