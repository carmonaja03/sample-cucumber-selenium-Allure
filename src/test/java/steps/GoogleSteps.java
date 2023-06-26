package steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import steps.common.POInstances;

public class GoogleSteps extends POInstances {
    @When("^user \"(.*)\" inputs \"(.*)\" text on search textbox$")
    public void userInputsTextOnSearchTextBox(String userKey, String searchText) throws Exception {
        String inputSearch = userProfileHelper.getValue(userKey,searchText);
        googlePage.inputTextOnSearchBox(inputSearch);
    }

    @Then("^user \"(.*)\" validates that \"(.*)\" is displayed$")
    public void userValidatesThatIsDisplayed(String userKey, String searchText) throws Exception {
        String text = userProfileHelper.getValue(userKey,searchText);
        googlePage.validateSearchText(text);
    }

}
