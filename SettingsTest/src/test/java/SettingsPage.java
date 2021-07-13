import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

    @FindBy(xpath = ".//div[@class='ellip']")
    private WebElement firstCity;

    @FindBy(xpath = ".//div[@id='citySugg_InputContainer']")
    private WebElement cityInput;

    @FindBy(xpath = ".//input[@id='field_cityBSugg_SearchInput']")
    private WebElement nativeCity;

    @FindBy(xpath = ".//select[@id='field_bday']")
    private WebElement birthDay;

    @FindBy(xpath = ".//select[@id='field_bmonth']")
    private WebElement birthMonth;

    @FindBy(xpath = ".//select[@id='field_byear']")
    private WebElement birthYear;

    @FindBy(xpath = ".//input[@name='button_savePopLayerEditUserProfileNew']")
    private WebElement saveBtn;

    public void clickPersonalDataBtn() {
        personalDataBtn.click();
    }

    public void clickNameBtn() {
        name.click();
    }

    public void setName(String newName) {
        name.clear();
        name.sendKeys(newName);
    }

    public void clickSurnameBtn() {
        surname.click();
    }

    public void setSurname(String newSurname) {
        surname.clear();
        surname.sendKeys(newSurname);
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

    public void clickBirthMonthBtn() {
        birthMonth.click();
    }

    public void clickBirthYearBtn() {
        birthYear.click();
    }

    public void setBirthday(String day) {
        try {
            String dayLocator = ".//option[contains(text(),'" + day +"')]";
            driver.findElement(By.xpath(dayLocator)).click();
        } catch (Exception e) {
            System.out.println("There isn't any Birthday fields like this");
        }
    }
    public String getBirthday(int i) {
        String tmp = null;
        switch (i) {
            case 0:
                tmp = birthDay.getText();
            case 1:
                tmp = birthMonth.getText();
            case 2:
                tmp = birthYear.getText();
        }
        return tmp;
    }

    public void save() {
        saveBtn.click();
    }

    public void setCity(String newCity, int i) {
        switch (i) {
            case 0:
            currentCity.clear();
            currentCity.sendKeys(newCity);
            cityInput.click();
            try {
                firstCity.click();
            } catch (Exception e) { }
            break;
            case 1:
            nativeCity.clear();
            nativeCity.sendKeys(newCity);
            break;
        }
    }

    public String getCity(int i) {
        String tmp = null;
        switch (i) {
            case 0:
                tmp = currentCity.getText();
                break;
            case 1:
                tmp = nativeCity.getText();
                break;
        }
        return tmp;
    }
}
