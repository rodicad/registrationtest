package com.maxbet.registration.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class BrowserController  {

    private final WebDriver webDriver;

    @Value( "${browser}" )
    private final String browser;




    @Autowired
    public BrowserController(  @Value( "${browser}" ) final String browser) throws Exception {
        this.browser = browser;
        System.setProperty( "webdriver.chrome.driver", "src/test/resources/selenium/chromedriver/chromedriver.exe" );
        System.out.println("browseer: "+browser);
        if (browser.equals( "chrome" )) {
            webDriver = new ChromeDriver(  );
        } else {
            webDriver = new FirefoxDriver(  );
        }

    }

    public WebDriver getWebDriver() {
        return webDriver;
    }









}
