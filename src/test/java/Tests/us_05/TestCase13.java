package Tests.us_05;

import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.ConfigReader;
import utilities.ExtentReportsListener;
import utilities.JSUtils;
import utilities.WaitUtils;

public class TestCase13 extends TestBase_US05 {
    //    1	My Account' sayfasına gidilir
//        2	Account Details' sayfasına gidilir
//        3	Hesap bilgileri görülür
//        4	Email addresi .com işareti olmadan yazılır. First, Last ve Display name alanlarında değişiklik yapılmaz
//        5	SAVE CHANGES' butonuna tıklanır
//        6	Hata mesajı alınmalı


    @Test(description = "negatif scenario")
    public void updateEmailWithİnvalidData() {
        ExtentReportsListener.extentTestInfo("Login girişi yapılıp My Account' sayfasına ve sonrasında" +
                " Account Details'a tıklanıp hesap bilgilerin olduğu sayfaya gidildi");
        //Account Details' sayfasına gidilir
        accountDetailsPage.emailAccountDetails.clear();
        accountDetailsPage.emailAccountDetails.sendKeys(ConfigReader.getProperty("invalidUserEmail"));
        ExtentReportsListener.extentTestInfo("Email 'example343@gmail' olarak girildi, diğer alanlara değişiklik yapılmadı ");
        JSUtils.JSclickWithTimeout(accountDetailsPage.saveChangesAccountDetails);
        ExtentReportsListener.extentTestInfo("Email '.com'  olmadan girildiğinde  aşağıdaki  uyarı mesajı alındı");
        WaitUtils.waitForVisibility(accountDetailsPage.passwordMissingMessagesAccountDetails, 10);
        String message = accountDetailsPage.passwordMissingMessagesAccountDetails.getText();
        ExtentReportsListener.extentTestInfo("Uyarı mesajı:  " + message);
        Assert.assertEquals(message, "Please provide a valid email address.");
        ExtentReportsListener.extentTestPass(".com olmadan update in başarısız olduğu doğrulandı ve test geçti");
    }


}
