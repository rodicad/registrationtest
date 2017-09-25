package com.maxbet.registration.model.pages.abstracts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by rodicad on 21/09/2017.
 */
@Component
public abstract class AbstractPage {
    private static final long DEFAULT_TIMEWAIT = 10;
    private WebDriver webDriver;

    public WebDriver getWebDriver() {
        return webDriver;
    }


    @Autowired
    public AbstractPage( WebDriver webDriver ) {
        this.webDriver = webDriver;
    }

    public void open (String url, Class instance) {
        webDriver.get( url );
        initElements(instance);
    }

    public void initElements(Class instance) {
        PageFactory.initElements( webDriver, instance );
    }

    public String getPageTitle() {
        return webDriver.getTitle();
    }

    public WebElement waitForElementClickable(WebElement element) {
            return new WebDriverWait(webDriver, DEFAULT_TIMEWAIT)
                    .until( ExpectedConditions.elementToBeClickable(element));
        }

    public WebElement waitElementToBePresent(WebElement element) {
        return new WebDriverWait(webDriver, DEFAULT_TIMEWAIT).until(ExpectedConditions.visibilityOf(element));
    }

    public WebElement waitElementToBePresentByLocator(By locator) {
        return new WebDriverWait(webDriver, DEFAULT_TIMEWAIT).until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public boolean isElementDisplayed(By locator) {
        return ExpectedConditions.invisibilityOfElementLocated(locator ).apply( webDriver );
    }




}
