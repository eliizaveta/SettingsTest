import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class TmpPage {

    public WebDriver driver;
    public TmpPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
}
