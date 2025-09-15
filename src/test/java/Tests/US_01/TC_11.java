package Tests.US_01;

import org.testng.annotations.Test;
import utilities.ConfigReader;
import utilities.ExtentReportsListener;

public class TC_11 extends TestBase_US01 {

    @Test (description = "negatif scenario")
    public void afterPointInvalidEmail() {
        ExtentReportsListener.extentTestInfo("email e noktadan sonra .123456 ile kayıt yapılmaya çalışılır ve login olunamaz");
        // (1 ve 2 extends TestBase ile yapılır)
        // 3 Acilan pencerede Username icin gecerli bir data girilir
        alloverCommercePage.registerUserName.sendKeys(faker.name().username());
        //  4 Your Email address  icin "." dan sonra geçersiz data girilir (.123456)
        ExtentReportsListener.extentTestInfo("email e name@gmail.123456 girilir");
        alloverCommercePage.registerEmail.sendKeys(faker.name().firstName()+"@gmail.123456");
        // 5 Password icin gecerli bir data girilir
        alloverCommercePage.registerPassword.sendKeys(ConfigReader.getProperty("password"));
        // 6 "I agree to privacy policy" checkbox i isaretlenir
        if (!alloverCommercePage.registerPrivacyPolicyCheckBox.isSelected()) {alloverCommercePage.registerPrivacyPolicyCheckBox.click();}
        // 7 Sign Up butonuna tiklanir
        alloverCommercePage.registerSingUpButton.click();
        // 8 Kayit isleminin gerceklesmedigi doğrulanir
        // a) Açılan pencere altında uyarı mesajı alınır
        ExtentReportsListener.extentTestInfo("Uyarı mesajının olup olmadığı kontrol edilir");
        softAssert.assertFalse(alloverCommercePage.bugEmailErrorMessage.isEmpty());
        //  b) Kayıt yapılarak girilen sayfaya girilemediği doğrulanır
        ExtentReportsListener.extentTestInfo("Uyarı mesajı çıkmadı, login olarak sayfaya girilemediği doğrulanır");
        softAssert.assertTrue(alloverCommercePage.homeSignOut.isDisplayed());
        ExtentReportsListener.extentTestFail("Email e noktadan sonra geçersiz '123456' rakamları girildi " +
                "geçersiz email e ait uyarı mesajı alınmadı ve sayfaya girilememesi gerekirken login olarak girildi." +
                "Test başarısız oldu");
        softAssert.assertAll();
    }

}
