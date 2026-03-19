package Runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
//import io.cucumber.testng.AbstractTestNGCucumberTests;
//import io.cucumber.testng.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)

@CucumberOptions(
        features = {"src/test/java/Feature/EBayHome.feature"},
        glue = {"StepDefination"},
        monochrome = true,
        dryRun = false,
        tags ="",
        plugin = {"pretty","html:target/cucumber","json:target/cucumber.json"}
)
public class MainRunner {
}
