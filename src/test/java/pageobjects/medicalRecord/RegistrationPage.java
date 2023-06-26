package pageobjects.medicalRecord;

import hooks.AllureScreenShot;
import org.openqa.selenium.By;
import pageobjects.common.BasePage;
import utils.Verify;

public class RegistrationPage extends BasePage {

    private By byRegistrationLabel = By.cssSelector("div[id='content']>h2");
    private By byGivenNameTextBox = By.cssSelector("input[name='givenName']");
    private By byFamilyNameTextBox = By.cssSelector("input[name='familyName']");
    private By byNextButton = By.cssSelector("button[id='next-button']");
    private By byGenderSelect = null;
    private By byBirthDayTextBox = By.cssSelector("input[id='birthdateDay-field']");
    private By byBirthMonthDropDown = By.cssSelector("select[id='birthdateMonth-field']");
    private By byBirthYearTextBox = By.cssSelector("input[id='birthdateYear-field']");
    private By byAddress1TextBox = By.cssSelector("input[id='address1']");
    private By byAddress2TextBox = By.cssSelector("input[id='address2']");
    private By byPhoneNumberTextBox = By.cssSelector("input[name='phoneNumber']");
    private By byRelationshipTypeDropdown = By.cssSelector("select[id='relationship_type']");
    private By byPersonNameTextBox = By.cssSelector("input[placeholder='Person Name']");
    private By byConfirmButton = By.cssSelector("input[id='submit']");
    private By byGivenNameLabel = By.cssSelector("span[class='PersonName-givenName']");
    private By byFamilyNameLabel = By.cssSelector("span[class='PersonName-familyName']");


    public void validateRegistrationLabelIsDisplayed(String translation) {
        Verify.getInstance().assertEquals(findElement(byRegistrationLabel).getAttribute("innerText"), translation);
    }

    public void inputNameInDemographicsSection(String givenName, String familyName) {
        findElement(byGivenNameTextBox).sendKeys(givenName);
        findElement(byFamilyNameTextBox).sendKeys(familyName);

        findElement(byNextButton).click();
    }

    public void selectGenderInDemographicsSection(String gender) {
        byGenderSelect = By.xpath("//select[@id='gender-field']/option[contains(text(),'"+ gender +"')]");
        findElement(byGenderSelect).click();

        findElement(byNextButton).click();
    }

    public void inputBirthDateInDemographicsSection(String birthDay, String birthMonth, String birthYear) {
        findElement(byBirthDayTextBox).sendKeys(birthDay);
        findElement(byBirthMonthDropDown).dropdown().selectByVisibleText(birthMonth);
        findElement(byBirthYearTextBox).sendKeys(birthYear);

        findElement(byNextButton).click();
    }
}
