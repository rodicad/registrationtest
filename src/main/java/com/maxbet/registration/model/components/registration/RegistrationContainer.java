package com.maxbet.registration.model.components.registration;

import com.maxbet.registration.model.pages.MaxebtMainPage;
import com.maxbet.registration.model.pages.abstracts.WebButton;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class  RegistrationContainer {

    public String title;
    public String subtitle;

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getCheckmark() {
        return checkmark;
    }

    public void setCheckmark(String checkmark) {
        this.checkmark = checkmark;
    }

    public String checkmark;


    private WebButton registrationButton;
    private WebButton fbButton;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public void setRegistrationButton(WebButton registrationButton) {
        this.registrationButton = registrationButton;
    }

    public WebButton getRegistrationButton() {
        return registrationButton;
    }

    public WebButton getFbRegistrationButton() {
        return fbButton;
    }

    public void setFbRegistrationButton(WebButton fbButton) {
        this.fbButton = fbButton;
    }





}
