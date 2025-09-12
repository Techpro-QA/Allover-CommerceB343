package Tests.us_05;

import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.ConfigReader;
import utilities.ExtentReportsListener;
import utilities.JSUtils;
import utilities.WaitUtils;

public class TestCase08 extends TestBase_US05 {
//        1	My Account' sayfasına gidilir
//        2	Account Details' sayfasına gidilir
//        3	Hesap bilgileri görülür
//        4	First name boş bırakılır , Last name, Display name ve Email address alanlarında değişiklik yapılmaz
//        5	SAVE CHANGES' butonuna tıklanır
//        6	Hata mesajı alınmalı


    @Test(description = "negatif scenario")
    public void updateWithEmptyFirstName() {
        ExtentReportsListener.extentTestInfo("Login girişi yapılıp My Account' sayfasına ve sonrasında" +
                " Account Details'a tıklanıp hesap bilgilerin olduğu sayfaya gidildi");
        //Account Details' sayfasına gidilir
        accountDetailsPage.firstNameAccountDetails.clear();
        ExtentReportsListener.extentTestInfo("First name boş bırakılır, diğer alanlara değişiklik yapılmaz ");
        JSUtils.JSclickWithTimeout(accountDetailsPage.saveChangesAccountDetails);
        ExtentReportsListener.extentTestInfo("First name boş olduğuna ait aşağıdaki  uyarı mesajı alınır");
        WaitUtils.waitForVisibility(accountDetailsPage.firstNameMissingMessagesAccountDetails, 10);
        String message = accountDetailsPage.firstNameMissingMessagesAccountDetails.getText();
        ExtentReportsListener.extentTestInfo(message);
        Assert.assertEquals(message, "FIRST NAME is a required field.");
        ExtentReportsListener.extentTestPass("First name olmadan güncellemenin başarısız olduğu uyarı mesajı ile doğrulandı ve test geçti");
    }
}
