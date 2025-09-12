package Tests.us_01;

import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.ConfigReader;
import utilities.ExtentReportsListener;

public class TestCase09 extends TestBase_US01 {
//    1	Sayfaya gidilir
//    2	Register a tiklanir
//    3	Acilan pencerede Username icin gecerli bir data girilir
//    4	Your Email address icin gecerli data girilir
//    5	Password icin gecersiz bir data girilir
//    6	"I agree to privacy policy" checkbox i isaretlenir
//    7	Sign Up butonuna tiklanir
//    8	Kayit isleminin basarili bir sekilde gerceklesmedigi doğrulanir


    @Test (description = "negatif scenario")
    public void missedCharecterPassword() {
        ExtentReportsListener.extentTestInfo("passworda '1' girilir ve login olunamaz");
        //(1 ve 2 extends TestBase ile yapılır)
        // 3 Acilan pencerede Username icin gecerli bir data girilir
        alloverCommercePage.userName.sendKeys(faker.name().username());
        // 4 Your Email address icin gecerli bir data girilir
        alloverCommercePage.email.sendKeys(faker.internet().emailAddress());
        // 5 Password icin tek karakterli geçersiz data girilir
        alloverCommercePage.password.sendKeys(ConfigReader.getProperty("invalidUserPassword"));
        // 6 "I agree to privacy policy" checkbox i isaretlenir
        if (!alloverCommercePage.checkBox.isSelected()) {alloverCommercePage.checkBox.click();}
        // 7 Sign Up butonuna tiklanir
        alloverCommercePage.singUpButton.click();
        ExtentReportsListener.extentTestInfo("Login olarak sayfaya girilmediği doğrulanır");
        // 8 Kayit isleminin gerceklesmedigi doğrulanir
        softAssert.assertTrue(alloverCommercePage.singOutButton.isEmpty());
        ExtentReportsListener.extentTestInfo("US1 e göre 'Parola en az 8 karakter uzunluğunda olmalıdır. " +
                "Daha güçlü hale getirmek için büyük ve küçük harfler, sayılar ve ! ? $ % ^ & kullanılmalıdır.'" +
                "kuralına uygun olmayan tek karakterli parola (1) kabul adildi" +
                " Doğrulama başarısız oldu");
        ExtentReportsListener.extentTestFail("Login olarak giriş yapıldı test başarısız oldu");
        softAssert.assertAll();


    }
}
