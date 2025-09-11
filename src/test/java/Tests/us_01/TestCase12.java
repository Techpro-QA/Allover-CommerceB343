package Tests.us_01;

import org.testng.annotations.Test;
import utilities.ConfigReader;
import utilities.ExtentReportsListener;

public class TestCase12 extends TestBase_US01 {

    @Test (description = "negatif scenario")
    public void afterPointInvalidEmail() {
        ExtentReportsListener.extentTestInfo("email e noktadan sonra .123456 ile kayıt yapılmaya çalışılır ve  login olunamaması beklenir");
        // (1 ve 2 extends TestBase ile yapılır)
        // 3 Acilan pencerede Username icin gecerli bir data girilir
        alloverCommercePage.userName.sendKeys(faker.name().username());
        //  4 Your Email address  icin "." dan sonra geçersiz data girilir (.123456)
        ExtentReportsListener.extentTestInfo("email e name@gmail.123456 girilir");
        alloverCommercePage.email.sendKeys(faker.name().firstName()+"@gmail.123456");
        // 5 Password icin gecerli bir data girilir
        alloverCommercePage.password.sendKeys(ConfigReader.getProperty("password"));
        // 6 "I agree to privacy policy" checkbox i isaretlenir
        if (!alloverCommercePage.checkBox.isSelected()) {alloverCommercePage.checkBox.click();}
        // 7 Sign Up butonuna tiklanir
        alloverCommercePage.singUpButton.click();
        // 8 Kayit isleminin gerceklesmedigi doğrulanir
        // a) Açılan pencere altında uyarı mesajı alınır
        ExtentReportsListener.extentTestInfo("Açılan pop up  uyarı mesajının kontrolü yapılır");
        softAssert.assertFalse(alloverCommercePage.bugEmailErrorMessage.isEmpty());
        //  b) Kayıt yapılarak girilen sayfaya girilemediği doğrulanır
        ExtentReportsListener.extentTestInfo("Uyarı mesajı çıkmasına rağmen login olarak sayfaya girilemediği doğrulanır");
        softAssert.assertTrue(alloverCommercePage.singOutButton.isEmpty());
        ExtentReportsListener.extentTestFail("Email de noktadan sonra hiçbir mailde kullanılmayan '123456' girilmiş " +
                "geçersiz email e ait uyarı mesajı alınmamış ve sayfaya girilememesi gerekirken login olarak girilmiştir.");
        softAssert.assertAll();
    }

}
