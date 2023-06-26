package steps.demoWebShop;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import steps.common.POInstances;

public class ShopCartPageSteps extends POInstances {

    @And("^\"(.*)\" user updates product quantity to \"(.*)\"$")
    public void userUpdatesProductQuantityTo(String userKey, String productQtyKey) throws Exception {
        String productQty = productsHelper.getValue(userKey, productQtyKey);
        shopCartPage.updateProductQty(productQty);
    }

    @And("^user checks agree with the terms and conditions$")
    public void userChecksAgreeWithTheTermsAndConditions() {
        shopCartPage.checksAgreeWithTheTermsAndConditions();
    }
}