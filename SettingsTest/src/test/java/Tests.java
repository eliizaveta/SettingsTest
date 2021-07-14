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
        settingsPage.setBirthday("incorrectDay");
        settingsPage.save();
        settingsPage.check("birthdayAlert", "incorrectDayOfBirthday");
    }

    @Test
    public void incorrectMonthOfBirthday() {

        settingsPage.clickBirthMonthBtn();
        settingsPage.setBirthday("incorrectMonth");
        settingsPage.save();
        settingsPage.check("birthdayAlert", "incorrectMonthOfBirthday");
    }

    @Test
    public void incorrectYearOfBirthday() {

        settingsPage.clickBirthYearBtn();
        settingsPage.setBirthday("incorrectYear");
        settingsPage.save();
        settingsPage.check("birthdayAlert", "incorrectYearOfBirthday");
    }

    @Test
    public void incorrectCurrentCity() {

        settingsPage.clickCurrentCityBtn();
        settingsPage.setCity("incorrectCity1", "current");
        settingsPage.save();
        settingsPage.check("currentCityAlert", "incorrectCurrentCity");
    }

    @Test
    public void incorrectNativeCity() {

        settingsPage.clickNativeCityBtn();
        settingsPage.setCity("incorrectCity2", "native");
        settingsPage.save();
        settingsPage.check("nativeCityAlert", "incorrectNativeCity");
    }

    @Test
    public void changeCity() throws InterruptedException {

        settingsPage.clickCurrentCityBtn();
        settingsPage.setCity("correctCity1", "current");
        settingsPage.save();

        Thread.sleep(1000);
        settingsPage.clickPersonalDataBtn();
        String city = settingsPage.getCity("current");
        settingsPage.check(city, "correctCity1", "changeCity");
    }

    @Test
    public void changeBirthday() throws InterruptedException {

        settingsPage.clickBirthDayBtn();
        settingsPage.setBirthday("newDay");
        settingsPage.setBirthday("newMonth");
        settingsPage.setBirthday("newYear");
        settingsPage.save();

        Thread.sleep(1000);
        settingsPage.clickPersonalDataBtn();
        String bDay = settingsPage.getBirthday(0);
        settingsPage.check(bDay, "newDay", "changeBirthdayDay");
        String bMonth = settingsPage.getBirthday(1);
        settingsPage.check(bMonth, "newMonth", "changeBirthdayMonth");
        String bYear = settingsPage.getBirthday(2);
        settingsPage.check(bYear, "newYear", "changeBirthdayYear");

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
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
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
