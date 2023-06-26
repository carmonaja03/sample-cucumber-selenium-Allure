package pageobjects.medicalRecord;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pageobjects.common.BasePage;
public class LoginPage extends BasePage {

    private By byUsernameTextBox = By.cssSelector("input[id='username']");
    private By byPasswordTextBox = By.cssSelector("input[id='password']");
    private By byLogInButton = By.cssSelector("input[id='loginButton']");
    private By byLocationSelection = null;

    public void inputCredentials(String username, String password) {
        findElement(byUsernameTextBox).sendKeys(username);
        findElement(byPasswordTextBox).sendKeys(password);
    }

    public void selectLocation(String location) {
        byLocationSelection = By.cssSelector("li[id='"+ location +"']");
        findElement(byLocationSelection).click();
    }

    public void clickLogInButton() {
        findElement(byLogInButton).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(byLogInButton));
    }
}
