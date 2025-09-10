package Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class MyAccount {
    public MyAccount() {
        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy(linkText = "Store Manager")
    public WebElement storeManager;

}
