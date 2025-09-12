package Tests.us_05;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.*;

import javax.swing.*;

public class TestCase03 extends TestBase_US05 {
    //        1	My Account' sayfasına gidilir
//        2	Account Details' sayfasına gidilir
//        3	Hesap bilgileri görülür
//        4	First name, Last name, Display name ve Email address alanlarında değişiklik yapılmaz
//        5	Biography' alanına açıklama gir
//        6	SAVE CHANGES' butonuna tıklanır
//        7	Kaydedilen açıklama kontrol edilir





    @Test
    public void biographyAbleToFill() {
        ExtentReportsListener.extentTestInfo("Login girişi yapılıp My Account' sayfasına ve sonrasında" +
                " Account Details'a tıklanıp hesap bilgilerin olduğu sayfaya gidildi");

        ExtentReportsListener.extentTestInfo("Hesap bilgileri görüntülenir");
        //biograpy alanı iframe içindedir
        Driver.getDriver().switchTo().frame("user_description_ifr");
        JSUtils.JSscrollIntoView(accountDetailsPage.biographyAccountDetails);
        accountDetailsPage.biographyAccountDetails.clear();
        ExtentReportsListener.extentTestInfo("Biograpy alanına text girildi");
        String expectedText = ConfigReader.getProperty("expectedText");
        accountDetailsPage.biographyAccountDetails.sendKeys(expectedText);
        //iframe den çıkılır ve save changes buttonuna tıklanıp sonrasında çıkan uyarı yazısı kapatılır
        Driver.getDriver().switchTo().defaultContent();
        JSUtils.JSclickWithTimeout(accountDetailsPage.saveChangesAccountDetails);
        JSUtils.JSclickWithTimeout(accountDetailsPage.alertShutAccountDetails);
        //girilen text doğrulama için alınır, bunun için tekrar iframe girilir
        Driver.getDriver().switchTo().frame("user_description_ifr");
        JSUtils.JSscrollIntoView(accountDetailsPage.biographyAccountDetails);
        ExtentReportsListener.extentTestInfo("Girilen text doğrulama testi için alındı");
        String text = accountDetailsPage.biographyAccountDetails.getDomProperty("innerText");
        ExtentReportsListener.extentTestInfo("Girilen text ve alının text karşılaştırıldı");
        Assert.assertEquals(text,expectedText);
        Driver.getDriver().switchTo().defaultContent();
        ExtentReportsListener.extentTestPass("doğrulama başarılı oldu test geçti");
    }
}
