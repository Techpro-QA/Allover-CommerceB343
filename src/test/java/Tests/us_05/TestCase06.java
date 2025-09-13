package Tests.us_05;

import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.ConfigReader;
import utilities.ExtentReportsListener;
import utilities.JSUtils;
import utilities.WaitUtils;

public class TestCase06 extends TestBase_US05 {
//        1	My Account' sayfasına gidilir
//        2	Account Details' sayfasına gidilir
//        3	Hesap bilgileri görülür
//        4	First name, Last name, Display name ve Email address alanlarında değişiklik yapılmaz
//        5	Password change' kısmına gidilir
//        6	Current password' kısmına eski şifre girilir
//        7	New password' kısmı boş bırakılır
//        8	Confirm password' kısmına yeni şifre girilir
//        9	SAVE CHANGES' butonuna tıklanır
//        10	Hata mesajı alınmalı


    @Test(description = "negatif scenario")
    public void passwordChangeWithEmptyNewP() {
        ExtentReportsListener.extentTestInfo("Login girişi yapılıp My Account' sayfasına ve sonrasında" +
                " Account Details'a tıklanıp hesap bilgilerin olduğu sayfaya gidildi");
        //Password change' kısmına gidilir
        JSUtils.JSscrollIntoView(accountDetailsPage.passwordConfirmAccountDetails);
        WaitUtils.waitForVisibility(accountDetailsPage.passwordCurrentAccountDetails, 10);
        // Current password ve Confirm password' kısmına aynı yeni şifre girilir
        accountDetailsPage.passwordCurrentAccountDetails.sendKeys(ConfigReader.getProperty("password"));
        accountDetailsPage.passwordConfirmAccountDetails.sendKeys(ConfigReader.getProperty("tenCharacterConfirmPasswordAccountDetails"));
        JSUtils.JSscrollIntoView(accountDetailsPage.saveChangesAccountDetails);
        WaitUtils.waitFor(2);
        ExtentReportsListener.extentTestInfo("New password boş bırakıldı, current ve confirm password kısımları uygun passwordler ile dolduruldu" +
                " ve değişiklikleri kaydedin butonuna basıldı");
        accountDetailsPage.saveChangesAccountDetails.click();
        //SAVE CHANGES' butonuna tıklanır
        WaitUtils.waitForVisibility(accountDetailsPage.passwordMissingMessagesAccountDetails, 3);
        String text = accountDetailsPage.passwordMissingMessagesAccountDetails.getText();
        ExtentReportsListener.extentTestInfo("New password boş olduğuna ait aşağıdaki  uyarı mesajı alınır");
        ExtentReportsListener.extentTestInfo(text);
        Assert.assertEquals(text, "New passwords do not match.");
        ExtentReportsListener.extentTestPass("Password değişikliğinin başarısız olduğu uyarı mesajı ile doğrulandı ve test geçti ");

    }
}
