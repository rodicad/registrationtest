package com.maxbet.registration.model.pages;

import com.maxbet.registration.config.BrowserController;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by rodicad on 21/09/2017.
 */
@Component
public class AbstractPage {


    private WebDriver driver;

    @Autowired
    public AbstractPage(BrowserController browserController){
        this.driver =browserController.getWebDriver();

    }

    protected WebDriver getDriver() {
        return this.getDriver();
    }


//    public WebDriver getWebDriver() {
//        return driver;
//    }
//
    public void open (String url) {
        driver.get( url );
    }


}
