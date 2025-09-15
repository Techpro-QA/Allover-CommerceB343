package Tests.US_05;

import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.ConfigReader;
import utilities.ExtentReportsListener;
import utilities.JSUtils;
import utilities.WaitUtils;

public class TC_07 extends TestBase_US05 {
    //    1	My Account' sayfasına gidilir
//        2	Account Details' sayfasına gidilir
//        3	Hesap bilgileri görülür
//        4	First name, Last name, Display name ve Email address alanlarında değişiklik yapılmaz
//        5	Password change' kısmına gidilir
//        6	Current password' kısmına eski şifre girilir
//        7	New password' kısmı yeni şifre girilir
//        8	Confirm password' kısmına boş bırakılır
//        9	SAVE CHANGES' butonuna tıklanır
//        10	Hata mesajı alınmalı


    @Test(description = "negatif scenario")
    public void passwordChangeWithEmptyConfirmP() {
        ExtentReportsListener.extentTestInfo("Login girişi yapılıp My Account' sayfasına ve sonrasında" +
                " Account Details'a tıklanıp hesap bilgilerin olduğu sayfaya gidildi");
        //Password change' kısmına gidilir
        JSUtils.JSscrollIntoView(accountDetailsPage.passwordConfirmAccountDetails);
        WaitUtils.waitForVisibility(accountDetailsPage.passwordNewAccountDetails, 10);
        // Current password ve New password' kısmına aynı yeni şifre girilir
        accountDetailsPage.passwordCurrentAccountDetails.sendKeys(ConfigReader.getProperty("password"));
        accountDetailsPage.passwordNewAccountDetails.sendKeys(ConfigReader.getProperty("tenCharacterConfirmPasswordAccountDetails"));
        JSUtils.JSclickWithTimeout(accountDetailsPage.saveChangesAccountDetails);
        ExtentReportsListener.extentTestInfo("Confirm password boş bırakılır, current ve new password kısımları uygun passwordler ile dolduruldu" +
                " ve değişiklikleri kaydedin butonuna basıldı");
        WaitUtils.waitForVisibility(accountDetailsPage.passwordMissingMessagesAccountDetails, 3);
        String text = accountDetailsPage.passwordMissingMessagesAccountDetails.getText();
        ExtentReportsListener.extentTestInfo("Confirm password boş olduğuna ait aşağıdaki  uyarı mesajı alındı");
        ExtentReportsListener.extentTestInfo("Uyarı mesajı:  " +  text);
        Assert.assertEquals(text, "Please re-enter your password.");
        ExtentReportsListener.extentTestPass("Password değişikliğinin başarısız olduğu  doğrulandı ve test geçti ");

    }
}
