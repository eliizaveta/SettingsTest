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
    public void incorrectName() {

        settingsPage.clickNameBtn();
        settingsPage.setName("newName");
        settingsPage.save();
        settingsPage.check("incorrectNameAlert", "incorrectName");
    }

    @Test
    public void emptyName() {

        settingsPage.clickNameBtn();
        settingsPage.setName("emptyName");
        settingsPage.save();
        settingsPage.check("emptyNameAlert", "emptyName");
    }

    @Test
    public void incorrectSurname() {

        settingsPage.clickSurnameBtn();
        settingsPage.setSurname("newSurname");
        settingsPage.save();
        settingsPage.check("incorrectNameAlert", "incorrectSurname");
    }

    @Test
    public void emptySurname() {

        settingsPage.clickSurnameBtn();
        settingsPage.setSurname("emptySurname");
        settingsPage.save();
        settingsPage.check("emptySurnameAlert", "emptySurname");
    }

    @Test
    public void incorrectDayOfBirthday() {

        settingsPage.clickBirthDayBtn();
        settingsPage.setBirthday(ConfProperties.getProperty("incorrectDay"));
        settingsPage.save();
        settingsPage.check("birthdayAlert", "incorrectDayOfBirthday");
    }

    @Test
    public void incorrectMonthOfBirthday() {

        settingsPage.clickBirthMonthBtn();
        settingsPage.setBirthday(ConfProperties.getProperty("incorrectMonth"));
        settingsPage.save();
        settingsPage.check("birthdayAlert", "incorrectMonthOfBirthday");
    }

    @Test
    public void incorrectYearOfBirthday() {

        settingsPage.clickBirthYearBtn();
        settingsPage.setBirthday(ConfProperties.getProperty("incorrectYear"));
        settingsPage.save();
        settingsPage.check("birthdayAlert", "incorrectYearOfBirthday");
    }

    @Test
    public void incorrectCurrentCity() {

        settingsPage.clickCurrentCityBtn();
        settingsPage.setCity(ConfProperties.getProperty("incorrectCity1"), 0);
        settingsPage.save();
        settingsPage.check("currentCityAlert", "incorrectCurrentCity");
    }

    @Test
    public void incorrectNativeCity() {

        settingsPage.clickNativeCityBtn();
        settingsPage.setCity(ConfProperties.getProperty("incorrectCity2"), 1);
        settingsPage.save();
        settingsPage.check("nativeCityAlert", "incorrectNativeCity");
    }

    @Test
    public void changeCity() {

        settingsPage.clickCurrentCityBtn();
        settingsPage.setCity(ConfProperties.getProperty("correctCity1"), 0);
        settingsPage.save();
        settingsPage.clickPersonalDataBtn();
        String city = settingsPage.getCity(0);
        settingsPage.check(city, "correctCity1", "changeCity");
    }

    @Test
    @Ignore
    public void changeBirthday() {

        settingsPage.clickBirthDayBtn();
        settingsPage.setBirthday(ConfProperties.getProperty("newDay"));
        settingsPage.setBirthday(ConfProperties.getProperty("newMonth"));
        settingsPage.setBirthday(ConfProperties.getProperty("newYear"));
        settingsPage.save();

        settingsPage.clickPersonalDataBtn();
        String bDay = settingsPage.getBirthday(0);
        settingsPage.check(bDay, "newDay", "changeBirthdayDay");
        String bMonth = settingsPage.getBirthday(1);
        settingsPage.check(bMonth, "newMonth", "changeBirthdayMonth");
        String bYear = settingsPage.getBirthday(2);
        settingsPage.check(bYear, "newYear", "changeBirthdayYear");

    }

    @Test
    public void changeSex() {

    }

    @BeforeClass
    public static void init() {

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
