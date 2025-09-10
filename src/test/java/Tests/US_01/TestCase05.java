package Tests.US_01;

import org.testng.annotations.Test;
import utilities.ConfigReader;
import utilities.ExtentReportsListener;

public class TestCase05 extends TestBase_US01 {
//    1	Sayfaya gidilir
//    2	Register a tiklanir
//    3	Acilan pencerede Username icin gecerli bir data girilir
//    4	Your Email address icin gecerli data girilir
//    5	Password icin gecerli bir data girilir
//    6	"I agree to privacy policy" checkbox i bos birakilir
//    7	Sign Up butonuna tiklanir
//    8	Kayit isleminin gerceklesmedigi doğrulanir
    // a) Checkbox üzerinde açılan pop up "İlerlemek istiyorsanız lütfen bu kutuyu işaretleyin." uyarı mesajı alınır
    //b) Kayıt yapılarak girilen sayfaya girilemediği doğrulanır

    @Test (description = "negatif scenario")
    public void checkBoxNotSelected() {
        ExtentReportsListener.extentTestInfo("checkbox işaretlenmez ve  login olunamaması beklenir");
        //(1 ve 2 extends TestBase ile yapılır)
        //  3 Acilan pencerede Username icin gecerli bir data girilir
        alloverCommercePage.userName.sendKeys(faker.name().username());
        // 4 Your Email address icin gecerli bir data girilir
        alloverCommercePage.email.sendKeys(faker.internet().emailAddress());
        // 5 Password icin gecerli bir data girilir
        alloverCommercePage.password.sendKeys(ConfigReader.getProperty("password"));
        // 6 "I agree to privacy policy" checkbox i işaretliyse işaret  kaldırılır
        if (alloverCommercePage.checkBox.isSelected()) {alloverCommercePage.checkBox.click();}
        // 7 Sign Up butonuna tiklanir
        alloverCommercePage.singUpButton.click();
        // 8 Kayit isleminin gerceklesmedigi doğrulanir
        //  a) Checkbox üzerinde açılan pop up "İlerlemek istiyorsanız lütfen bu kutuyu işaretleyin." uyarı mesajı alınır
        String validationMessage = alloverCommercePage.checkBox.getAttribute("validationMessage");
        ExtentReportsListener.extentTestInfo("Açılan pop up  uyarı mesajının kontrolü yapılır");
        softAssert.assertTrue(validationMessage.contains("kutuyu"));
        //  b) Kayıt yapılarak girilen sayfaya girilemediği doğrulanır
        ExtentReportsListener.extentTestInfo("Uyarı mesajı çıkmasına rağmen login olarak sayfaya girilemediği doğrulanır");
        softAssert.assertTrue(alloverCommercePage.singOutButton.isEmpty());
        softAssert.assertAll();
        ExtentReportsListener.extentTestPass("Checkbox işaretlenmeden sing up butonuna tıklandıktan sonra uyrı mesajı alındığı" +
                " ve login olarak sayfaya girilmediği başarılı bir şekilde doğrulanmıştır.");
    }

}
