$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("RegistrationFormUI.feature");
formatter.feature({
  "line": 1,
  "name": "Access website under test",
  "description": "",
  "id": "access-website-under-test",
  "keyword": "Feature"
});
formatter.before({
  "duration": 3199573,
  "status": "passed"
});
formatter.scenario({
  "line": 4,
  "name": "Print url",
  "description": "",
  "id": "access-website-under-test;print-url",
  "type": "scenario",
  "keyword": "Scenario",
  "tags": [
    {
      "line": 3,
      "name": "@mainPage"
    }
  ]
});
formatter.step({
  "line": 5,
  "name": "I open website under test",
  "keyword": "When "
});
formatter.step({
  "line": 6,
  "name": "correct page url should be displayed",
  "keyword": "Then "
});
formatter.step({
  "comments": [
    {
      "line": 7,
      "value": "#   And registration container should display correct data"
    }
  ],
  "line": 8,
  "name": "user clicks on registration button",
  "keyword": "When "
});
formatter.step({
  "line": 9,
  "name": "registration form should be open",
  "keyword": "Then "
});
formatter.step({
  "line": 10,
  "name": "correct fields should be displayed in the registration form",
  "keyword": "And "
});
formatter.match({
  "location": "RegistrationPageSteps.openWebsite()"
});
formatter.result({
  "duration": 2785313280,
  "status": "passed"
});
formatter.match({
  "location": "RegistrationPageSteps.correctPageUrlShouldBeDisplayed()"
});
formatter.result({
  "duration": 9745067,
  "status": "passed"
});
formatter.match({
  "location": "RegistrationPageSteps.userOpensRegistrationForm()"
});
formatter.result({
  "duration": 249106347,
  "status": "passed"
});
formatter.match({
  "location": "RegistrationPageSteps.registrationFormShouldBeOpen()"
});
formatter.result({
  "duration": 631934720,
  "status": "passed"
});
formatter.match({
  "location": "RegistrationPageSteps.correctFieldsShouldBeDisplayedInTheRegistrationForm()"
});
formatter.result({
  "duration": 543737600,
  "error_message": "org.junit.ComparisonFailure: expected:\u003c[Adresă]\u003e but was:\u003c[Email]\u003e\r\n\tat org.junit.Assert.assertEquals(Assert.java:115)\r\n\tat org.junit.Assert.assertEquals(Assert.java:144)\r\n\tat com.maxbet.registration.stepdefs.RegistrationPageSteps.correctFieldsShouldBeDisplayedInTheRegistrationForm(RegistrationPageSteps.java:74)\r\n\tat ✽.And correct fields should be displayed in the registration form(RegistrationFormUI.feature:10)\r\n",
  "status": "failed"
});
formatter.after({
  "duration": 900570453,
  "status": "passed"
});
});