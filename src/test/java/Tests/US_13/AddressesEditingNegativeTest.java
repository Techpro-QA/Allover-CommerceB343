package Tests.US_13;
import Pages.AddressesPage;
import Pages.HomePage;
import Pages.MyAccountPage;
import Pages.StoreManagerPage;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.*;
import utilities.*;


public class AddressesEditingNegativeTest{

    //DataProvider supplies test data for the test method
    //Here, empty values are intentionally provided to test negative scenarios
    @DataProvider
    public static Object[][] AddressData() {
        return new Object[][]{
                {"","","","Turkey","","","","","Ankara"}
        };
    }

    @BeforeClass
    public void setUp() {
        //Navigate to the application URL
        Driver.getDriver().get(ConfigReader.getProperty("alloverCommerceUrl"));


        HomePage homePage = new HomePage();

        //Login proces
        homePage.homeSignIn.click();
        homePage.usernameOrEmailAddressTextBox.sendKeys(ConfigReader.getProperty("vendorEmail"));
        homePage.passwordTextBox.sendKeys(ConfigReader.getProperty("vendorPassword"));
        homePage.signInButton.click();
        //Ensure the account is signed in (by clicking sign out option)
        homePage.homeSignOut.click();

    }

    // This test method fills the shipping address form with invalid/empty data
    // (negative test scenario) and then validates if the proper error messages are displayed.
    @Test(dataProvider = "AddressData")
    public void fillShippingAddress(String firstName, String lastName, String company,String country,String street1, String street2, String postCode, String city,String province) {

        MyAccountPage myAccountPage = new MyAccountPage();
        AddressesPage addressesPage = new AddressesPage();

        //a// Navigate to the "Addresses" section
        myAccountPage.addressesMenu.click();

        //Click on "Edit Your Shipping Address"
        addressesPage.editYourShippingAddress.click();
        ExtentReportsListener.extentTestInfo("Form elementleri dolduruluyor");
        //Fill in the form fields (with test data provided by DataProvider)
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
        ExtentReportsListener.extentTestPass("Tüm form elementleri doğru çalışıyor");
        // Click "Save Address" to attempt saving invalid form
        addressesPage.saveAddressButton.click();
        // Verify that failure messages are displayed for the required fields
        ExtentReportsListener.addScreenshotToReport("Fail mesajları görüntülenmeli");
        Assert.assertTrue(addressesPage.firstNameFailMessage.isDisplayed());
        Assert.assertTrue(addressesPage.lastNameFailMessage.isDisplayed());
        Assert.assertTrue(addressesPage.streetAddressFailMessage.isDisplayed());
        Assert.assertTrue(addressesPage.postCodeFailMessage.isDisplayed());
        Assert.assertTrue(addressesPage.townCityFailMessage.isDisplayed());
    }

    @AfterClass
    // Close the driver after test execution
    public void tearDown() {
        Driver.quitDriver();
    }
}