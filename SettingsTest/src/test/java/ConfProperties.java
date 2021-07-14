import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

public class ConfProperties {

    protected static FileInputStream fileInputStream;
    protected static BufferedReader input;
    protected static Properties PROPERTIES;

    static {
        try {
            fileInputStream = new FileInputStream("src/test/resources/conf.properties");
            input = new BufferedReader(new InputStreamReader(fileInputStream, "UTF-8"));
            PROPERTIES = new Properties();
            PROPERTIES.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    fileInputStream.close();
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static String getProperty(String key) {
        return PROPERTIES.getProperty(key);
    }

    public static boolean isElementHere(WebDriver driver, String XPATH){
        try{
            driver.findElement(By.xpath(XPATH));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
