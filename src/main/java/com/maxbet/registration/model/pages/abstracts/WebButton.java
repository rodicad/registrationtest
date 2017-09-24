package com.maxbet.registration.model.pages.abstracts;

import org.openqa.selenium.WebElement;

/**
 * Created by Rodicad on 24/09/2017.
 */
public class WebButton {
    public WebElement getButtonLocator() {
        return buttonLocator;
    }

    public String getButtonName() {
        return buttonName;
    }

    private WebElement buttonLocator;
    private String buttonName;

    public WebButton(WebElement address) {
        this.buttonLocator = address;
        this.buttonName = address.getText();
    }
}
