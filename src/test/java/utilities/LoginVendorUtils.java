package utilities;

import Pages.HomeVendorPage;

public class LoginVendorUtils {

        /**
         * Vendor olarak giriş yapar ve My Account sayfasına ulaşır.
         * Hem pozitif hem negatif senaryolarda kullanılabilir.
         *
         * @param username Kullanıcı adı
         * @param password Şifre
         */
        public static void loginAndNavigateToMyAccount(HomeVendorPage homeVendorPage,String username, String password) {
            homeVendorPage.signInRegisterButton.click();
            homeVendorPage.username.clear();
            homeVendorPage.username.sendKeys(username);
            homeVendorPage.password.clear();
            homeVendorPage.password.sendKeys(password);
            homeVendorPage.signInButton.click();
            WaitUtils.waitFor(2);
            JSUtils.JSscrollIntoView(homeVendorPage.textMyAccount);
            WaitUtils.waitForVisibility(homeVendorPage.textMyAccount, 2);
            BrowserUtils.verifyElementClickable(homeVendorPage.textMyAccount);
            JSUtils.JSclickWithTimeout(homeVendorPage.textMyAccount);
        }
    }
