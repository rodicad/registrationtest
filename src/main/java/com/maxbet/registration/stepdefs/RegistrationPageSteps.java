package com.maxbet.registration.stepdefs;

import com.maxbet.registration.config.TestConfig;
import com.maxbet.registration.model.pages.MaxebtMainPage;
import com.maxbet.registration.stepdefs.expectedData.RegistrationTestValidator;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration( classes = TestConfig.class )
public class RegistrationPageSteps  {

    @Autowired
    private  MaxebtMainPage mainPage;

    @Given( "^I open website under test$" )
    public void openWebsite() throws Exception {
        mainPage.open();
    }

    @Then( "^correct page url should be displayed$" )
    public void correctPageUrlShouldBeDisplayed() throws Throwable {
        Assert.assertEquals(RegistrationTestValidator.PAGE_TITLe, mainPage.getPageTitle());
    }

    @And("^registration container should display correct data$")
    public void registrationFormShouldDisplayCorrectData() throws Throwable {
        Assert.assertEquals(RegistrationTestValidator.REGISTRATION_FORM_TITLE, mainPage.getRegistrationContainer().getTitle());
        Assert.assertEquals(RegistrationTestValidator.REGISTRATION_FORM_SUBTITLE, mainPage.getRegistrationContainer().getSubtitle());
        Assert.assertEquals(RegistrationTestValidator.REGISTRATION_FORM_CHECKMARK, mainPage.getRegistrationContainer().getCheckmark());

        Assert.assertEquals(RegistrationTestValidator.REGISTRATION_FB_BUTTON_NAME, mainPage.getRegistrationContainer().getFbRegistrationButton().getButtonName());
        Assert.assertEquals(RegistrationTestValidator.REGISTRATION_BUTTON_NAME, mainPage.getRegistrationContainer().getRegistrationButton().getButtonName());

    }

    @When("^user clickon registration button")
    public void userOpensRegistrationForm() throws Throwable {
        mainPage.openRegistrationForm();
    }

    @Then("^registration form should be open$")
    public void registrationFormShouldBeOpen() throws Throwable {
        Assert.assertTrue(mainPage.isRegistrationFormOpen());
        mainPage.getRegistrationDataForm();

    }
}
