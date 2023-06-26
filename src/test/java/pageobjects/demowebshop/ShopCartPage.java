package pageobjects.demowebshop;

import org.openqa.selenium.By;
import pageobjects.common.BasePage;
import utils.UrlHelper;

public class ShopCartPage extends BasePage {

    private By byProductQtyTextBox = By.cssSelector("input[name*='itemquantity']");
    private By byCheckOutButton = By.cssSelector("button[id='checkout']");
    private By byAgreeWithTermsAndConditions = By.cssSelector("input[id='termsofservice']");

    public void navigateToURL(String urlKey) throws Exception {
        String url = String.valueOf(UrlHelper.getURLEnv(urlKey));
        maximizeViewPort();
        navigateToPage(url);
    }

    public void updateProductQty(String productQty) {
        findElement(byProductQtyTextBox).clear().sendKeys(productQty);
    }

    public void checksAgreeWithTheTermsAndConditions() {
        if(findElement(byAgreeWithTermsAndConditions).getAttribute("checked") == null){
            findElement(byAgreeWithTermsAndConditions).click();
        }
    }
}