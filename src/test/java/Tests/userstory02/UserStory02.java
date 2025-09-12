package Tests.userstory02;

import Pages.HomePage;
import Pages.MyAccount;
import Pages.StoreManager;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utilities.ConfigReader;
import utilities.Driver;

public class UserStory02 {

    @DataProvider
    public static Object[][] LoginData() {
        return new Object[][]{

                //Siteye kayıtlı kullanıcı adı ve e-posta ile tekrar üye olunamamalı
                {"ekinoks", "ekinoks@gmail.com", "takimruhu"},

                //Siteye kayıtlı kullanıcı adı ve kayıtsız e-posta ile üye olunamamalı
                {"ekinoks", "ekinoks0661@abc.com", "takimruhu"},

                //Siteye kayıtsız kullanıcı adı ve siteye kayıtlı e-posta ile üye olunamamalı
                {"tekinoks", "ekinoks@gmail.com", "takimruhu"}
        };
    }

    @Test(dataProvider = "LoginData")
    public void cannotRegisterWithExistingData (String username, String email, String password) {
        Driver.getDriver().get(ConfigReader.getProperty("alloverCommerceUrl"));
        HomePage homePage = new HomePage();


        homePage.registerButton.click();
        homePage.userName.sendKeys(username);
        homePage.email.sendKeys(email);
        homePage.password.sendKeys(password);

        if (!homePage.checkBox.isSelected()) {
            homePage.checkBox.click();
        }

        homePage.singUpButton.click();

        Assert.assertTrue(homePage.loginErrorMessage.isDisplayed());

        Driver.quitDriver();

    }
}
