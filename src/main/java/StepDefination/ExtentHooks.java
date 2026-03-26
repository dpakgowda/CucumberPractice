package StepDefination;

import Utils.DriverFactory;
import Utils.ExtentManager;
import Utils.ScreenshotUtil;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import java.sql.Driver;

public class ExtentHooks {
    @Before
    public void beforeScenario(Scenario scenario){
        ExtentManager.test = ExtentManager.getInstance().createTest(scenario.getName());
    }
    @After
    public void afterScenario(){
        ExtentManager.getInstance().flush();
    }
    @AfterStep
    public void afterSteo(){
        String screenshot = ScreenshotUtil.captureBase64(DriverFactory.getDriver());
        ExtentManager.test.addScreenCaptureFromBase64String(screenshot,"Step");
    }
}
