package com.maxbet.registration.model.components.interfaces;

/**
 * Created by Rodicad on 24/09/2017.
 */
public interface RegistrationForm {

    void setFirstName(String var1);

    void setSurname(String var1);

    void setTitle(String var1);

    void setDateOfBirth(String var1, String var2, String var3);

    void setDayOfBirth(String var1);

    void setMonthOfBirth(String var1);

    void setYearOfBirth(String var1);

    void setEmail(String var1);

    void setMobile(String var1);

    void setCountry(String var1);

    void setAddressLine1(String var1);

    void setAddressLine2(String var1);

    void setCity(String var1);

    void setCounty(String var1);

    void setPostalCode(String var1);

    void setUsername(String var1);

    void setPassword(String var1);

    void setSecurityQuestion(String var1);

    void setSecurityAnswer(String var1);

    void setCurrency(String var1);

    void setDepositLimit();

    void setTypeOfLimit(String var1);

    void setLimitAmount(String var1);

    void setPromoCode(String var1);

    void checkOver18();

    void uncheckOver18();

    void checkReceiveFreeBets();

    void uncheckReceiveFreeBets();

    String getFirstName();

    String getSurname();

    String getTitle();

    String getDateOfBirth();

    String getDayOfBirth();

    String getMonthOfBirth();

    String getYearOfBirth();

    String getEmail();

    String getMobile();

    String getCountry();

    String getAddressLine1();

    String getAddressLine2();

    String getCity();

    String getCounty();

    String getPostalCode();

    String getUsername();

    String getPassword();

    String getSecurityQuestion();

    String getSecurityAnswer();

    String getCurrency();

    String getDepositLimit();

    String getPromoCode();

    void clickOnMrTitle();

    void clickOnMsTitle();

    void clickOnMrsTitle();

    void clickOnMissTitle();

    void clickOnHide();

    void clickOnDailyLimit();

    void clickOnWeeklyLimit();

    void clickOnMonthlyLimit();

    void clickOnCreateAccount();

    boolean isDisplayed();

    void clickOnEnterAddressManualy();
}

