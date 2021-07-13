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
        settingsPage.clickSurnameBtn();
        settingsPage.setName(ConfProperties.getProperty("newName"));
        settingsPage.setSurname(ConfProperties.getProperty("newSurname"));
        settingsPage.save();
        settingsPage.check("nameAlert", "incorrectName");
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
        String city = settingsPage.getCity();
        System.out.println(city);
        Assert.assertEquals("Санкт-Петербург", city);
        System.out.println("Test of change city is successful");
    }

    @Test
    public void changeBirthday() {

        settingsPage.clickBirthDayBtn();
        settingsPage.setBirthday(ConfProperties.getProperty("newDay"));
        settingsPage.setBirthday(ConfProperties.getProperty("newMonth"));
        settingsPage.setBirthday(ConfProperties.getProperty("newYear"));
        settingsPage.save();
        settingsPage.clickPersonalDataBtn();
        String bDay = settingsPage.getBirthday(0);
        Assert.assertEquals(ConfProperties.getProperty("newDay"), bDay);
        String bMonth = settingsPage.getBirthday(1);
        Assert.assertEquals(ConfProperties.getProperty("newMonth"), bMonth);
        String bYear = settingsPage.getBirthday(2);
        Assert.assertEquals(ConfProperties.getProperty("newYear"), bYear);
        System.out.println("Test of change city is successful");

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
