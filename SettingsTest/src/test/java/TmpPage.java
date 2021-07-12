import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TmpPage {

    public WebDriver driver;
    public TmpPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = ".//div[@class='toolbar_dropdown_w h-mod']")
    @CacheLookup
    private WebElement menuBtn;

    @FindBy(xpath = ".//span[contains(text(), 'настройки')]")
    @CacheLookup
    private WebElement settingsBtn;

    public void clickMenuBtn() {
        menuBtn.click();
    }

    public void clickSettingsBtn() {
        settingsBtn.click();
    }
}
