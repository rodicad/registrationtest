package com.maxbet.registration.model.pages.abstracts;

import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AbstractDriver {

    protected static WebDriver webDriver;

    public static WebDriver getWebDriver() {
        return webDriver;
    }



}
