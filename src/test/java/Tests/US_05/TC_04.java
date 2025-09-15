package Tests.US_05;

import Pages.AccountDetailsPage;
import Pages.HomePage;
import Pages.MyAccountPage;
import com.github.javafaker.Faker;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utilities.*;

public class TC_04 {
//        1	My Account' sayfasına gidilir
//        2	Account Details' sayfasına gidilir
//        3	Hesap bilgileri görülür
//        4	First name, Last name, Display name ve Email address alanlarında değişiklik yapılmaz
//        5	Password change' kısmına gidilir
//        6	Current password' kısmına eski şifre girilir
//        7	New password' kısmına yeni şifre girilir
//        8	Confirm password' kısmına aynı yeni şifre girilir
//        9	SAVE CHANGES' butonuna tıklanır
//        10	Değiştirilen şifre kontrol edilir


    Faker faker;
    HomePage homePage;
    AccountDetailsPage accountDetailsPage;
    SoftAssert softAssert;

    @Test
    public void passwordChangeWith10Char() {
        faker = new Faker();
        accountDetailsPage = new AccountDetailsPage();
        homePage =new HomePage();
        MyAccountPage myAccountPage = new MyAccountPage();
        ExtentReportsListener.extentTestInfo("8 karakterli eski şifreyi (12345Aa*), 10 karakterli yeni şifreye (12346Bb*12)  değiştirme testi ");
        ExtentReportsListener.extentTestInfo("Siteye yeni kullanıcı tanımlanarak giriş yapılır");
        Driver.getDriver().get(ConfigReader.getProperty("alloverCommerceUrl"));
        homePage.registerButton.click();
        homePage.registerUserName.sendKeys(faker.name().username());
        //  Your Email address icin gecerli bir data girilir
        homePage.registerEmail.sendKeys(faker.internet().emailAddress());
        //  Password icin gecerli bir data girilir
        homePage.registerPassword.sendKeys(ConfigReader.getProperty("password"));
        //  "I agree to privacy policy" checkbox i isaretlenir
        if (!homePage.registerPrivacyPolicyCheckBox.isSelected()) {homePage.registerPrivacyPolicyCheckBox.click();}
        //  Sign Up butonuna tiklanir
        homePage.registerSingUpButton.click();
        // Sing out butonuna tıklanır
        homePage.homeSignOut.click();
        // Çıkan alert tıklanarak kapatılır
        myAccountPage.alertMessage.click();
        //Account Details e tıklanarak değişikliklerin yapılabileceği My Account sayfasına girilir
        JSUtils.JSclickWithTimeout(myAccountPage.accountDetailsButton);
        ExtentReportsListener.extentTestInfo("Login girişi yapılıp My Account' sayfasına ve sonrasında" +
                " Account Details'a tıklanıp hesap bilgilerin olduğu sayfaya gidildi");
        // First ve last name data ları girilir
        accountDetailsPage.firstNameAccountDetails.sendKeys(faker.name().firstName());
        accountDetailsPage.lastNameAccountDetails.sendKeys(faker.name().lastName());
        // sayfa aşağı scroll edilir
        JSUtils.JSscrollIntoView(accountDetailsPage.passwordCurrentAccountDetails);
        WaitUtils.waitForVisibility(accountDetailsPage.passwordCurrentAccountDetails, 10);
        // current, new ve confirm password bölümleri doldurulur
        accountDetailsPage.passwordCurrentAccountDetails.sendKeys(ConfigReader.getProperty("password"));
        accountDetailsPage.passwordNewAccountDetails.sendKeys(ConfigReader.getProperty("tenCharacterConfirmPasswordAccountDetails"));
        accountDetailsPage.passwordConfirmAccountDetails.sendKeys(ConfigReader.getProperty("tenCharacterConfirmPasswordAccountDetails"));
        JSUtils.JSscrollIntoView(accountDetailsPage.saveChangesAccountDetails);
        WaitUtils.waitFor(2);
        ExtentReportsListener.extentTestInfo("Değişiklikleri kaydedin butonu aktifleşti ve tıklandı");
        accountDetailsPage.saveChangesAccountDetails.click();
        String message = accountDetailsPage.alertAccountDetails.getText();
        ExtentReportsListener.extentTestInfo(message+ "       mesajı alınır");
        ExtentReportsListener.extentTestInfo("Şifre değişikliği üst kısımda çıkan allert mesajı ile doğrulandı");
        Assert.assertTrue(message.contains("changed successfully"));
        //ExtentReportsListener.extentTestInfo("Bir önceki testte en az 12 karakter uzunluğunda şifre gereklidir uyarısına rağmen 10 karakterli bir şifre ile test geçmiştir");
        ExtentReportsListener.extentTestPass("Şifre değişikliği testi geçti");

    }


    @Test(description = "negatif scenario, tek karakterli  şifre değişikliği kabul edilmemeli" )
    public void passwordChangeWithSingleChar() {
        faker = new Faker();
        accountDetailsPage = new AccountDetailsPage();
        homePage = new HomePage();
        softAssert = new SoftAssert();
        MyAccountPage myAccountPage = new MyAccountPage();
        ExtentReportsListener.extentTestInfo("8 karakterli eski şifreyi (12345Aa*), tek karakterli yeni şifreye (1) değiştirme testi ");
        ExtentReportsListener.extentTestInfo("Siteye yeni kullanıcı tanımlanarak giriş yapıldı");
        Driver.getDriver().get(ConfigReader.getProperty("alloverCommerceUrl"));
        homePage.registerButton.click();
        homePage.registerUserName.sendKeys(faker.name().username());
        //  Your Email address icin gecerli bir data girilir
        homePage.registerEmail.sendKeys(faker.internet().emailAddress());
        //  Password icin gecerli bir data girilir
        homePage.registerPassword.sendKeys(ConfigReader.getProperty("password"));
        //  "I agree to privacy policy" checkbox i isaretlenir
        if (!homePage.registerPrivacyPolicyCheckBox.isSelected()) {homePage.registerPrivacyPolicyCheckBox.click();}
        //  Sign Up butonuna tiklanir
        homePage.registerSingUpButton.click();
        // Sing out butonuna tıklanır
        homePage.homeSignOut.click();
        // Çıkan alert tıklanarak kapatılır
        myAccountPage.alertMessage.click();
        //Account Details e tıklanarak değişikliklerin yapılabileceği My Account sayfasına girilir
        JSUtils.JSclickWithTimeout(myAccountPage.accountDetailsButton);
        ExtentReportsListener.extentTestInfo("Login girişi yapılıp My Account' sayfasına ve sonrasında" +
                " Account Details'a tıklanıp hesap bilgilerin olduğu sayfaya gidildi");
        // First ve last name data ları girilir
        accountDetailsPage.firstNameAccountDetails.sendKeys(faker.name().firstName());
        accountDetailsPage.lastNameAccountDetails.sendKeys(faker.name().lastName());
        // sayfa aşağı scroll edilir
        JSUtils.JSscrollIntoView(accountDetailsPage.passwordCurrentAccountDetails);
        WaitUtils.waitForVisibility(accountDetailsPage.passwordCurrentAccountDetails, 10);
        // password alanları doldurulur
        accountDetailsPage.passwordCurrentAccountDetails.sendKeys(ConfigReader.getProperty("password"));
        accountDetailsPage.passwordNewAccountDetails.sendKeys(ConfigReader.getProperty("invalidUserPassword"));
        accountDetailsPage.passwordConfirmAccountDetails.sendKeys(ConfigReader.getProperty("invalidUserPassword"));
        JSUtils.JSscrollIntoView(accountDetailsPage.saveChangesAccountDetails);
        WaitUtils.waitFor(2);
        ExtentReportsListener.addScreenshotToReport("tek karakter ile şifre güncelleme");
        ExtentReportsListener.extentTestInfo("Aktifleşmemesi gereken değişiklikleri kaydedin butonu aktifleşti ve tıklandı");
        softAssert.assertFalse(accountDetailsPage.saveChangesAccountDetails.isEnabled());
        accountDetailsPage.saveChangesAccountDetails.click();
        String message = accountDetailsPage.alertAccountDetails.getText();
        ExtentReportsListener.extentTestInfo("Alınan  mesaj:  " +message);
        ExtentReportsListener.extentTestInfo("Yapılamaması gereken şifre değişikliğinin gerçekleştiği doğrulanır");
        softAssert.assertFalse(message.contains("changed successfully"));;
        ExtentReportsListener.extentTestFail("Tek karakterli şifre kabul edilmiş, test başarısız olmuştur");
        softAssert.assertAll();

    }

    @AfterMethod
    public void tearDown() {
        try {
             Driver.quitDriver();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
