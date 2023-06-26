package runners;

import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.BeforeClass;

@CucumberOptions(tags = {"@wip"})

public class SoloRunner extends BaseRunner {

    @BeforeClass
    public static void setup() {
        System.setProperty("cukes.selenium", "true");
        System.setProperty("cukes.browsercombo", "LOCAL_FF");
    }
}