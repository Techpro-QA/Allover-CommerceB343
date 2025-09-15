package Tests.US_05;

import Pages.AccountDetailsPage;
import Pages.HomePage;
import Pages.MyAccountPage;
import com.github.javafaker.Faker;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.JSUtils;


public class TestBase_US05 {//her class ta tekrar eden kodlar için parent class oluşturuldu

        protected Faker faker;
        protected AccountDetailsPage accountDetailsPage;
        protected HomePage homePage;
        protected MyAccountPage myaccountpage;
        SoftAssert softAssert;

        @BeforeMethod
        public void setUp() {
            faker = new Faker();
            myaccountpage = new MyAccountPage();
            accountDetailsPage = new AccountDetailsPage();
            homePage = new HomePage();
            softAssert = new SoftAssert();
//            1	Siteye gidilir ve sing in butonuna basılarak pencere açılır,
//            2	Açılan pencerede kayıtlı bir kullanıcı e posta adresi ve password ile login sayfasına girilir
//            3	"Remember me" checkboxına tıklanır
//            4	Alttaki Sign in butonuna tıklanır açılan pencere kapatılarak üye olarak sayfaya girilir
//            5 Sing out butonuna tıklanır
//            6 Alert tıklanarak kapatılır
//            7 Account Details bölümü scroll yapılıp görünür olduktan sonra tıklanarak My Account sayfasına giriş yapılır

            Driver.getDriver().get(ConfigReader.getProperty("alloverCommerceUrl"));
            //Sing in butonuna tıklanır
            homePage.homeSignIn.click();
            //Açılan pencerede kayıtlı bir kullanıcı veya posta adresi ve password girilir
            homePage.usernameOrEmailAddressTextBox.sendKeys(ConfigReader.getProperty("userName"));
            homePage.passwordTextBox.sendKeys(ConfigReader.getProperty("password"));
            if(!homePage.rememberMeCheckBox.isSelected()){homePage.rememberMeCheckBox.click();}
            //alttaki sing in buttonuna sayfaya login olarak girmek için tıklanır
            homePage.signInButton.click();
            //Login sayfasında sing out butonuna tıklanarak My Account sayfasına gidilir
            homePage.homeSignOut.click();
            // Çıkan allert tıklanarak kapatılır
            myaccountpage.alertMessage.click();
            // Değişikliklerin yapılacağı My account sayfasına girmek için Account Details butonuna tıklandı
            JSUtils.JSclickWithTimeout(myaccountpage.accountDetailsButton);


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

