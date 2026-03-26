package StepDefination;

import Utils.DriverFactory;
import Utils.ScreenshotUtil;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import java.util.Base64;

public class MasterHooks extends DriverFactory {

    @Before
    public void setUp(){
        DriverFactory.initDriver();
    }

    @AfterStep
    public void afterStep(Scenario scenario){
        String screenshot = ScreenshotUtil.captureBase64(DriverFactory.getDriver());

        //Attach to Cucumber report
        scenario.attach(
                Base64.getDecoder().decode(screenshot),"image/png","Step Screenshot"
        );
        //Attach to Allure
        io.qameta.allure.Allure.addAttachment(
                "Step Screenshot","image/png",new java.io.ByteArrayInputStream(Base64.getDecoder().decode(screenshot)),".png"
        );
    }

    @After
    public void tearDown(Scenario scenario){
        if(scenario.isFailed()){
            String screenshot = ScreenshotUtil.captureBase64(DriverFactory.getDriver());

            scenario.attach(Base64.getDecoder().decode(screenshot),"image/png","Failure Screenshot");
        }
        if(DriverFactory.getDriver() != null) {
            DriverFactory.getDriver().quit();
        }
    }
}
