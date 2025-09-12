package Tests.us_01;

import org.testng.annotations.Test;
import utilities.ConfigReader;
import utilities.ExtentReportsListener;

public class TestCase08 extends TestBase_US01 {
//    1	Sayfaya gidilir
//    2	Register a tiklanir
//    3	Acilan pencerede Username icin gecerli bir data girilir
//    4	Your Email address icin ".com" olmayan data girilir
//    5	Password icin gecerli bir data girilir
//    6	"I agree to privacy policy" checkbox i isaretlenir
//    7	Sign Up butonuna tiklanir
//    8	Kayit isleminin gerceklesmedigi doğrulanir
//     a) Açılan pencere altında "Please provide a valid email address."uyarı mesajı alınır
//     b) Kayıt yapılarak girilen sayfaya girilemediği doğrulanır

    @Test (description = "negatif scenario")
    public void invalidEmail() {
        ExtentReportsListener.extentTestInfo("email .com olmadan yazılır ve  login olunamaması beklenir");
        //(1 ve 2 extends TestBase ile yapılır)
        //  3 Acilan pencerede Username icin gecerli bir data girilir
        alloverCommercePage.userName.sendKeys(faker.name().username());
        // 4 Your Email address icin ".com" olmayan  data girilir
        alloverCommercePage.email.sendKeys(ConfigReader.getProperty("invalidUserEmail"));
        // 5 Password icin gecerli bir data girilir
        alloverCommercePage.password.sendKeys(ConfigReader.getProperty("password"));
        // 6 "I agree to privacy policy" checkbox i isaretlenir
        if (!alloverCommercePage.checkBox.isSelected()) {alloverCommercePage.checkBox.click();}
        // 7 Sign Up butonuna tiklanir
        alloverCommercePage.singUpButton.click();
        // 8 Kayit isleminin gerceklesmedigi doğrulanir
        // a) Açılan pencere altında "Please provide a valid email address."uyarı mesajı alınır
        String emailErrorText = alloverCommercePage.emailErrorMessage.getText();
        ExtentReportsListener.extentTestInfo("Uyarı mesajı: "+emailErrorText);
        ExtentReportsListener.extentTestInfo("Açılan uyarı mesajının kontrolü yapılır");
        softAssert.assertTrue(emailErrorText.contains("email"));
        //  b) Kayıt yapılarak girilen sayfaya girilemediği doğrulanır
        ExtentReportsListener.extentTestInfo("Uyarı mesajı çıkmasına rağmen login olarak sayfaya girilemediği doğrulanır");
        softAssert.assertTrue(alloverCommercePage.singOutButton.isEmpty());
        softAssert.assertAll();
        ExtentReportsListener.extentTestPass(".com olmayan (example343@gmail) email ile sing up butonuna tıklandıktan sonra uyrı mesajı alındığı" +
                " ve login olarak sayfaya girilmediği başarılı bir şekilde doğrulanmıştır.");
    }
}

