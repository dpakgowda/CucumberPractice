package StepDefination;

import Utils.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;

import java.sql.Driver;

public class MasterHooks extends DriverFactory {

    @Before
    public void setUp(){
        DriverFactory.initDriver();
    }

    @After
    public void tearDown(){
        if(DriverFactory.getDriver() != null) {
            DriverFactory.getDriver().quit();
        }
    }
}
