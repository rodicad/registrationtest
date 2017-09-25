package com.maxbet.registration.stepdefs;

import com.maxbet.registration.config.Log;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class CommonSteps {

    private WebDriver webDriver;

    @Autowired
    public CommonSteps( WebDriver webDriver ) {
        this.webDriver = webDriver;
    }

    private static final Logger LOG = LoggerFactory.getLogger( Log.class );

    @Before
    public void startTestCase(Scenario scenario) {
        LOG.info( "Starting test case: {}", scenario.getName() );
    }

    @After
    public  void endTestCase(Scenario scenario) {
    LOG.info( "Finished test case: {}", scenario.getName() );
        webDriver.quit();
    }











}
