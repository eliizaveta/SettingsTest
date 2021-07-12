import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;

public class Tests {

    public static WebDriver driver;

    public static LoginPage loginPage;
    public static TmpPage tmpPage;
    public static SettingsPage settingsPage;

    @Test
    @Ignore
    public void incorrectName() {
        settingsPage.clickNameBtn();
        settingsPage.clickSurnameBtn();
        //передавать пар-ры
        settingsPage.save();
        //проверка
    }

    @Test
    @Ignore
    public void incorrectDayOfBirthday() {
        settingsPage.clickBirthDayBtn();
        settingsPage.setDay();
        settingsPage.save();
        //проверка
    }

    @Test
    @Ignore
    public void incorrectMonthOfBirthday() {
        settingsPage.clickBirthMonthBtn();
        settingsPage.setMonth();
        settingsPage.save();
        //проверка
    }

    @Test
    @Ignore
    public void incorrectYearOfBirthday() {
        settingsPage.clickBirthYearBtn();
        settingsPage.setYear();
        settingsPage.save();
        //проверка
    }

    @Test
    @Ignore
    public void incorrectCity() {

        settingsPage.clickCurrentCityBtn();
        settingsPage.setCity(ConfProperties.getProperty("incorrectCity1"), 0);
        settingsPage.save();
        //проверка

        settingsPage.clickNativeCityBtn();
        settingsPage.setCity(ConfProperties.getProperty("incorrectCity2"), 1);
        settingsPage.save();
        //проверка
    }

    @Test
    public void changeCity() {
        settingsPage.clickCurrentCityBtn();
        settingsPage.setCity(ConfProperties.getProperty("correctCity1"), 0);
        settingsPage.save();
        //проверка

        settingsPage.clickCurrentCityBtn();
        settingsPage.setCity(ConfProperties.getProperty("correctCity2"), 0);
        settingsPage.save();
        //проверка
    }

    @BeforeClass
    public static void setup() {

        System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("chromedriver"));
        driver = new ChromeDriver();

        loginPage = new LoginPage(driver);
        tmpPage = new TmpPage(driver);
        settingsPage = new SettingsPage(driver);

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(ConfProperties.getProperty("loginpage"));

        loginPage.inputLogin(ConfProperties.getProperty("login"));
        loginPage.inputPasswd(ConfProperties.getProperty("password"));
        loginPage.clickLoginBtn();

        tmpPage.clickMenuBtn();
        tmpPage.clickSettingsBtn();

        settingsPage.clickPersonalDataBtn();
    }

    @AfterClass
    public static void clear() {
        driver.quit();
    }
}
