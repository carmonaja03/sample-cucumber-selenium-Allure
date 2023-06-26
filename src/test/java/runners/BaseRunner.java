package runners;

import automation.library.common.Property;
import automation.library.common.TestContext;
import automation.library.cucumber.selenium.RunnerClassParallel;
import automation.library.cucumber.selenium.RunnerClassSequential;
import automation.library.reporting.AllureScreenShotPub;
import automation.library.reporting.TextReport;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;

@CucumberOptions(
        plugin = {"io.qameta.allure.cucumber4jvm.AllureCucumber4Jvm","json:RunReports/cucumberJson/cucumber-report.json"},
        features = {"classpath:features"},
        glue = {"steps", "hooks"}
        )

public class BaseRunner extends RunnerClassSequential {

        @AfterTest
        public void teardown() {
                TextReport tr = new TextReport();
                tr.createReport(true);
        }

        @BeforeClass
        public void setEnv() {
                TestContext.getInstance().testdataPut("paths", System.getProperty("user.dir") + "/src/test/resources/config/paths.properties");

                String pathsPath = testDataGetFromProp("paths", "environmentsPath");
                String envPath = pathsPath + System.getProperty("cukes.env") + ".properties";

                TestContext.getInstance().testdataPut("env", System.getProperty("user.dir") + envPath);

                String runTimePath = System.getProperty("user.dir") + testDataGetFromProp("paths", "seleniumRuntimePath") + "runtime.properties";

                TestContext.getInstance().testdataPut("paths", runTimePath);


        }

        public String testDataGetFromProp(String path, String key) {
                return Property.getProperties(TestContext.getInstance().testdataGet(path).toString()).getString(key);
        }
}