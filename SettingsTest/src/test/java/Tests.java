import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class Tests {

    public static WebDriver driver;
    public static WebDriverWait wait;

    public static LoginPage loginPage;
    public static TmpPage tmpPage;
    public static SettingsPage settingsPage;



    @Test
    public void incorrectName() {

        settingsPage.clickNameBtn();
        settingsPage.setName("newName");
        settingsPage.save();
        settingsPage.check("incorrectNameAlert", "incorrect name");
    }

    @Test
    public void emptyName() {

        settingsPage.clickNameBtn();
        settingsPage.setName("emptyName");
        settingsPage.save();
        settingsPage.check("emptyNameAlert", "empty name");
    }


    @Test
    /**
     * данный тест находит баг, который позволяет менять фамилию (и аналогично имя) на цифры
     */
    public void incorrectSurname() {

        settingsPage.clickSurnameBtn();
        settingsPage.setSurname("newSurname");
        settingsPage.save();
        settingsPage.check("incorrectNameAlert", "incorrect surname");
    }

    @Test
    public void emptySurname() {

        settingsPage.clickSurnameBtn();
        settingsPage.setSurname("emptySurname");
        settingsPage.save();
        settingsPage.check("emptySurnameAlert", "empty surname");
    }

    @Test
    public void incorrectDayOfBirthday() {

        settingsPage.clickBirthDayBtn();
        settingsPage.setBirthday("incorrectDay");
        settingsPage.save();
        settingsPage.check("birthdayAlert", "incorrect day of birthday");
    }

    @Test
    public void incorrectMonthOfBirthday() {

        settingsPage.clickBirthMonthBtn();
        settingsPage.setBirthday("incorrectMonth");
        settingsPage.save();
        settingsPage.check("birthdayAlert", "incorrect month of birthday");
    }

    @Test
    public void incorrectYearOfBirthday() {

        settingsPage.clickBirthYearBtn();
        settingsPage.setBirthday("incorrectYear");
        settingsPage.save();
        settingsPage.check("birthdayAlert", "incorrect year of birthday");
    }

    @Test
    public void incorrectCurrentCity() {

        settingsPage.clickCurrentCityBtn();
        settingsPage.setCity("incorrectCity1", "current");
        settingsPage.save();
        settingsPage.check("currentCityAlert", "incorrect current city");
    }

    @Test
    public void incorrectNativeCity() {

        settingsPage.clickNativeCityBtn();
        settingsPage.setCity("incorrectCity2", "native");
        settingsPage.save();
        settingsPage.check("nativeCityAlert", "incorrect native city");
    }

    @Test
    public void changeCity() {

        settingsPage.clickCurrentCityBtn();
        settingsPage.setCity("correctCity1", "current");
        settingsPage.save();

        settingsPage.clickPersonalDataBtn(wait);
        String city = settingsPage.getCity("current");
        settingsPage.check(city, "correctCity1", "change city");
    }

    @Test
    public void changeBirthday() {

        settingsPage.clickBirthDayBtn();
        settingsPage.setBirthday("newDay");
        settingsPage.setBirthday("newMonth");
        settingsPage.setBirthday("newYear");
        settingsPage.save();

        settingsPage.clickPersonalDataBtn(wait);
        String bDay = settingsPage.getBirthday("day");
        settingsPage.check(bDay, "newDay", "change birthday day");
        String bMonth = settingsPage.getBirthday("month");
        settingsPage.check(bMonth, "newMonth", "change birthday month");
        String bYear = settingsPage.getBirthday("year");
        settingsPage.check(bYear, "newYear", "change birthday year");
    }

    @Test
    public void changeGender() {

        settingsPage.setMaleGender();
        settingsPage.save();
        settingsPage.clickPersonalDataBtn(wait);
        String gender = settingsPage.getGender();
        settingsPage.check(gender, "genderMale", "change gender to male");

        settingsPage.setFemaleGender();
        settingsPage.save();
        settingsPage.clickPersonalDataBtn(wait);
        gender = settingsPage.getGender();
        settingsPage.check(gender, "genderFemale", "change gender to female");
    }

    @BeforeClass
    public static void init() {

        System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("chromedriver"));
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver,10);

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

        settingsPage.clickPersonalDataBtn(wait);
    }

    @AfterClass
    public static void clear() {
        driver.quit();
    }
}
