package Tests.US_07;

import Pages.ComparePage;
import utilities.ActionsUtils;
import utilities.ReusableMethods;
import utilities.WaitUtils;

public class CompareHelperUS_07 {

    ComparePage comparePage ;

    public CompareHelperUS_07() {
        comparePage = new ComparePage();
    }

    public void search(String keyword) {
        WaitUtils.waitFor(2);
        comparePage.searchBox.clear();
        comparePage.searchBox.sendKeys(keyword);
        comparePage.searchClickButton.click();
    }

    public void addProductsToCompare(int count, int startIndex) {
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
}
