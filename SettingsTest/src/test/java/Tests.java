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
        Assert.assertTrue("No error notifications", ConfProperties.isElementHere(driver,
                ConfProperties.getProperty("notificationLocator")));
        System.out.println("Test of incorrect name is successful");
    }

    @Test
    public void incorrectDayOfBirthday() {

        settingsPage.clickBirthDayBtn();
        settingsPage.setBirthday("день");
        settingsPage.save();
        Assert.assertTrue("No error notifications", ConfProperties.isElementHere(driver,
                ConfProperties.getProperty("notificationLocator")));
        System.out.println("Test of incorrect day of birth is successful");
    }

    @Test
    public void incorrectMonthOfBirthday() {

        settingsPage.clickBirthMonthBtn();
        settingsPage.setBirthday("месяц");
        settingsPage.save();
        Assert.assertTrue("No error notifications", ConfProperties.isElementHere(driver,
                ConfProperties.getProperty("notificationLocator")));
        System.out.println("Test of incorrect month of birth is successful");
    }

    @Test
    public void incorrectYearOfBirthday() {

        settingsPage.clickBirthYearBtn();
        settingsPage.setBirthday("год");
        settingsPage.save();
        Assert.assertTrue("No error notifications", ConfProperties.isElementHere(driver,
                ConfProperties.getProperty("notificationLocator")));
        System.out.println("Test of incorrect year of birth is successful");
    }

    @Test
    public void incorrectCity() {

        settingsPage.clickCurrentCityBtn();
        settingsPage.setCity(ConfProperties.getProperty("incorrectCity1"), 0);
        settingsPage.save();
        Assert.assertTrue("No error notifications", ConfProperties.isElementHere(driver,
                ConfProperties.getProperty("notificationLocator")));
        System.out.println("Test of incorrect city is successful");

        settingsPage.clickNativeCityBtn();
        settingsPage.setCity(ConfProperties.getProperty("incorrectCity2"), 1);
        settingsPage.save();
        Assert.assertTrue("No error notifications", ConfProperties.isElementHere(driver,
                ConfProperties.getProperty("notificationLocator")));
        System.out.println("Test of incorrect city is successful");
    }

    @Test
    public void changeCity() {

        settingsPage.clickCurrentCityBtn();
        settingsPage.setCity("Санкт-Петербург", 0);
        settingsPage.save();
        settingsPage.clickPersonalDataBtn();
        String city = settingsPage.getCity(0);
        Assert.assertEquals("Санкт-Петербург", city);
        System.out.println("Test of change city is successful");
    }

    @Test
    public void changeBirthday() {

        settingsPage.clickBirthDayBtn();
        settingsPage.setBirthday("2");
        settingsPage.setBirthday("февраль");
        settingsPage.setBirthday("2000");
        settingsPage.save();
        settingsPage.clickPersonalDataBtn();
        String bDay = settingsPage.getBirthday(0);
        System.out.println(bDay);
        Assert.assertEquals("2", bDay);
        String bMonth = settingsPage.getBirthday(1);
        Assert.assertEquals("февраль", bMonth);
        String bYear = settingsPage.getBirthday(2);
        Assert.assertEquals("2000", bYear);
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
