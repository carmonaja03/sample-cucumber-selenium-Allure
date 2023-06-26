package steps;

import io.cucumber.java.en.Given;
import utils.UrlHelper;
import steps.common.POInstances;

public class NavigationPageSteps extends POInstances {

    @Given("^\"(.*)\" is open$")
    public void isOpen(String urlKey) throws Exception {
        navigationPage.navigateToURL(urlKey);
    }
}
