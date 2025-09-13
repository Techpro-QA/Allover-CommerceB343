package Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class MyAccountPage {
    public MyAccountPage() {
        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy(xpath = "//a[text()='My Account']")
    public WebElement accountvendorLink;

    @FindBy(xpath ="//a[text()='Orders']")
    public WebElement orders;

}
