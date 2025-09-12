package Tests.US_07;


import Pages.Compare_US_07_Page;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.*;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class Compare_US07_TC02 {
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
    public void compareTest02_removeAndAddAgain() {

        //Karşılaştıracağı ürünleri silip yeni ürünleri ekleyebilme testi

        search("Bag");
        addProductsToCompare(4, 4);  // 4 ürün ekle
        Assert.assertTrue(compareUs07Page.assertionCount4.getText().contains("4"));

        // 2 ürünü sil
        compareUs07Page.removeButton1.click();
        compareUs07Page.removeButton2.click();
        Assert.assertTrue(compareUs07Page.assertionCount2.getText().contains("2"));

        // Tekrar 2 ürün ekle
        addProductsToCompare(2, 0);
        Assert.assertTrue(compareUs07Page.assertionCount4.getText().contains("4"));
    }
    }


