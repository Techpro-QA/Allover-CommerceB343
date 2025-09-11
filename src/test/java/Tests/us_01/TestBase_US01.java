package Tests.us_01;

import Pages.Homepage;
import com.github.javafaker.Faker;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;
import utilities.ConfigReader;
import utilities.Driver;

public class TestBase_US01 {//her class ta tekrar eden kodlar için parent class oluşturuldu

        // kayıtlı email ve username ile tekrar kayıt yapılamadığından dolayı bu ikisi için faker kullanıldı
        protected Faker faker;
        protected Homepage alloverCommercePage;
        SoftAssert softAssert;

        @BeforeMethod
        public void setUp() {
            faker = new Faker();
            alloverCommercePage = new Homepage();
            softAssert = new SoftAssert();
            //    1	Sayfaya gidilir
            //    2	Register a tiklanir
            Driver.getDriver().get(ConfigReader.getProperty("alloverCommerceUrl"));
            alloverCommercePage.registerButton.click();
        }

    @AfterMethod
    public void tearDown() {
        try {
            Driver.quitDriver();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

