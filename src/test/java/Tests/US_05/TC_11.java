package Tests.US_05;

import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.ExtentReportsListener;
import utilities.JSUtils;
import utilities.WaitUtils;

public class TC_11 extends TestBase_US05 {

    //    1	My Account' sayfasına gidilir
//        2	Account Details' sayfasına gidilir
//        3	Hesap bilgileri görülür
//        4	Email boş bırakılır , First, Last ve Display name  alanlarında değişiklik yapılmaz
//        5	SAVE CHANGES' butonuna tıklanır
//        6	Hata mesajı alınmalı


    @Test(description = "negatif scenario")
    public void updateWithEmptyEmail() {
        ExtentReportsListener.extentTestInfo("Login girişi yapılıp My Account' sayfasına ve sonrasında" +
                " Account Details'a tıklanıp hesap bilgilerin olduğu sayfaya gidildi");
        //Account Details' sayfasına gidilir
        accountDetailsPage.emailAccountDetails.clear();
        ExtentReportsListener.extentTestInfo("Email boş bırakıldı, diğer alanlara değişiklik yapılmadı ");
        JSUtils.JSclickWithTimeout(accountDetailsPage.saveChangesAccountDetails);
        ExtentReportsListener.extentTestInfo("Email boş olduğuna ait aşağıdaki  uyarı mesajı alındı");
        WaitUtils.waitForVisibility(accountDetailsPage.emailMissingMessagesAccountDetails,10);
        String message = accountDetailsPage.emailMissingMessagesAccountDetails.getText();
        ExtentReportsListener.extentTestInfo("Uyarı mesajı:  " + message);
        Assert.assertEquals(message,"EMAIL ADDRESS is a required field.");
        ExtentReportsListener.extentTestPass("Email olmadan update in başarısız olduğu  doğrulandı ve test geçti");
    }

}
