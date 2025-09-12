package Tests.US_01;

import org.testng.annotations.Test;
import utilities.ExtentReportsListener;

public class TestCase04 extends TestBase_US01 {
//    1	Sayfaya gidilir
//    2	Register a tiklanir
//    3	Acilan pencerede Username icin gecerli bir data girilir
//    4	Your Email address icin gecerli data girilir
//    5	Password alani bos birakilir
//    6	"I agree to privacy policy" checkbox i isaretlenir
//    7	Sign Up butonuna tiklanir
//    8	Kayit isleminin gerceklesmedigi doğrulanir
//  a) Password üzerinde açılan pop up "Lütfen bu alanı doldurun." uyarı mesajı alınır
//  b) Kayıt yapılarak girilen sayfaya girilemediği doğrulanır

    @Test (description = "negatif scenario")
    public void emptyPassword()  {
        ExtentReportsListener.extentTestInfo("password boş bırakılır ve  login olunamaması beklenir");
        // (1 ve 2 extends TestBase ile yapılır)
        // 3 Acilan pencerede Username icin gecerli bir data girilir
        alloverCommercePage.userName.sendKeys(faker.name().username());
        // 4 Your Email address icin gecerli data girilir
        alloverCommercePage.email.sendKeys(faker.internet().emailAddress());
        // 5 Password boş bırakılır
        // 6 "I agree to privacy policy" checkbox i isaretlenir
        if (!alloverCommercePage.checkBox.isSelected()) {alloverCommercePage.checkBox.click();}
        // 7 Sign Up butonuna tiklanir
        alloverCommercePage.singUpButton.click();
        // 8 Kayit isleminin gerceklesmedigi doğrulanir
        //  a) Password üzerinde açılan pop up "Lütfen bu alanı doldurun." uyarı mesajı alınır
        // mesaj içeriği developerlar tarafından ileride değiştirilebileceği için NotNull testi yapıldı
        String validationMessage = alloverCommercePage.password.getAttribute("validationMessage");
        ExtentReportsListener.extentTestInfo("Açılan pop up  uyarı mesajının kontrolü yapılır");
        softAssert.assertTrue(validationMessage.contains("Lütfen"));
        //  b) Kayıt yapılarak girilen sayfaya girilemediği doğrulanır
        ExtentReportsListener.extentTestInfo("Uyarı mesajı çıkmasına rağmen login olarak sayfaya girilemediği doğrulanır");
        softAssert.assertTrue(alloverCommercePage.singOutButton.isEmpty());
        softAssert.assertAll();
        ExtentReportsListener.extentTestPass("Boş password ile sing up butonuna tıklandıktan sonra uyrı mesajı alındığı" +
                " ve login olarak sayfaya girilmediği başarılı bir şekilde doğrulanmıştır.");
    }
}
