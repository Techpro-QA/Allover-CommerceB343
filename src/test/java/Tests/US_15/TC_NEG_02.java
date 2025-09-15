package Tests.US_15;

import Pages.*;

import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.*;

public class TC_NEG_02 {
    @Test(description = "Attribute kısmında color ve size bilgilerinin doğru ve eksiksiz girilmesi")
    public void testAttribute() {

      // Go to AlloverCommerce website
        ExtentReportsListener.extentTestInfo("Allover Commerce sayfasina gidilir");
        Driver.getDriver().get(ConfigReader.getProperty("alloverCommerceUrl"));
        HomePage homePage = new HomePage();
        ProductPage productPage = new ProductPage();
        MyAccountPage myAccountPage = new MyAccountPage();
        StoreManagerPage storeManagerPage = new StoreManagerPage();
        // Sign in with vendor account
        ExtentReportsListener.extentTestInfo("Kullanici adiyla giris yapabilmek icin Sign in butonuna tiklanir");
        homePage.homeSignIn.click();

        ExtentReportsListener.extentTestInfo("Username alanina kayitli vendor hesabiyla gecerli bilgiler girilir");
        homePage.usernameOrEmailAddressTextBox.sendKeys(ConfigReader.getProperty("vendorEmail"));
        homePage.passwordTextBox.sendKeys(ConfigReader.getProperty("vendorPassword"));
        homePage.signInButton.click();
        WaitUtils.waitFor(2);
        ReusableMethods.addScreenShotToReport();
        // Go to My Account → Store Manager → Products → Add New
        JSUtils.JSscrollIntoView(homePage.textMyAccount);
        WaitUtils.waitForVisibility(homePage.textMyAccount, 2);
        JSUtils.JSclickWithTimeout(homePage.textMyAccount);

        ExtentReportsListener.extentTestInfo("Store Manager > Products > Add New menülerine girildi");
        myAccountPage.storeManagerMenu.click();
        storeManagerPage.productsMenu.click();
        storeManagerPage.addNewButton.click();

        // Enter attributes (color and size)
        JSUtils.JSscrollIntoView(productPage.attributeMenu);
        ReusableMethods.visibleWait(productPage.attributeMenu, 3);
        JSUtils.JSclickWithTimeout(productPage.attributeMenu);

        // Select Color
        if (!productPage.color.isSelected()) {

            JSUtils.JSclickWithTimeout(productPage.color);
        }

        productPage.colorOptions.click();
        productPage.backArrow1.click();

        // Select visibility
        if (!productPage.visiblePageCheckBox.isSelected()) {
            productPage.visiblePageCheckBox.click();
        }
        // Select Size
        if (!productPage.sizeCheckBox.isSelected()) {
            productPage.sizeCheckBox.click();
        }

        productPage.sizeOptions.click();
        productPage.backArrow2.click();
        // Submit attributes
        ReusableMethods.scrollEnd();
        JSUtils.JSclickWithTimeout(productPage.submitButton);
    }


}
