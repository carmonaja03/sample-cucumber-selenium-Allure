package pageobjects;

import hooks.AllureScreenShot;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import pageobjects.common.BasePage;
import utils.Verify;

public class GooglePage extends BasePage {

    private By searchTextBox = By.cssSelector("input[name='q']");
    public void inputTextOnSearchBox(String inputSearch) {
        findElement(searchTextBox).clear().sendKeys(inputSearch).sendKeysChord(Keys.ENTER);

    }

    public void validateSearchText(String text) {
        By searchLabel = By.xpath("//a[@href='"+text+"']/ancestor::div[@id='search']");
        Verify.getInstance().assertTrue(findElement(searchLabel).clickable().element().isDisplayed());
        AllureScreenShot.attachScreenShot(getDriver(), "Search is displayed");

    }
}
