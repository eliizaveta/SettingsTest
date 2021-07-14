import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
    private WebElement firstCurrentCity;

    @FindBy(xpath = ".//div[@id='citySugg_InputContainer']")
    private WebElement currentCityInput;

    @FindBy(xpath = ".//input[@id='field_cityBSugg_SearchInput']")
    private WebElement nativeCity;

    @FindBy(xpath = ".//select[@id='field_bday']")
    private WebElement birthDay;

    @FindBy(xpath = ".//select[@id='field_bday']/option[@selected='selected']")
    private WebElement birthDaySelected;

    @FindBy(xpath = ".//select[@id='field_bmonth']")
    private WebElement birthMonth;

    @FindBy(xpath = ".//select[@id='field_bmonth']/option[@selected='selected']")
    private WebElement birthMonthSelected;

    @FindBy(xpath = ".//select[@id='field_byear']")
    private WebElement birthYear;

    @FindBy(xpath = ".//select[@id='field_byear']/option[@selected='selected']")
    private WebElement birthYearSelected;

    @FindBy(xpath = ".//li[@class='form_ul_li']/input[@checked='checked']")
    private WebElement genderSelected;

    @FindBy(xpath = ".//input[@name='button_savePopLayerEditUserProfileNew']")
    private WebElement saveBtn;

    @FindBy(xpath = ".//input[@value='1']")
    private WebElement manBtn;

    @FindBy(xpath = ".//input[@value='2']")
    private WebElement womanBtn;

    public void clickPersonalDataBtn(WebDriverWait wait) {
        wait.until(ExpectedConditions.visibilityOf(personalDataBtn));
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

    public void clickBirthMonthBtn() {
        birthMonth.click();
    }

    public void clickBirthYearBtn() {
        birthYear.click();
    }

    public void save() {
        saveBtn.click();
    }

    public void setName(String newName) {
        name.clear();
        name.sendKeys(ConfProperties.getProperty(newName));
    }

    public void setSurname(String newSurname) {
        surname.clear();
        surname.sendKeys(ConfProperties.getProperty(newSurname));
    }

    public void setMaleGender() {
        manBtn.click();
    }

    public void setFemaleGender() {
        womanBtn.click();
    }

    public void setBirthday(String day) {
        try {
            String dayLocator = ".//option[contains(text(),'" + ConfProperties.getProperty(day) +"')]";
            driver.findElement(By.xpath(dayLocator)).click();
        } catch (Exception e) {
            System.out.println("There isn't any Birthday fields like this");
        }
    }

    public void setCity(String newCity, String  i) {
        switch (i) {
            case "current":
                currentCity.clear();
                currentCity.sendKeys(ConfProperties.getProperty(newCity));
                currentCityInput.click();
                try {
                    firstCurrentCity.click();
                } catch (Exception e) { }
                break;
            case "native":
                nativeCity.clear();
                nativeCity.sendKeys(ConfProperties.getProperty(newCity));
                break;
        }
    }

    public String getGender() {
        return genderSelected.getAttribute("value");
    }

    public String getBirthday(String i) {

        String tmp = "";
        switch (i) {
            case "day":
                tmp = birthDaySelected.getAttribute("value");
                break;
            case "month":
                tmp = birthMonthSelected.getText();
                break;
            case "year":
                tmp = birthYearSelected.getAttribute("value");
                break;
        }
        return tmp;
    }

    public String getCity(String i) {
        String tmp = "";
        switch (i) {
            case "current":
                tmp = currentCity.getAttribute("value");
                break;
            case "native":
                tmp = nativeCity.getAttribute("value");
                break;
        }
        return tmp;
    }

    public void check(String notification, String testName) {
        Assert.assertTrue("No error notifications", ConfProperties.isElementHere(driver,
                ConfProperties.getProperty(notification)));
        System.out.println("Test " + testName + " is successful");
    }

    public void check(String variable ,String parameter, String testName) {
        Assert.assertEquals(ConfProperties.getProperty(parameter), variable);
        System.out.println("Test " + testName + " is successful");
    }
}
