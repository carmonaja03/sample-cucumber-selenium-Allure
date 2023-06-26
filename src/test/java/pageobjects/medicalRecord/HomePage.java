package pageobjects.medicalRecord;

import hooks.AllureScreenShot;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pageobjects.common.BasePage;
import utils.Verify;

public class HomePage extends BasePage {

    private By byRegisterAPatientButton = By.cssSelector("a[id='referenceapplication-registrationapp-registerPatient-homepageLink-referenceapplication-registrationapp-registerPatient-homepageLink-extension']");

    public void validateHomePage(String url) {
        Verify.getInstance().assertEquals(getCurrURL(), url);
        AllureScreenShot.attachScreenShot(getDriver(), "Page");
    }

    public void clickRegisterAPatient() {
        findElement(byRegisterAPatientButton).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(byRegisterAPatientButton));
    }
}
