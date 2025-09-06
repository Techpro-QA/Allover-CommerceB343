package utilities;

import pages.HomeVendorPage;

public class LoginUtils {

        /**
         * Vendor olarak giriş yapar ve My Account sayfasına ulaşır.
         * Hem pozitif hem negatif senaryolarda kullanılabilir.
         *
         * @param username Kullanıcı adı
         * @param password Şifre
         */
        public static void loginAndNavigateToMyAccount(String username, String password) {
            ExtentReportsListener.extentTestInfo("Allover commerce sayfasina gidilir");
            Driver.getDriver().get(ConfigReader.getProperty("allowerceUrl"));
            HomeVendorPage homeVendorPage = new HomeVendorPage();
            ExtentReportsListener.extentTestInfo("Vendor username ve password ile giris yapilir");
            homeVendorPage.signInRegisterButton.click();
            homeVendorPage.username.clear();
            homeVendorPage.username.sendKeys(username);
            homeVendorPage.password.clear();
            homeVendorPage.password.sendKeys(password);
            homeVendorPage.signInButton.click();

            WaitUtils.waitFor(2);
            ExtentReportsListener.extentTestInfo("Sayfanin altindaki my account menusune tiklanir");
            JSUtils.JSscrollIntoView(homeVendorPage.textMyAccount);
            WaitUtils.waitForVisibility(homeVendorPage.textMyAccount, 2);
            BrowserUtils.verifyElementClickable(homeVendorPage.textMyAccount);
            JSUtils.JSclickWithTimeout(homeVendorPage.textMyAccount);
        }
    }
