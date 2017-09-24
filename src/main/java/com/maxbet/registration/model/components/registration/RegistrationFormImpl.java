package com.maxbet.registration.model.components.registration;

import com.maxbet.registration.model.components.interfaces.RegistrationForm;
import com.maxbet.registration.model.pages.MaxebtMainPage;
import com.maxbet.registration.model.pages.abstracts.AbstractPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Rodicad on 24/09/2017.
 */

public class RegistrationFormImpl extends MaxebtMainPage implements RegistrationForm {

    @FindBy(how = How.CSS, using = "#short_form_container > form > div:nth-child(1)")
     public static WebElement prenume;
    @FindBy( how = How.CSS, using = " #short_form_container > form > div" )
    public static List<WebElement> registrationFields;

    public RegistrationFormImpl() {
        PageFactory.initElements( super.getWebDriver(), RegistrationFormImpl.class );
        System.out.println("Label frm reg form: "+prenume.getText());
        System.out.println("Labels total reg form: "+registrationFields.size());
    }
    @Override
    public void setFirstName(String var1) {

    }

    @Override
    public void setSurname(String var1) {

    }

    @Override
    public void setTitle(String var1) {

    }

    @Override
    public void setDateOfBirth(String var1, String var2, String var3) {

    }

    @Override
    public void setDayOfBirth(String var1) {

    }

    @Override
    public void setMonthOfBirth(String var1) {

    }

    @Override
    public void setYearOfBirth(String var1) {

    }

    @Override
    public void setEmail(String var1) {

    }

    @Override
    public void setMobile(String var1) {

    }

    @Override
    public void setCountry(String var1) {

    }

    @Override
    public void setAddressLine1(String var1) {

    }

    @Override
    public void setAddressLine2(String var1) {

    }

    @Override
    public void setCity(String var1) {

    }

    @Override
    public void setCounty(String var1) {

    }

    @Override
    public void setPostalCode(String var1) {

    }

    @Override
    public void setUsername(String var1) {

    }

    @Override
    public void setPassword(String var1) {

    }

    @Override
    public void setSecurityQuestion(String var1) {

    }

    @Override
    public void setSecurityAnswer(String var1) {

    }

    @Override
    public void setCurrency(String var1) {

    }

    @Override
    public void setDepositLimit() {

    }

    @Override
    public void setTypeOfLimit(String var1) {

    }

    @Override
    public void setLimitAmount(String var1) {

    }

    @Override
    public void setPromoCode(String var1) {

    }

    @Override
    public void checkOver18() {

    }

    @Override
    public void uncheckOver18() {

    }

    @Override
    public void checkReceiveFreeBets() {

    }

    @Override
    public void uncheckReceiveFreeBets() {

    }

    @Override
    public String getFirstName() {
        return null;
    }

    @Override
    public String getSurname() {
        return null;
    }

    @Override
    public String getTitle() {
        return null;
    }

    @Override
    public String getDateOfBirth() {
        return null;
    }

    @Override
    public String getDayOfBirth() {
        return null;
    }

    @Override
    public String getMonthOfBirth() {
        return null;
    }

    @Override
    public String getYearOfBirth() {
        return null;
    }

    @Override
    public String getEmail() {
        return null;
    }

    @Override
    public String getMobile() {
        return null;
    }

    @Override
    public String getCountry() {
        return null;
    }

    @Override
    public String getAddressLine1() {
        return null;
    }

    @Override
    public String getAddressLine2() {
        return null;
    }

    @Override
    public String getCity() {
        return null;
    }

    @Override
    public String getCounty() {
        return null;
    }

    @Override
    public String getPostalCode() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getSecurityQuestion() {
        return null;
    }

    @Override
    public String getSecurityAnswer() {
        return null;
    }

    @Override
    public String getCurrency() {
        return null;
    }

    @Override
    public String getDepositLimit() {
        return null;
    }

    @Override
    public String getPromoCode() {
        return null;
    }

    @Override
    public void clickOnMrTitle() {

    }

    @Override
    public void clickOnMsTitle() {

    }

    @Override
    public void clickOnMrsTitle() {

    }

    @Override
    public void clickOnMissTitle() {

    }

    @Override
    public void clickOnHide() {

    }

    @Override
    public void clickOnDailyLimit() {

    }

    @Override
    public void clickOnWeeklyLimit() {

    }

    @Override
    public void clickOnMonthlyLimit() {

    }

    @Override
    public void clickOnCreateAccount() {

    }

    @Override
    public boolean isDisplayed() {
        return false;
    }

    @Override
    public void clickOnEnterAddressManualy() {

    }
}
