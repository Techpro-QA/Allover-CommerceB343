package Tests.us_05;

import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.*;

public class TestCase14 extends TestBase_US05 {
    //    1	My Account' sayfasına gidilir
//        2	Account Details' sayfasına gidilir
//        3	Hesap bilgileri görülür
//        4	Email addresi example@343gmail.1234567 olarak girilir. First, Last ve Display name alanlarında değişiklik yapılmaz
//        5	SAVE CHANGES' butonuna tıklanır
//        6	Hata mesajı alınmalı


    @Test(description = "negatif scenario")
    public void updateEmailWithinvalidAfterPoint() {
        ExtentReportsListener.extentTestInfo("Login girişi yapılıp My Account' sayfasına ve sonrasında" +
                " Account Details'a tıklanıp hesap bilgilerin olduğu sayfaya gidildi");
        //Account Details' sayfasına gidilir
        accountDetailsPage.emailAccountDetails.clear();
        accountDetailsPage.emailAccountDetails.sendKeys(ConfigReader.getProperty("invalidAfterPointEmail2"));
        ExtentReportsListener.addScreenshotToReport("geçersiz email");
        ExtentReportsListener.extentTestInfo("Email 'example343@gmail.1234567' olarak girildi, diğer alanlara değişiklik yapılmadı ");
        JSUtils.JSclickWithTimeout(accountDetailsPage.saveChangesAccountDetails);
        ExtentReportsListener.extentTestInfo("Email geçersiz girildiğinde ait sayfada uyarı mesajı alınır");
        ExtentReportsListener.addScreenshotToReport("Uyarı mesajı alınmadı");
        ExtentReportsListener.extentTestInfo("Değişikliklerin başarılı olduğunu gösteren mesajın alınmadığı kontrol edilir");
        WaitUtils.waitForVisibility(accountDetailsPage.alertAccountDetails, 10);
        String message = accountDetailsPage.alertAccountDetails.getText();
        ExtentReportsListener.extentTestInfo("Değişikliğin başarılı olduğuna ait mesaj:  "+ message);
        softAssert.assertFalse(message.contains("changed successfully"));
        ExtentReportsListener.extentTestFail("Mesajın kontrolü ile geçersiz emailin kabul edildiği doğrulandı ve test başarısız oldu");

        //aşağıda yazılan kodlar diğer testlerde hata oluşmaması için email i tekrar eski değerine atamak için yazıldı
        Driver.getDriver().navigate().refresh();
        WaitUtils.waitFor(5);
        accountDetailsPage.emailAccountDetails.clear();
        accountDetailsPage.emailAccountDetails.sendKeys(ConfigReader.getProperty("email"));
        JSUtils.JSclickWithTimeout(accountDetailsPage.saveChangesAccountDetails);
        softAssert.assertAll();
    }


}
