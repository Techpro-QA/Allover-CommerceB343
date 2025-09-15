package Tests.US_05;

import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.ExtentReportsListener;
import utilities.JSUtils;
import utilities.WaitUtils;

public class TC_10 extends TestBase_US05 {

    //    1	My Account' sayfasına gidilir
//        2	Account Details' sayfasına gidilir
//        3	Hesap bilgileri görülür
//        4	Display name boş bırakılır , First name, Last name ve Email address alanlarında değişiklik yapılmaz
//        5	SAVE CHANGES' butonuna tıklanır
//        6	Hata mesajı alınmalı


    @Test(description = "negatif scenario")
    public void updateWithEmptyDisplayName() {
        ExtentReportsListener.extentTestInfo("Login girişi yapılıp My Account' sayfasına ve sonrasında" +
                " Account Details'a tıklanıp hesap bilgilerin olduğu sayfaya gidildi");
        //Account Details' sayfasına gidilir
        accountDetailsPage.displayNameAccountDetails.clear();
        ExtentReportsListener.extentTestInfo("Display name boş bırakıldı, diğer alanlara değişiklik yapılmadı ");
        JSUtils.JSclickWithTimeout(accountDetailsPage.saveChangesAccountDetails);
        ExtentReportsListener.extentTestInfo("Display name boş olduğuna ait aşağıdaki  uyarı mesajı alındı");
        WaitUtils.waitForVisibility(accountDetailsPage.displayNameMissingMessagesAccountDetails, 10);
        String message = accountDetailsPage.displayNameMissingMessagesAccountDetails.getText();
        ExtentReportsListener.extentTestInfo("Uyarı mesajı:  " + message);
        Assert.assertEquals(message, "DISPLAY NAME is a required field.");
        ExtentReportsListener.extentTestPass("Display name olmadan update in başarısız olduğu  doğrulandı ve test geçti");
    }


}
