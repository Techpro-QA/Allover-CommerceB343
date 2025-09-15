package Tests.US_01;

import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.ConfigReader;
import utilities.ExtentReportsListener;

public class TC_07 extends TestBase_US01 {
//    1	Sayfaya gidilir
//    2	Register a tiklanir
//    3	Acilan pencerede Username icin gecerli bir data girilir
//    4	Your Email address icin "@" olmayan data girilir
//    5	Password icin gecerli bir data girilir
//    6	"I agree to privacy policy" checkbox i isaretlenir
//    7	Sign Up butonuna tiklanir
//    8	Kayit isleminin gerceklesmedigi doğrulanir
// a) Email üzerinde açılan pop up
// "Lütfen e-posta adresine bir "@" işareti ekleyin. "example343gmail.com" adresinde "@" eksik."uyarı mesajı alınır
// b) Kayıt yapılarak girilen sayfaya girilemediği doğrulanır

    @Test (description = "negatif scenario")
    public void emailWithout_At() {
        ExtentReportsListener.extentTestInfo("email @ olmadan yazılır ve  login olunamaması beklenir");
        //(1 ve 2 extends TestBase ile yapılır)
        //  3 Acilan pencerede Username icin gecerli bir data girilir
        alloverCommercePage.registerUserName.sendKeys(faker.name().username());
        // 4 Your Email address icin "@" olmayan  data girilir
        alloverCommercePage.registerEmail.sendKeys(ConfigReader.getProperty("emailWithOut@"));
        // 5 Password icin gecerli bir data girilir
        alloverCommercePage.registerPassword.sendKeys(ConfigReader.getProperty("password"));
        // 6 "I agree to privacy policy" checkbox i isaretlenir
        if (!alloverCommercePage.registerPrivacyPolicyCheckBox.isSelected()) {alloverCommercePage.registerPrivacyPolicyCheckBox.click();}
        // 7 Sign Up butonuna tiklanir
        alloverCommercePage.registerSingUpButton.click();
        // 8 Kayit isleminin gerceklesmedigi doğrulanir
        // a) Email üzerinde açılan pop up
        // "Lütfen e-posta adresine bir "@" işareti ekleyin. "example343gmail.com" adresinde "@" eksik."uyarı mesajı alınır
        String validationMessage = alloverCommercePage.registerEmail.getAttribute("validationMessage");
        ExtentReportsListener.extentTestInfo("pop up uyarı mesajı: "+validationMessage);
        ExtentReportsListener.extentTestInfo("Açılan pop up  uyarı mesajının kontrolü yapılır");
        softAssert.assertTrue(validationMessage.contains("@"));
        //  b) Kayıt yapılarak girilen sayfaya girilemediği doğrulanır
        ExtentReportsListener.extentTestInfo("Uyarı mesajı çıkmasına rağmen login olarak sayfaya girilemediği doğrulanır");
        Assert.assertTrue(alloverCommercePage.singOutButton.isEmpty());
        softAssert.assertAll();
        ExtentReportsListener.extentTestPass("@ işareti olmayan (example343gmail.com) email ile sing up butonuna tıklandıktan sonra uyrı mesajı alındığı" +
                " ve login olarak sayfaya girilmediği başarılı bir şekilde doğrulanmıştır.");
    }
}
