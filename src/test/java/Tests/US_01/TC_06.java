package Tests.US_01;

import Tests.US_01.TestBase_US01;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.ConfigReader;
import utilities.ExtentReportsListener;

public class TC_06 extends TestBase_US01 {
    //    1	Sayfaya gidilir
//    2	Register a tiklanir
//    3	Acilan pencerede Username icin "*" içeren bir data girilir
//    4	Your Email address icin gecerli bir data girilir
//    5	Password icin gecerli bir data girilir
//    6	"I agree to privacy policy" checkbox i isaretlenir
//    7	Sign Up butonuna tiklanir
//    8	Kayit isleminin basarili bir sekilde gerceklestigi doğrulanir


    @Test(description = "(Username e özel karakter '*' concat edilir")
    public void validUsername() {
        ExtentReportsListener.extentTestInfo("Acilan pencerede Username icin  'Aa1*' içeren bir data girilir");

        //  3 Acilan pencerede Username icin  "Aa1*" içeren bir data girilir
        //  (Username e Küçük harf, büyük harf, rakam ve nokta dışında bir özel özel karakter "*" girilir)
        ExtentReportsListener.extentTestInfo("username e isimden sonra 'Aa1*' concat edilir ");
        alloverCommercePage.registerUserName.sendKeys(faker.name().username()+"Aa1*");
        // 4 Your Email address icin gecerli bir data girilir
        alloverCommercePage.registerEmail.sendKeys(faker.internet().emailAddress());
        // 5 Password icin gecerli bir data girilir
        alloverCommercePage.registerPassword.sendKeys(ConfigReader.getProperty("password"));
        // 6 "I agree to privacy policy" checkbox i isaretlenir
        if (!alloverCommercePage.registerPrivacyPolicyCheckBox.isSelected()) {alloverCommercePage.registerPrivacyPolicyCheckBox.click();}
        // 7 Sign Up butonuna tiklanir
        alloverCommercePage.registerSingUpButton.click();

        ExtentReportsListener.extentTestInfo("Kayit isleminin basarili bir sekilde gerceklestigi doğrulanir");
        Assert.assertTrue(!alloverCommercePage.singOutButton.isEmpty());
        ExtentReportsListener.extentTestFail("Doğrulama başarısız oldu. " +
                "TestCase01 de username e concat edilen 'Aa1.' 'Aa1*' ile değiştirilmiş," +
                "nokta TestCase01 de geçerken '*' kabul edilmemiş ve test başarısız olmuştur");

    }
}
