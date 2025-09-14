package utilities;

import Pages.HomeVendorPage;

public class LoginUtils {

    /**
     * Vendor olarak giriş yapar ve My Account sayfasına ulaşır.
     * Hem pozitif hem negatif senaryolarda kullanılabilir.
     *
     * @param username Kullanıcı adı
     * @param password Şifre
     */
    public static void loginAndNavigateToMyAccount(String username, String password) {
        Driver.getDriver().get(ConfigReader.getProperty("alloverCommerceUrl"));
        HomeVendorPage homeVendorPage = new HomeVendorPage();

        homeVendorPage.signInRegisterButton.click();
        homeVendorPage.username.clear();
        homeVendorPage.username.sendKeys(username);
        homeVendorPage.password.clear();
        homeVendorPage.password.sendKeys(password);
        homeVendorPage.signInButton.click();

        WaitUtils.waitFor(2);

        JSUtils.JSscrollIntoView(homeVendorPage.textMyAccount);
        WaitUtils.waitForVisibility(homeVendorPage.textMyAccount, 2);
        JSUtils.JSclickWithTimeout(homeVendorPage.textMyAccount);
    }
}