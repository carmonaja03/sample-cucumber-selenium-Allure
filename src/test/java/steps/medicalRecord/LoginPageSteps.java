package steps.medicalRecord;

import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import steps.common.POInstances;

public class LoginPageSteps extends POInstances {

    @When("^\"(.*)\" inputs \"(.*)\" and \"(.*)\" in the login page$")
    public void userInputsAnd(String adminUserKey, String usernameKey, String passwordKey) throws Exception {
        String username = userLoginHelper.getValue(adminUserKey,usernameKey);
        String password = userLoginHelper.getValue(adminUserKey,passwordKey);
        loginPage.inputCredentials(username, password);
    }

    @And("^\"(.*)\" selects a \"(.*)\" as the location for this session$")
    public void selectsALocation(String adminUserKey, String locationKey) throws Exception {
        String location = userLoginHelper.getValue(adminUserKey,locationKey);
        loginPage.selectLocation(location);
    }

    @And("^clicks the Log In button$")
    public void clicksTheLogInButton() {
        loginPage.clickLogInButton();
    }
}
