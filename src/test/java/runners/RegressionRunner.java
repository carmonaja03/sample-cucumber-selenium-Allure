package runners;

import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.BeforeClass;

@CucumberOptions(tags = {"@RegressionSuite","@done"})

public class RegressionRunner extends BaseRunner {

    @BeforeClass
    public static void setup() {
        System.setProperty("cukes.selenium", "true");
        System.setProperty("cukes.browsercombo", "LOCAL_FF");
    }
}