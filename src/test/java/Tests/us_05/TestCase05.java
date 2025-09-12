package Tests.us_05;

import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.ConfigReader;
import utilities.ExtentReportsListener;
import utilities.JSUtils;
import utilities.WaitUtils;

public class TestCase05 extends TestBase_US05 {
//        1	My Account' sayfasına gidilir
//        2	Account Details' sayfasına gidilir
//        3	Hesap bilgileri görülür
//        4	First name, Last name, Display name ve Email address alanlarında değişiklik yapılmaz
//        5	Password change' kısmına gidilir
//        6	Current password' kısmı boş bırakılır
//        7	New password' kısmına yeni şifre girilir
//        8	Confirm password' kısmına aynı yeni şifre girilir
//        9	SAVE CHANGES' butonuna tıklanır
//        10	Hata mesajı alınmalı


    @Test(description = "negatif scenario")
    public void passwordChangeWithEmptyCurrentP() {
        ExtentReportsListener.extentTestInfo("Login girişi yapılıp My Account' sayfasına ve sonrasında" +
                " Account Details'a tıklanıp hesap bilgilerin olduğu sayfaya gidildi");
        //Password change' kısmına gidilir
        JSUtils.JSscrollIntoView(accountDetailsPage.passwordCurrentAccountDetails);
        WaitUtils.waitForVisibility(accountDetailsPage.passwordCurrentAccountDetails, 10);
        // New password ve Confirm password' kısmına aynı yeni şifre girilir
        accountDetailsPage.passwordNewAccountDetails.sendKeys(ConfigReader.getProperty("tenCharacterConfirmPasswordAccountDetails"));
        accountDetailsPage.passwordConfirmAccountDetails.sendKeys(ConfigReader.getProperty("tenCharacterConfirmPasswordAccountDetails"));
        JSUtils.JSscrollIntoView(accountDetailsPage.saveChangesAccountDetails);
        WaitUtils.waitFor(2);
        ExtentReportsListener.extentTestInfo("Current password boş bırakıldı , new ve confirm password kısımlarına uygun password girildi" +
                " ve değişiklikleri kaydedin butonuna basıldı");
        //SAVE CHANGES' butonuna tıklanır
        accountDetailsPage.saveChangesAccountDetails.click();
        WaitUtils.waitForVisibility(accountDetailsPage.passwordMissingMessagesAccountDetails, 3);
        //alınan uyarı locate edilir
        String text = accountDetailsPage.passwordMissingMessagesAccountDetails.getText();
        ExtentReportsListener.extentTestInfo("Current password boş olduğuna ait aşağıdaki uyarı mesajı alındı");
        ExtentReportsListener.extentTestInfo("Uyarı mesaj:  " + text);
        Assert.assertEquals(text, "Please enter your current password.");
        ExtentReportsListener.extentTestPass("Password değişikliğinin başarısız olduğu uyarı mesajı ile doğrulandı ve test geçti ");

    }
}
