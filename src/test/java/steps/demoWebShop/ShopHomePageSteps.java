package steps.demoWebShop;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import steps.common.POInstances;

public class ShopHomePageSteps extends POInstances {

    @Then("^\"(.*)\" user \"(.*)\" is now logged in$")
    public void userIsNowLoggedIn(String userKey, String usernameKey) throws Exception {
        String username = userLoginHelper.getValue(userKey,usernameKey);
        shopHomePage.validateUserIsLoggedIn(username);
    }

    @And("^\"(.*)\" clicks \"(.*)\" in the top header menu$")
    public void userClicksInTheMainMenu(String userKey, String productCategoryKey) throws Exception {
        String productCategory = productsHelper.getValue(userKey, productCategoryKey);
        shopHomePage.clickTopHeaderMenu(productCategory);
    }

    @And("^\"(.*)\" user selects \"(.*)\"$")
    public void userSelects(String userKey, String productKey) throws Exception {
       String productName = productsHelper.getValue(userKey, productKey);
        shopHomePage.clickProduct(productName);
    }

    @And("^user clicks add to cart button$")
    public void userClicksAddToCartButton() {
        shopHomePage.clickAddToCartButton();
    }

    @Then("^user confirms that the product has been added to cart$")
    public void userValidatesProductHasBeenAddedToCart() {
        shopHomePage.validateProductHasBeenAddedToCart();
    }

    @When("^user clicks shopping cart$")
    public void userClicksShoppingCart() {
        shopHomePage.clickShoppingCart();
    }
}
