package Tests.us_01;

import org.testng.annotations.Test;
import utilities.ConfigReader;
import utilities.ExtentReportsListener;

public class TestCase10 extends TestBase_US01 {

    @Test (description = "negatif scenario")
    public void sameUserName() {
        ExtentReportsListener.extentTestInfo("daha önca kayıt yapılmış bir username ile kayıt yapılmaya çalışılır ve  login olunamaması beklenir");
        // (1 ve 2 extends TestBase ile yapılır)
        // 3 Acilan pencerede Username icin daha önce kayıtlı bir data girilir
        alloverCommercePage.userName.sendKeys(ConfigReader.getProperty("userName"));
        //  4 Your Email address icin gecerli bir data girilir
        alloverCommercePage.email.sendKeys(faker.internet().emailAddress());
        // 5 Password icin gecerli bir data girilir
        alloverCommercePage.password.sendKeys(ConfigReader.getProperty("password"));
        // 6 "I agree to privacy policy" checkbox i isaretlenir
        if (!alloverCommercePage.checkBox.isSelected()) {alloverCommercePage.checkBox.click();}
        // 7 Sign Up butonuna tiklanir
        alloverCommercePage.singUpButton.click();
        // 8 Kayit isleminin gerceklesmedigi doğrulanir
        //  a) sayfa alt kısmında "An account is already registered with that username. Please choose another." uyarı mesajı alınır
        String warningMessage = alloverCommercePage.sameUserNameErrorMessage.getText();
        ExtentReportsListener.extentTestInfo("Açılan pop up  uyarı mesajının kontrolü yapılır");
        softAssert.assertTrue(warningMessage.contains("username"));
        //  b) Kayıt yapılarak girilen sayfaya girilemediği doğrulanır
        ExtentReportsListener.extentTestInfo("Uyarı mesajı çıkmasına rağmen login olarak sayfaya girilemediği doğrulanır");
        softAssert.assertTrue(alloverCommercePage.singOutButton.isEmpty());
        softAssert.assertAll();
        ExtentReportsListener.extentTestPass("Daha önca kayıt yapılmış username ile sing up butonuna tıklandıktan sonra uyrı mesajı alındığı" +
                " ve login olarak sayfaya girilmediği başarılı bir şekilde doğrulanmıştır.");
    }

    @Test (description = "negatif scenario")
    public void sameEmail() {
        ExtentReportsListener.extentTestInfo("daha önca kayıt yapılmış bir email ile kayıt yapılmaya çalışılır ve  login olunamaması beklenir");
        // (1 ve 2 extends TestBase ile yapılır)
        // 3 Acilan pencerede Username icin gecerli bir data girilir
        alloverCommercePage.userName.sendKeys(faker.name().username());
        //  4 Your Email address  icin daha önce kayıtlı bir data girilir
        alloverCommercePage.email.sendKeys(ConfigReader.getProperty("email"));
        // 5 Password icin gecerli bir data girilir
        alloverCommercePage.password.sendKeys(ConfigReader.getProperty("password"));
        // 6 "I agree to privacy policy" checkbox i isaretlenir
        if (!alloverCommercePage.checkBox.isSelected()) {alloverCommercePage.checkBox.click();}
        // 7 Sign Up butonuna tiklanir
        alloverCommercePage.singUpButton.click();
        // 8 Kayit isleminin gerceklesmedigi doğrulanir
        //  a) sayfa alt kısmında "An account is already registered with that username. Please choose another." uyarı mesajı alınır
        String warningMessage = alloverCommercePage.sameEmailErrorMessage.getText();
        ExtentReportsListener.extentTestInfo("Açılan pop up  uyarı mesajının kontrolü yapılır");
        softAssert.assertTrue(warningMessage.contains("email"));
        //  b) Kayıt yapılarak girilen sayfaya girilemediği doğrulanır
        ExtentReportsListener.extentTestInfo("Uyarı mesajı çıkmasına rağmen login olarak sayfaya girilemediği doğrulanır");
        softAssert.assertTrue(alloverCommercePage.singOutButton.isEmpty());
        softAssert.assertAll();
        ExtentReportsListener.extentTestPass("Daha önca kayıt yapılmış emaile ile sing up butonuna tıklandıktan sonra uyrı mesajı alındığı" +
                " ve login olarak sayfaya girilmediği başarılı bir şekilde doğrulanmıştır.");
    }
}
