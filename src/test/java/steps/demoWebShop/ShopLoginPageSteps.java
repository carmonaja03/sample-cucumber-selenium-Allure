package steps.demoWebShop;

import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import steps.common.POInstances;

public class ShopLoginPageSteps extends POInstances {

    @When("^\"(.*)\" user inputs \"(.*)\" and \"(.*)\" in the webshop login page")
    public void userInputsAndInTheWebshopLoginPage(String shopUserKey, String usernameKey, String passwordKey) throws Exception {
        String username = userLoginHelper.getValue(shopUserKey,usernameKey);
        String password = userLoginHelper.getValue(shopUserKey,passwordKey);
        shopLoginPage.inputCredentials(username, password);
    }

    @And("^clicks the log in button$")
    public void clicksTheLogInButton() {
        shopLoginPage.clickLoginButton();
    }
}
