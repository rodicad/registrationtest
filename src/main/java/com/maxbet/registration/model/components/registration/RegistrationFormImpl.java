package com.maxbet.registration.model.components.registration;

import com.maxbet.registration.model.pages.abstracts.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Rodicad on 24/09/2017.
 */

@Component
public class RegistrationFormImpl extends AbstractPage {

    @FindBy( how = How.CSS, using = "#short_form_container > form > div:nth-child(1) > label" )
    public static WebElement firstNameLabel;

    @FindBy( how = How.CSS, using = "#short_form_container > form > div:nth-child(2) > label" )
    public static WebElement lastNameLabel;

    @FindBy( how = How.CSS, using = "#short_form_container > form > div:nth-child(3) > label" )
    public static WebElement dateOfBirthLabel;

    @FindBy( how = How.CSS, using = "#short_form_container > form > div:nth-child(4) > label" )
    public static WebElement addressLabel;

    @FindBy( how = How.CSS, using = "#short_form_container > form > div:nth-child(7) > label" )
    public static WebElement userDetailsLabel;

    @FindBy( id = "firstname" )
    public static WebElement firstNameField;

    @FindBy( id = "lastname" )
    public static WebElement lastNameField;

    @FindBy( id = "day" )
    public static WebElement dayOfBirthField;

    @FindBy( id = "month" )
    public static WebElement monthOfBirthField;

    @FindBy( id = "year" )
    public static WebElement yearOfBirthField;

    @FindBy( id = "address" )
    public static WebElement addressField;

    @FindBy(how = How.CSS, using = "#short_form_container > form > button")
    public static WebElement nextStepButton;

    @Autowired
    public RegistrationFormImpl( WebDriver webDriver ) {
       super (webDriver );
    }

    public void initializeElements() {
        PageFactory.initElements( getWebDriver(), RegistrationFormImpl.class );
        waitElementToBePresent( nextStepButton );
        System.out.println("first name label : "+firstNameLabel.getText());
        System.out.println("last  name label : "+lastNameLabel.getText());
        System.out.println("date of birth label : "+dateOfBirthLabel.getText());
        System.out.println("Address label : "+addressLabel.getText());
        System.out.println("date utiliziatr label : "+userDetailsLabel.getText());
    }

    public String getFirstNameLabel() {
        return firstNameLabel.getText();
    }

    public String getLastNameLabel() {
        return lastNameLabel.getText();
    }

    public String getDateOfBirthLabel() {
        return dateOfBirthLabel.getText();
    }

    public String getAddressLabel() {
        return addressLabel.getText();
    }

    public String getUserDetailsLabel() {
        return userDetailsLabel.getText();
    }

}