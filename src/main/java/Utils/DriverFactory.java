package Utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class DriverFactory {

    public static WebDriver driver;
    public WebDriver getDriver() {
        try{
            ReadConfig file = new ReadConfig();
            String BrowserName = file.getBrowser();

            switch (BrowserName){
                case "chrome":
                    if(null == driver) {
                        WebDriverManager.chromedriver().setup();
                        System.setProperty("webdriver.chrome.driver",Constant.CHROME_DRIVER_DIRECTORY);
                        driver = new ChromeDriver();
                        driver.manage().window().maximize();
                    }
                break;

            }

        } catch (Exception e)
        {
            e.printStackTrace();
        } finally {
            driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        }
        return driver;

    }
}
