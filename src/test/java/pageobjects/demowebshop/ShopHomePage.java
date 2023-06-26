package pageobjects.demowebshop;

import hooks.AllureScreenShot;
import org.openqa.selenium.By;
import pageobjects.common.BasePage;
import utils.Verify;

public class ShopHomePage extends BasePage {

    private By byCustomerAccount = By.cssSelector("div[class='header-links']>ul>li>a[class='account']");
    private By byAddToCartButton = By.cssSelector("input[id*='add-to-cart-button']");
    private By byBarNotificationSuccessMessage = By.cssSelector("div[class='bar-notification success']");
    private By byShoppingCartLink = By.cssSelector("li[id='topcartlink']>a[class='ico-cart']>span");

    public void validateUserIsLoggedIn(String username) {
        Verify.getInstance().assertEquals(findElement(byCustomerAccount).getAttribute("innerText"), username);
        AllureScreenShot.attachScreenShot(driver, "Home page");
    }

    public void clickTopHeaderMenu(String productCategory) {
        By byProductCategoryMenu = By.xpath("//ul[@class='top-menu']/li/a[contains(text(),'"+ productCategory +"')]");
        findElement(byProductCategoryMenu).clickable().click();
    }

    public void clickProduct(String productName) {
        By product = By.cssSelector("h2[class='product-title']>a[href='"+productName+"']");
        findElement(product).click();
    }

    public void clickAddToCartButton() {
        findElement(byAddToCartButton).click();
    }

    public void validateProductHasBeenAddedToCart() {
        //Verify.getInstance().assertTrue(findElement(byBarNotificationSuccessMessage).element().isDisplayed());
        Verify.getInstance().assertTrue(isElementPresent(byBarNotificationSuccessMessage));
    }

    public void clickShoppingCart() {
        findElement(byShoppingCartLink).clickJS();
    }
}