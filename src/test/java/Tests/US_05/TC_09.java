package Tests.US_05;

import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.ExtentReportsListener;
import utilities.JSUtils;
import utilities.WaitUtils;

public class TC_09 extends TestBase_US05 {
    //    1	My Account' sayfasına gidilir
//        2	Account Details' sayfasına gidilir
//        3	Hesap bilgileri görülür
//        4	Last name boş bırakılır , First name, Display name ve Email address alanlarında değişiklik yapılmaz
//        5	SAVE CHANGES' butonuna tıklanır
//        6	Hata mesajı alınmalı


    @Test(description = "negatif scenario")
    public void updateWithEmptyLastName() {
        ExtentReportsListener.extentTestInfo("Login girişi yapılıp My Account' sayfasına ve sonrasında" +
                " Account Details'a tıklanıp hesap bilgilerin olduğu sayfaya gidildi");
        //Account Details' sayfasına gidilir
        accountDetailsPage.lastNameAccountDetails.clear();
        ExtentReportsListener.extentTestInfo("Last name boş bırakıldı, diğer alanlara değişiklik yapılmadı ");
        JSUtils.JSclickWithTimeout(accountDetailsPage.saveChangesAccountDetails);
        ExtentReportsListener.extentTestInfo("Last name boş olduğuna ait aşağıdaki  uyarı mesajı alındı");
        WaitUtils.waitForVisibility(accountDetailsPage.lastNameMissingMessagesAccountDetails, 10);
        String message = accountDetailsPage.lastNameMissingMessagesAccountDetails.getText();
        ExtentReportsListener.extentTestInfo("Uyarı mesajı:  " + message);
        Assert.assertEquals(message, "LAST NAME is a required field.");
        ExtentReportsListener.extentTestPass("Last name olmadan update in başarısız olduğu  doğrulandı ve test geçti");
    }
}
