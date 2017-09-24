package com.maxbet.registration.stepdefs;

import com.maxbet.registration.config.Log;
import com.maxbet.registration.config.driver.BrowserDriverInit;
import com.maxbet.registration.model.pages.abstracts.AbstractDriver;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class CommonSteps extends AbstractDriver {
    private static final Logger LOG = LoggerFactory.getLogger( Log.class );

    @Before
    public static void startTestCase(String testcase) {
        LOG.info( "Starting test case: {}", testcase );
    }

    @After
    public static void endTestCase(String testcase) {
    LOG.info( "Finished test case: {}", testcase );
        getWebDriver().quit();
    }











}
