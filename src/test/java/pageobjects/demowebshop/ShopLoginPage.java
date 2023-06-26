package pageobjects.demowebshop;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pageobjects.common.BasePage;

public class ShopLoginPage extends BasePage {

    private By byUserEmailTextBox = By.cssSelector("input[id='Email']");
    private By byPasswordTextBox = By.cssSelector("input[id='Password']");
    private By byLogInButton = By.cssSelector("input[value='Log in']");


    public void inputCredentials(String username, String password) {
        findElement(byUserEmailTextBox).sendKeys(username);
        findElement(byPasswordTextBox).sendKeys(password);
    }

    public void clickLoginButton() {
        findElement(byLogInButton).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(byLogInButton));
    }


}
