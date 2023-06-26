package steps.medicalRecord;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import steps.common.POInstances;

public class RegistrationPageSteps extends POInstances {

    @And("^\"(.*)\" confirms that \"(.*)\" label is displayed in the page$")
    public void labelIsDisplayedInThePage(String userKey, String translationKey) throws Exception {
        String translation = translationsHelper.getValue(userKey, translationKey);
        registrationPage.validateRegistrationLabelIsDisplayed(translation);
    }

    @When("^\"(.*)\" inputs \"(.*)\" and \"(.*)\" in the name section of Demographics$")
    public void inputsAndInTheDemographicsSection(String patientKey, String givenNameKey, String familyNameKey) throws Exception {
        String givenName = userProfileHelper.getValue(patientKey,givenNameKey);
        String familyName = userProfileHelper.getValue(patientKey,familyNameKey);
        registrationPage.inputNameInDemographicsSection(givenName, familyName);
    }

    @And("^\"(.*)\" selects \"(.*)\" in the gender section of Demographics$")
    public void selectsInTheDemographicsSection(String patientKey, String genderKey) throws Exception {
        String gender = userProfileHelper.getValue(patientKey,genderKey);
        registrationPage.selectGenderInDemographicsSection(gender);
    }

    @And("^\"(.*)\" inputs \"(.*)\", \"(.*)\" and \"(.*)\" in the birthdate section of Demographics$")
    public void inputsBirthDateAndInTheDemographicsSection(String patientKey, String birthDayKey, String birthMonthKey, String birthYearKey) throws Exception {
        String birthDay = userProfileHelper.getValue(patientKey, birthDayKey);
        String birthMonth = userProfileHelper.getValue(patientKey, birthMonthKey);
        String birthYear = userProfileHelper.getValue(patientKey, birthYearKey);
        registrationPage.inputBirthDateInDemographicsSection(birthDay, birthMonth, birthYear);
    }
}
