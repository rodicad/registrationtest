package com.maxbet.registration.config.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BrowserDriverInit  {

    @Autowired
    private WebDriverProperties webDriverProperties;

    @Bean
    public WebDriver webDriver(){
        WebDriver driver = null;
        if (webDriverProperties.getBrowserType().equals("chrome")) {
            driver = initChromeDriver();
        } else if (webDriverProperties.getBrowserType().equals("firefox")){
            driver = initFirefoxDriver();
        } else if (webDriverProperties.getBrowserType().equals("firefox")){
            driver = initIEDriver();
        }

        driver.manage().window().setSize(webDriverProperties.getBrowserResolution());
        return driver;
    }

    private WebDriver initChromeDriver() {
        webDriverProperties.setChromeBinaryProperty();
        return new ChromeDriver();
    }

    private WebDriver initIEDriver() {
        webDriverProperties.setIEBinaryProperty();
        return new InternetExplorerDriver();
    }

    private WebDriver initFirefoxDriver() {
        return new FirefoxDriver();
    }



}
