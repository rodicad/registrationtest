package com.maxbet.registration.cucumber_runners;

import com.maxbet.registration.config.TestConfig;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;

@CucumberOptions(
        plugin = { "pretty",
                "html:target/cucumber/cucumber-html-report",
                "json:target/cucumber/cucumber-json-report.json"
        },
        monochrome = true,
        features = "src/test/resources/cucumber/features",
        glue = {
                "com.maxbet.registration.stepdefs"

        } )
@RunWith( Cucumber.class )
@ContextConfiguration( classes = TestConfig.class )

public class RegistrationRunner {
    private static final Logger LOG = LoggerFactory.getLogger( RegistrationRunner.class );
}
