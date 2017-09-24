package com.maxbet.registration.model.pages;

import com.maxbet.registration.model.components.HeaderComponent;
import com.maxbet.registration.model.components.registration.RegistrationContainer;
import com.maxbet.registration.model.components.SportsPromos;
import com.maxbet.registration.model.components.registration.RegistrationFormButton;
import com.maxbet.registration.model.components.registration.RegistrationFormImpl;
import com.maxbet.registration.model.pages.abstracts.AbstractPage;
import com.maxbet.registration.model.pages.abstracts.WebButton;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource( "classpath:application.properties" )
public class MaxebtMainPage extends AbstractPage {

    @Value( "${maxbet.main.page.url}" )
    public String maxbetPageUrl;

    @FindBy(id = "regForm")
    public static WebElement regForm;

    private RegistrationContainer registrationContainer;

    @FindBy(id="fb-reg-title-container")
    public static WebElement registrationHeader;

    @FindBy(id = "fb-reg-title")
    public static WebElement registrationContainerTitle;
    @FindBy(id = "fb-reg-subtitle")
    public static WebElement registrationContainerSubTitle;
    @FindBy(id = "fb-reg-checkmark")
    public static WebElement registrationContainerCheckmark;

    @FindBy(id="showForm")
    public static WebElement registrationButton;

    @FindBy(how = How.CSS, using = "#fbenabled > div.fb-login-btn")
    public static WebElement fbRegistrationButton;

    private HeaderComponent header;

    private SportsPromos sportsPromos;

    private RegistrationFormImpl registrationDataForm;

    public void open() throws Exception {
        super.open( maxbetPageUrl, MaxebtMainPage.class );
        buildRegistrationForm();
        System.out.println("Is reg butotn open: "+isRegistrationFormOpen());

        openRegistrationForm();
        System.out.println("Is reg butotn open, cause it should:  "+isRegistrationFormOpen());
    }

    public RegistrationFormImpl getRegistrationDataForm() {
        if (registrationDataForm == null) {
            registrationDataForm =  new RegistrationFormImpl();
        }
        return registrationDataForm;
    }




    private void buildRegistrationForm() {
        registrationContainer = new RegistrationContainer();
        registrationContainer.setTitle(registrationContainerTitle.getText());
        registrationContainer.setSubtitle(registrationContainerSubTitle.getText());
        registrationContainer.setCheckmark(registrationContainerCheckmark.getText());

        registrationContainer.setFbRegistrationButton(new WebButton(fbRegistrationButton));
        registrationContainer.setRegistrationButton(new WebButton(registrationButton));

    }

    public void openRegistrationForm() {
        registrationButton.click();
    }

    public boolean isRegistrationFormOpen() {
        return (!registrationContainer.getRegistrationButton().getButtonLocator().getAttribute("style").isEmpty());
    }

    public RegistrationContainer getRegistrationContainer() {
        return registrationContainer;
    }



}
