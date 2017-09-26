package com.maxbet.registration.model.components.registration;

import com.maxbet.registration.model.pages.abstracts.AbstractDriver;
import com.maxbet.registration.model.pages.abstracts.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Rodicad on 26/09/2017.
 */
@Component
public class MakeDeposit extends AbstractPage {
    private WebDriver webDriver;

    @FindBy(how = How.CSS, using = "#lightbox > div > div.lightbox-header")
    public static WebElement depositResponse;

    @FindBy(how = How.CSS, using = "#main-area > div > div:nth-child(1) > div > h3")
    public static WebElement title;

    @FindBy(id = "cc_name_on_card")
    public static WebElement nameOnCardField;

    @FindBy(how = How.CSS, using = "#main-area > div > div.trow.payment_details > div > div > div > div.user_details > label")
    public static WebElement label;


    @FindBy(id = "cc_card_number")
    public static WebElement cardNumberField;

    @FindBy(id = "cc_exp_month")
    public static WebElement expiryMonthField;

    @FindBy(id = "cc_exp_year")
    public static WebElement expiryYearField;

    @FindBy(id = "cc_cvv2")
    public static WebElement cvvCodeField;

    @FindBy(id = "continueButton")
    public static WebElement continueButton;

    public void initializeElements() {
        waitForIFrameToLoad(By.id("cashier-overlay"));
        waitForIFrameToLoad(By.id("scPage"));
        PageFactory.initElements( getWebDriver(), MakeDeposit.class );
        waitElementToBePresent( continueButton );
    }

    @Autowired
    public MakeDeposit(WebDriver webDriver) {
        super(webDriver);
    }

    public  WebElement getNameOnCardField() {
        return nameOnCardField;
    }

    public  WebElement getLabel() {
        return label;
    }

    public  WebElement getCardNumberField() {
        return cardNumberField;
    }

    public  WebElement getExpiryMonthField() {
        return expiryMonthField;
    }

    public  WebElement getExpiryYearField() {
        return expiryYearField;
    }

    public  WebElement getCvvCodeField() {
        return cvvCodeField;
    }

    public  WebElement getContinueButton() {
        return continueButton;
    }

    public  String getTitle() {
        return title.getText();
    }

    public WebElement getDepositResponse() {
        return depositResponse;
    }
}
