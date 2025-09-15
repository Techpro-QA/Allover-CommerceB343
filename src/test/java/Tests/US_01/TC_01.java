package Tests.US_01;

import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.ConfigReader;
import utilities.ExtentReportsListener;

public class TC_01 extends TestBase_US01 {
//    1	Sayfaya gidilir
//    2	Register a tiklanir
//    3	Acilan pencerede Username icin gecerli bir data girilir
//    4	Your Email address icin gecerli bir data girilir
//    5	Password icin gecerli bir data girilir
//    6	"I agree to privacy policy" checkbox i isaretlenir
//    7	Sign Up butonuna tiklanir
//    8	Kayit isleminin basarili bir sekilde gerceklestigi doğrulanir


    @Test (description = "başarılı bir şekilde login olunur")
    public void positiveScenario() {
        ExtentReportsListener.extentTestInfo("Tüm kısımlara uygun datalar girilir");
        //(1 ve 2 extends TestBase ile yapılır)
        //  3 Acilan pencerede Username icin gecerli bir data girilir
        //  (Küçük harf, büyük harf, rakam ve özel karakter içerebilmeli)
        ExtentReportsListener.extentTestInfo("username e isimden sonra 'Aa1.' concat edilerek testcase06 kontrolü burada yapılır ");
        alloverCommercePage.registerUserName.sendKeys(faker.name().username()+"Aa1.");
        // 4 Your Email address icin gecerli bir data girilir
        alloverCommercePage.registerEmail.sendKeys(faker.internet().emailAddress());
        // 5 Password icin gecerli bir data girilir
        alloverCommercePage.registerPassword.sendKeys(ConfigReader.getProperty("password"));
        // 6 "I agree to privacy policy" checkbox i isaretlenir
        if (!alloverCommercePage.registerPrivacyPolicyCheckBox.isSelected()) {alloverCommercePage.registerPrivacyPolicyCheckBox.click();}
        // 7 Sign Up butonuna tiklanir
        alloverCommercePage.registerSingUpButton.click();

        ExtentReportsListener.extentTestInfo("Kayit isleminin basarili bir sekilde gerceklestigi doğrulanir");
        Assert.assertTrue(!alloverCommercePage.homeSignOut.isDisplayed());
        ExtentReportsListener.extentTestPass("Uygun datalar girilerek sayfaya login olarak başarılı bir şekilde girilmiştir.");

    }
}
