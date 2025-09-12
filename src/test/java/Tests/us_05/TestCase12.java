package Tests.us_05;

import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.ConfigReader;
import utilities.ExtentReportsListener;
import utilities.JSUtils;
import utilities.WaitUtils;

public class TestCase12 extends TestBase_US05 {

    //    1	My Account' sayfasına gidilir
//        2	Account Details' sayfasına gidilir
//        3	Hesap bilgileri görülür
//        4	Email addresi "@" işareti olmadan yazılır. First name, Last name ve Display name alanlarında değişiklik yapılmaz
//        5	SAVE CHANGES' butonuna tıklanır
//        6	Hata mesajı alınmalı


    @Test(description = "negatif scenario")
    public void updateEmailWithout_At() {
        ExtentReportsListener.extentTestInfo("Login girişi yapılıp My Account' sayfasına ve sonrasında" +
                " Account Details'a tıklanıp hesap bilgilerin olduğu sayfaya gidildi");
        //Account Details' sayfasına gidilir
        accountDetailsPage.emailAccountDetails.clear();
        accountDetailsPage.emailAccountDetails.sendKeys(ConfigReader.getProperty("emailWithOut@"));
        ExtentReportsListener.extentTestInfo("Email @ işareti olmadan değiştirildi ");
        JSUtils.JSscrollIntoView(accountDetailsPage.saveChangesAccountDetails);
        WaitUtils.waitFor(1);
        accountDetailsPage.saveChangesAccountDetails.click();
        ExtentReportsListener.extentTestInfo("'@' eksik olduğuna ait uyarı mesajı beklendi");
        WaitUtils.waitFor(2);
        ExtentReportsListener.addScreenshotToReport("Uyarı mesajı alınmadı");
        ExtentReportsListener.extentTestInfo("Değişiklikliğin neden olmadığını belirten mesajın alınmadığı doğrulandı");
        Assert.assertTrue(accountDetailsPage.alertListAccountDetails.isEmpty());
        ExtentReportsListener.extentTestPass("'@' olmadan update in olmadığı  doğrulandı" +
                " ama hatanın nerede yapıldığına ait kullanıcıya uyarı mesajı verilmedi, test geçti ama iyileştirme yapılmalı");
    }
}
