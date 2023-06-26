package steps.medicalRecord;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import steps.common.POInstances;
import utils.UrlHelper;

public class HomePageSteps extends POInstances {

    @Then("^page is navigated to \"(.*)\"$")
    public void pageIsNavigatedTo(String urlKey) throws Exception {
        String url = String.valueOf(UrlHelper.getURLEnv(urlKey));
        homePage.validateHomePage(url);
    }

    @When("^user selects Register a patient$")
    public void userSelectsRegisterAPatient() {
        homePage.clickRegisterAPatient();
    }
}
