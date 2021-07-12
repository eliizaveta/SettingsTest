import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SettingsPage {

    public WebDriver driver;
    public SettingsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = ".//a[@class='user-settings_i']/div[contains(text(), 'Личные данные')]")
    private WebElement personalDataBtn;

    @FindBy(xpath = ".//input[@id='field_name']")
    private WebElement name;

    @FindBy(xpath = ".//input[@id='field_surname']")
    private WebElement surname;

    @FindBy(xpath = ".//input[@id='field_citySugg_SearchInput']")
    private WebElement currentCity;

    @FindBy(xpath = ".//input[@id='field_cityBSugg_SearchInput']")
    private WebElement nativeCity;

    @FindBy(xpath = ".//select[@id='field_bday']")
    private WebElement birthDay;

    @FindBy(xpath = ".//option[contains(text(),'день')]")
    private WebElement incorrectDay;

    @FindBy(xpath = ".//select[@id='field_bmonth']")
    private WebElement birthMonth;

    @FindBy(xpath = ".//option[contains(text(),'месяц')]")
    private WebElement incorrectMonth;

    @FindBy(xpath = ".//select[@id='field_byear']")
    private WebElement birthYear;

    @FindBy(xpath = ".//option[contains(text(),'год')]")
    private WebElement incorrectYear;

    @FindBy(xpath = ".//input[@name='button_savePopLayerEditUserProfileNew']")
    @CacheLookup
    private WebElement saveBtn;

    public void clickPersonalDataBtn() {
        personalDataBtn.click();
    }

    public void clickNameBtn() {
        name.click();
    }

    public void clickSurnameBtn() {
        surname.click();
    }

    public void clickCurrentCityBtn() {
        currentCity.click();
    }

    public void clickNativeCityBtn() {
        nativeCity.click();
    }

    public void clickBirthDayBtn() {
        birthDay.click();
    }

    public void setDay() {
        incorrectDay.click();
    }

    public void clickBirthMonthBtn() {
        birthMonth.click();
    }

    public void setMonth() {
        incorrectMonth.click();
    }

    public void clickBirthYearBtn() {
        birthYear.click();
    }

    public void setYear() {
        incorrectYear.click();
    }

    public void save() {
        saveBtn.click();
    }

    public void setCity(String city, int i) {
        switch (i) {
            case 0:
            currentCity.clear();
            currentCity.sendKeys(city);
            break;
            case 1:
            nativeCity.clear();
            nativeCity.sendKeys(city);
            break;
        }
    }
}
