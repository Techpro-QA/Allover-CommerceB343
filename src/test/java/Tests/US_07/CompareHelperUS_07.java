package Tests.US_07;

import Pages.ComparePage;
import utilities.ActionsUtils;
import utilities.ReusableMethods;
import utilities.WaitUtils;

public class CompareHelperUS_07 {

    ComparePage comparePage ;

    // Constructor
    public CompareHelperUS_07() {
        // Initialize the ComparePage object
        comparePage = new ComparePage();
    }

    //Search for a product by keyword.
    public void search(String keyword) {

        // Wait for the page to be ready
        WaitUtils.waitFor(2);

        // Clear the search box
        comparePage.searchBox.clear();

        // Enter the keyword into the search box
        comparePage.searchBox.sendKeys(keyword);

        // Click the search button
        comparePage.searchClickButton.click();
    }

    //Add products to the comparison list.
    public void addProductsToCompare(int count, int startIndex) {

        // Loop through the number of products to add
        for (int i = 0; i < count; i++) {

            // Hover over the product's "Compare" button
            ActionsUtils.hoverOver(comparePage.compareButtons.get(startIndex + i));

            // Click the "Compare" button
            ReusableMethods.click(comparePage.compareButtons.get(startIndex + i));

            // Wait briefly after clicking
            WaitUtils.waitFor(1);

            // If a pop-up appears, close it
            try {
                ReusableMethods.visibleWait(comparePage.popUparea, 1);
                comparePage.popUparea.click();
            } catch (Exception ignored) {
                // No pop-up appeared, continue
            }
        }
    }
}
