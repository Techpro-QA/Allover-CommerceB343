package utilities;

import Pages.HomePage;

public class LoginVendorUtils {

        /**
         * Vendor olarak giriş yapar ve My Account sayfasına ulaşır.
         * Hem pozitif hem negatif senaryolarda kullanılabilir.
         *
         * @param username Kullanıcı adı
         * @param password Şifre
         */
        public static void loginAndNavigateToMyAccount(String username, String password) {
            HomePage homePage = new HomePage();
            homePage.homeSignIn.click();
            homePage.usernameOrEmailAddressTextBox.clear();
            homePage.usernameOrEmailAddressTextBox.sendKeys(username);
            homePage.passwordTextBox.clear();
            homePage.passwordTextBox.sendKeys(password);
            homePage.signInButton.click();
            WaitUtils.waitFor(2);
            JSUtils.JSscrollIntoView(homePage.textMyAccount);
            WaitUtils.waitForVisibility(homePage.textMyAccount, 2);
            BrowserUtils.verifyElementClickable(homePage.textMyAccount);
            JSUtils.JSclickWithTimeout(homePage.textMyAccount);
        }
    }
