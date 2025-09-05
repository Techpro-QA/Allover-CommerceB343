package Tests.Us_07;


import utilities.*;
import Pages.AcatPage;
import org.testng.annotations.Test;

public class compareTest {

    AcatPage acatPage = new AcatPage();
    @Test
    public void compareTest01() {

        //https://allovercommerce.com sayfasında
        //Kullanıcı en fazla 4 ürünü karşılaştırmak için seçebilmeli
        //Karşılaştıracağı ürünleri silip yeni ürünleri ekleyebilmeli
        //Seçtiği ürünleri karşılaştırabilmeli
        //Karşılatırma ekranından ürünleri silebilmeli

        //https://allovercommerce.com sayfasına gidilir
        Driver.getDriver().get(ConfigReader.getProperty("allowerCommerceUrl"));

        //Karsılastırılması istenen urun adı search e yazılır
        acatPage.searchBox.sendKeys("Bag");
        acatPage.searchClickButton.click();


        //İstenen ürün resmi üzerindeki compare butonuna tıklanıp 1. 2. 3. ve 4.ürün seçilir.
        ActionsUtils.hoverOver(acatPage.firstChoice);
       acatPage.firstChoice.click();
       Driver.getDriver().navigate().refresh();


        acatPage.secondChoice.click();
        acatPage.thirdChoice.click();
        acatPage.fourthChoice.click();


        //Ürün resmi üzerindeki compare butonuna tıklanıp 2.  3. ve 4.ürün seçilir.
        //Karşılaştırma sayfasında  start compare butonuna tıklanır.
        //Karşılastırma sayfasında istenen 4 urunun karşılaştırma listesinin görüldüğü  dogrulanır



    }

    @Test
    public void testUS_01() {
       // Sayfaya gidilir
        Driver.getDriver().get(ConfigReader.getProperty("allowerCommerceUrl"));

       // Register a tiklanir
        acatPage.register.click();

       // Acilan pencerede Username icin gecerli bir data girilir
        acatPage.usernameBox.sendKeys("username");

       // Your Email address icin gecerli bir data girilir
        acatPage.emailBox.sendKeys("email");

       // Password icin gecerli bir data girilir
        acatPage.passwordBox.sendKeys("123456789Aa");

       // "I agree to privacy policy" checkbox i isaretlenir
        acatPage.privacyPolicyBox.click();

       // Sign Up butonuna tiklanir
        acatPage.signUp.click();

       // Kayit isleminin basarili bir sekilde gerceklestigi doğrulanir

    }
}
