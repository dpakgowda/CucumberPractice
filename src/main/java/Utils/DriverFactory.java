package Utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class DriverFactory {

    public static WebDriver driver;
    public static WebDriver initDriver() {

            ReadConfig file = new ReadConfig();
            String BrowserName = file.getBrowser();

            switch (BrowserName){
                case "chrome":

                        WebDriverManager.chromedriver().setup();
                        System.setProperty("webdriver.chrome.driver",Constant.CHROME_DRIVER_DIRECTORY);
                        driver = new ChromeDriver();
                        driver.manage().window().maximize();
                        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
                        break;
            }
            return driver;

        } public static  WebDriver getDriver(){
        return driver;
    }
}
