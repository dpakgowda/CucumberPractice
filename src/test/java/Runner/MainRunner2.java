package Runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;
//import io.cucumber.testng.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/java/Feature/EBayAdvance.feature"},
        glue = {"StepDefination"},
        monochrome = true,
        dryRun = false,
        plugin = {"pretty",
                "html:target/cucumber",
                "json:target/cucumber.json",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"},
        tags = ""
)

public class MainRunner2 {
}
