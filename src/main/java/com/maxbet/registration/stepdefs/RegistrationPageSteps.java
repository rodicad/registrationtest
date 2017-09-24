package com.maxbet.registration.stepdefs;

import com.maxbet.registration.config.BrowserController;
import com.maxbet.registration.config.TestConfig;
import com.maxbet.registration.model.pages.MaxebtMainPage;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration( classes = TestConfig.class )
public class RegistrationPageSteps  {

    @Autowired
    private  MaxebtMainPage mainPage;

    @Autowired
    private BrowserController browserController;

    @Given( "^I open website under test$" )
    public void openWebsite() throws Exception {
        System.out.println("is controller null: "+browserController);
        mainPage.open();

    }

    @Then( "^correct page url should be displayed$" )
    public void correctPageUrlShouldBeDisplayed() throws Throwable {

    }
}
