package Tests.us_05;

import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.ConfigReader;
import utilities.ExtentReportsListener;
import utilities.JSUtils;
import utilities.WaitUtils;


public class TestCase02 extends TestBase_US05 {
    //        1	My Account' sayfasına gidilir
//        2	Account Details'a tıklanır
//        3	Hesap bilgileri görülür
//        4	First name alanı doldurulur
//        5	Last Name alanı doldurulur
//        6	Display name alanı doldurulur
//        7 E mail adres alanı doldurulur
//        8 SAVE CHANGES' butonuna tıklanır
//        9 Kaydedilen değişiklikler doğrulanır




    @Test (description = "Başarılı bir şekilde datalar değiştirilir")
    public void allDataChange() {
        ExtentReportsListener.extentTestInfo("Login girişi yapılıp My Account' sayfasına ve sonrasında" +
                " Account Details'a tıklanıp hesap bilgilerin olduğu sayfaya gidildi");

        ExtentReportsListener.extentTestInfo("Hesap bilgileri görüntülenir");
        ExtentReportsListener.extentTestInfo("First, Last, Display name ve email adresi değiştirilir");
        accountDetailsPage.firstNameAccountDetails.clear();
        accountDetailsPage.firstNameAccountDetails.sendKeys(ConfigReader.getProperty("firstNameAccountDetails"));
        accountDetailsPage.lastNameAccountDetails.clear();
        accountDetailsPage.lastNameAccountDetails.sendKeys(ConfigReader.getProperty("lastNameAccountDetails"));
        accountDetailsPage.displayNameAccountDetails.clear();
        accountDetailsPage.displayNameAccountDetails.sendKeys(ConfigReader.getProperty("userName"));
        accountDetailsPage.emailAccountDetails.clear();
        accountDetailsPage.emailAccountDetails.sendKeys(ConfigReader.getProperty("email"));
        //save changes buttonuna tıklanır
        JSUtils.JSclickWithTimeout(accountDetailsPage.saveChangesAccountDetails);
        ExtentReportsListener.extentTestInfo("Değişikliklerin başarılı olduğunu gösteren aşağıdaki mesaj locate edilir");
        WaitUtils.waitForVisibility(accountDetailsPage.alertAccountDetails,10);
        String message = accountDetailsPage.alertAccountDetails.getText();
        ExtentReportsListener.extentTestInfo(message);
        Assert.assertTrue(message.contains("changed successfully"));
        ExtentReportsListener.extentTestPass("Doğrulama mesajı kontrolü ile testin başarılı olduğu doğrulanır");
    }
}
