package com.maxbet.registration.stepdefs;

import com.maxbet.registration.config.TestConfig;
import com.maxbet.registration.model.components.registration.MakeDeposit;
import com.maxbet.registration.model.components.registration.RegistrationFormImpl;
import com.maxbet.registration.model.pages.MaxebtMainPage;
import com.maxbet.registration.stepdefs.expectedData.RegistrationTestValidator;
import com.maxbet.registration.utils.StringUtils;
import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

@ContextConfiguration( classes = TestConfig.class )
public class RegistrationPageSteps  {
    private static final int DEFAULT_STRING_LENGTH = 5;
    private boolean isCookieSet = false;
    private  MaxebtMainPage mainPage;
    private RegistrationFormImpl registrationForm;
    private MakeDeposit makeDepositForm;

    @Autowired
    public RegistrationPageSteps(MaxebtMainPage mainPage, RegistrationFormImpl registrationForm,  MakeDeposit makeDepositForm) {
        this.mainPage= mainPage;
        this.registrationForm = registrationForm;
        this.makeDepositForm = makeDepositForm;
    }

    @Given( "^I open website under test$" )
    public void openWebsite() throws Exception {
        mainPage.open();
    }

    @Then( "^correct page url should be displayed$" )
    public void correctPageUrlShouldBeDisplayed() throws Throwable {
        Assert.assertEquals(RegistrationTestValidator.PAGE_TITLE, mainPage.getPageTitle());
    }

    @And("^registration container should display correct data$")
    public void registrationFormShouldDisplayCorrectData() throws Throwable {
        Assert.assertEquals(RegistrationTestValidator.REGISTRATION_FORM_TITLE, mainPage.getRegistrationContainer().getTitle());
        Assert.assertEquals(RegistrationTestValidator.REGISTRATION_FORM_SUBTITLE, mainPage.getRegistrationContainer().getSubtitle());
        Assert.assertEquals(RegistrationTestValidator.REGISTRATION_FORM_CHECKMARK, mainPage.getRegistrationContainer().getCheckmark());

        Assert.assertEquals(RegistrationTestValidator.REGISTRATION_FB_BUTTON_NAME, mainPage.getRegistrationContainer().getFbRegistrationButton().getButtonName());
        Assert.assertEquals(RegistrationTestValidator.REGISTRATION_BUTTON_NAME, mainPage.getRegistrationContainer().getRegistrationButton().getButtonName());
    }

    @When("^user clicks on registration button")
    public void userOpensRegistrationForm() throws Throwable {
        mainPage.openRegistrationForm();
    }

    @Then("^registration form should be open$")
    public void registrationFormShouldBeOpen() throws Throwable {
        Assert.assertTrue( mainPage.isRegistrationFormOpen() );
        registrationForm.initializeElements();
    }

    @And( "^correct fields should be displayed in the registration form$" )
    public void correctFieldsShouldBeDisplayedInTheRegistrationForm() throws Throwable {
        Assert.assertEquals(RegistrationTestValidator.FIRST_NAME_LABEL,registrationForm.getFirstNameLabel());
        Assert.assertEquals(RegistrationTestValidator.LAST_NAME_LABEL,registrationForm.getLastNameLabel());
        Assert.assertEquals(RegistrationTestValidator.DATE_OF_BIRTH_LABEL,registrationForm.getDateOfBirthLabel());
        Assert.assertEquals(RegistrationTestValidator.ADDRESS_LABEL,registrationForm.getAddressLabel());
        Assert.assertEquals(RegistrationTestValidator.USER_DETAILS_LABEL,registrationForm.getUserDetailsLabel());
    }

    @When("^user registers with valid data$")
    public void userRegistersValidData(DataTable userInfo) throws Throwable {
        List<List<String>> data = userInfo.raw();
        String randomString = StringUtils.generateRandomString(DEFAULT_STRING_LENGTH);
        String randomNumber = StringUtils.generateRandomNumberAsString(DEFAULT_STRING_LENGTH);

        String firstName = data.get(0).get(0) + randomString;
        String lastName = data.get(0).get(1) + randomString;
        String randomUserName = data.get(0).get(10)+ randomString;
        String password = data.get(0).get(11)+ randomString;

        registrationForm.getFirstNameField().sendKeys(firstName);
        registrationForm.getLastNameField().sendKeys(lastName);
        registrationForm.getDayOfBirthField().sendKeys(data.get(0).get(2));
        registrationForm.getMonthOfBirthField().sendKeys(data.get(0).get(3));
        registrationForm.getYearOfBirthField().sendKeys(data.get(0).get(4));
        registrationForm.getEmailField().sendKeys(data.get(0).get(8));
        registrationForm.getUsernameField().sendKeys(randomUserName);
        registrationForm.getPasswordField().sendKeys(password);
        registrationForm.getOver18checkBox().click();

        if (isCookieSet) {
            String address = data.get(0).get(5) + randomString;
            String postCode = data.get(0).get(6) + randomNumber;
            String city = data.get(0).get(7) + randomString;
            String phone = data.get(0).get(9) + randomNumber;

            registrationForm.getAddressField().sendKeys(address);
            registrationForm.getPostCodeField().sendKeys(postCode);
            registrationForm.getCityField().sendKeys(city);
            registrationForm.getPhoneField().sendKeys(phone);
        }

        registrationForm.getNextStepButton().click();

    }

    @Then("^a deposit form should be displayed$")
    public void aDepositFormShouldBeDisplayed() throws Throwable {
        makeDepositForm.initializeElements();
        Assert.assertEquals(RegistrationTestValidator.DEPOSIT_FORM_TITLE, makeDepositForm.getTitle());
    }

    @When("^user fills in deposit form with valid data$")
    public void userFillsInDepositFormWithValidData(DataTable cardDetails) throws Throwable {
        List<List<String>> cardData = cardDetails.raw();
        makeDepositForm.getCardNumberField().sendKeys(cardData.get(0).get(0));
        makeDepositForm.getExpiryMonthField().sendKeys(cardData.get(0).get(1));
        makeDepositForm.getExpiryYearField().sendKeys(cardData.get(0).get(2));
        makeDepositForm.getCvvCodeField().sendKeys(cardData.get(0).get(3));
        makeDepositForm.getContinueButton().click();
    }

    @And("^cookie \"([^\"]*)\" is set$")
    public void cookieIsSet(String cookieInfo) throws Throwable {
        String[] cookieData = cookieInfo.split(",");
        mainPage.getWebDriver().manage().addCookie(new Cookie(cookieData[0], cookieData[1]));
        isCookieSet = true;

    }

    @And("^cookie \"([^\"]*)\" is deleted$")
    public void cookieIsDeleted(String cookieInfo) throws Throwable {
        String[] cookieData = cookieInfo.split(",");
        mainPage.getWebDriver().manage().deleteCookie(new Cookie(cookieData[0], cookieData[1]));
        isCookieSet = false;
    }

    @Then("^deposit should finish successfully$")
    public void depositShouldFinishSuccessfully() throws Throwable {
        Assert.assertEquals(RegistrationTestValidator.SUCCESFULL_DEPOSIT_LABEL,  makeDepositForm.waitElementToBePresent(makeDepositForm.getDepositResponse(),20).getText());
        makeDepositForm.waitElementToBePresentByLocator(
                By.cssSelector(
                        "#application > div.page.fn-page.page--menu-children-contains > div.header.fn-header > div.fn-navigation-container.navigation-container.common-head > div.page-header.fn-header-container > div > div.main-header__common.main-header__common--desktop.common-top > div.col-md-9.common-top-controls > a.main-header__icon.main-header__icon--balance.common-icon-balance > span.main-header__balance-icon.micon-header-user-balance.common-icon"),50);
    }
}
