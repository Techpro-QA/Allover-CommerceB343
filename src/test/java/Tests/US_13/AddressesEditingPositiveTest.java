package Tests.US_13;
import Pages.AddressesPage;

import Pages.HomePage;
import Pages.MyAccountPage;
import Pages.StoreManagerPage;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.*;
import utilities.*;


public class AddressesEditingPositiveTest{

    // DataProvider supplies test data for the test method.
    // Here, valid values are provided to test positive scenarios.
    @DataProvider
    public static Object[][] AddressData() {
        return new Object[][]{
                {"Asil","Kandırmaz","KargoTest A.Ş.","Turkey","Kuleli Sk. No:22 D:4","İstanbul","34000","İstanbul","Ankara"}
        };
    }

    @BeforeClass
    public void setUp() {
        // Navigate to the application URL
        Driver.getDriver().get(ConfigReader.getProperty("alloverCommerceUrl"));

        HomePage homePage = new HomePage();

        // Login process
        homePage.homeSignIn.click();
        homePage.usernameOrEmailAddressTextBox.sendKeys(ConfigReader.getProperty("vendorEmail"));
        homePage.passwordTextBox.sendKeys(ConfigReader.getProperty("vendorPassword"));
        homePage.signInButton.click();
        // Ensure login was successful
        homePage.homeSignOut.click();

    }

//This test method fills the shipping address form with valid data
// (positive test scenario) and then verifies if the success message is displayed.

    @Test(dataProvider = "AddressData")
    public void fillShippingAddress(String firstName, String lastName, String company,String country,String street1, String street2, String postCode, String city,String province) {

        MyAccountPage myAccountPage = new MyAccountPage();
        AddressesPage addressesPage = new AddressesPage();

        // Navigate to the "Addresses" section
        myAccountPage.addressesMenu.click();

        // Click on "Edit Your Shipping Address"
        addressesPage.editYourShippingAddress.click();

        // Fill in the form fields with valid data
        addressesPage.firstNameTextBox.clear();
        addressesPage.firstNameTextBox.sendKeys(firstName);
        WaitUtils.waitFor(1);
        addressesPage.lastNameTextBox.clear();
        addressesPage.lastNameTextBox.sendKeys(lastName);
        WaitUtils.waitFor(1);
        addressesPage.companyNameTextBox.clear();
        addressesPage.companyNameTextBox.sendKeys(company);
        WaitUtils.waitFor(1);
        // Select country
        addressesPage.countryDropDown.click();
        addressesPage.countryDropDownTextBox.sendKeys(country, Keys.ENTER);
        addressesPage.streetAddressTextBox.clear();
        addressesPage.streetAddressTextBox.sendKeys(street1);
        ActionsUtils.scrollDown();
        WaitUtils.waitFor(1);
        addressesPage.streetAddress2TextBox.clear();
        addressesPage.streetAddress2TextBox.sendKeys(street2);
        WaitUtils.waitFor(1);
        addressesPage.postCodeTextBox.clear();
        addressesPage.postCodeTextBox.sendKeys(postCode);
        WaitUtils.waitFor(1);
        addressesPage.cityNameTextBox.clear();
        addressesPage.cityNameTextBox.sendKeys(city);
        WaitUtils.waitFor(1);
        // Select province
        addressesPage.provinceDropDown.click();
        addressesPage.provinceDropDownTextBox.sendKeys(province,Keys.ENTER);
        ExtentReportsListener.extentTestPass("Tüm Textboxlara yazı yazılabildi");

        // Save the address
        addressesPage.saveAddressButton.click();
        // Verify success message is displayed
        ExtentReportsListener.addScreenshotToReport("succes message'ın görüntülendiği kontrol edilir");
        WaitUtils.waitForVisibility(addressesPage.succesMessage,3);
        Assert.assertTrue(addressesPage.succesMessage.isDisplayed());
    }

    @AfterClass
    public void tearDown() {
        // Close the driver after test execution
        Driver.quitDriver();
    }
}