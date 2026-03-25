package Runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.junit.runner.RunWith;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)

@CucumberOptions(
        features = {"src/test/java/Feature/EBayHome.feature"},
        glue = {"StepDefination"},
        monochrome = true,
        dryRun = false,
        tags ="",
        plugin = {"pretty","html:target/cucumber","json:target/cucumber.json"}
)
public class MainRunner extends AbstractTestNGCucumberTests{
}
