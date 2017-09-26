$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("RegistrationFormUI.feature");
formatter.feature({
  "line": 1,
  "name": "Happy path for registration",
  "description": "",
  "id": "happy-path-for-registration",
  "keyword": "Feature"
});
formatter.before({
  "duration": 12452830,
  "status": "passed"
});
formatter.scenario({
  "line": 20,
  "name": "Print url",
  "description": "",
  "id": "happy-path-for-registration;print-url",
  "type": "scenario",
  "keyword": "Scenario",
  "tags": [
    {
      "line": 19,
      "name": "@register_with_no_cookie"
    }
  ]
});
formatter.step({
  "line": 21,
  "name": "I open website under test",
  "keyword": "When "
});
formatter.step({
  "line": 22,
  "name": "correct page url should be displayed",
  "keyword": "Then "
});
formatter.step({
  "line": 23,
  "name": "cookie \"_flow2,1\" is deleted",
  "keyword": "And "
});
formatter.step({
  "line": 24,
  "name": "user clicks on registration button",
  "keyword": "When "
});
formatter.step({
  "line": 25,
  "name": "registration form should be open",
  "keyword": "Then "
});
formatter.step({
  "line": 26,
  "name": "user registers with valid data",
  "rows": [
    {
      "cells": [
        "Ana",
        "Dana",
        "01",
        "01",
        "1980",
        "strada Cepei",
        "11315",
        "Cluj",
        "test@playtech.com",
        "645876555",
        "testAccount",
        "testPssword"
      ],
      "line": 27
    }
  ],
  "keyword": "When "
});
formatter.step({
  "line": 28,
  "name": "a deposit form should be displayed",
  "keyword": "Then "
});
formatter.step({
  "line": 29,
  "name": "user fills in deposit form with valid data",
  "rows": [
    {
      "cells": [
        "4265032684276897",
        "04",
        "2019",
        "338"
      ],
      "line": 30
    }
  ],
  "keyword": "When "
});
formatter.step({
  "line": 31,
  "name": "deposit should finish successfully",
  "keyword": "Then "
});
formatter.match({
  "location": "RegistrationPageSteps.openWebsite()"
});
formatter.result({
  "duration": 5613854676,
  "status": "passed"
});
formatter.match({
  "location": "RegistrationPageSteps.correctPageUrlShouldBeDisplayed()"
});
formatter.result({
  "duration": 8063834,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "_flow2,1",
      "offset": 8
    }
  ],
  "location": "RegistrationPageSteps.cookieIsDeleted(String)"
});
formatter.result({
  "duration": 63648586,
  "status": "passed"
});
formatter.match({
  "location": "RegistrationPageSteps.userOpensRegistrationForm()"
});
formatter.result({
  "duration": 168946630,
  "status": "passed"
});
formatter.match({
  "location": "RegistrationPageSteps.registrationFormShouldBeOpen()"
});
formatter.result({
  "duration": 1053242679,
  "status": "passed"
});
formatter.match({
  "location": "RegistrationPageSteps.userRegistersValidData(DataTable)"
});
formatter.result({
  "duration": 1011456264,
  "status": "passed"
});
formatter.match({
  "location": "RegistrationPageSteps.aDepositFormShouldBeDisplayed()"
});
formatter.result({
  "duration": 5462183747,
  "status": "passed"
});
formatter.match({
  "location": "RegistrationPageSteps.userFillsInDepositFormWithValidData(DataTable)"
});
formatter.result({
  "duration": 935408640,
  "status": "passed"
});
formatter.match({
  "location": "RegistrationPageSteps.depositShouldFinishSuccessfully()"
});
formatter.result({
  "duration": 2901594399,
  "error_message": "org.junit.ComparisonFailure: expected:\u003c[Succe]s\u003e but was:\u003c[Respin]s\u003e\r\n\tat org.junit.Assert.assertEquals(Assert.java:115)\r\n\tat org.junit.Assert.assertEquals(Assert.java:144)\r\n\tat com.maxbet.registration.stepdefs.RegistrationPageSteps.depositShouldFinishSuccessfully(RegistrationPageSteps.java:148)\r\n\tat ✽.Then deposit should finish successfully(RegistrationFormUI.feature:31)\r\n",
  "status": "failed"
});
formatter.after({
  "duration": 1607107328,
  "status": "passed"
});
});