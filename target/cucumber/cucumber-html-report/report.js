$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("RegistrationFormUI.feature");
formatter.feature({
  "line": 1,
  "name": "Access website under test",
  "description": "",
  "id": "access-website-under-test",
  "keyword": "Feature"
});
formatter.before({
  "duration": 7214077,
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
  "duration": 2488557018,
  "status": "passed"
});
formatter.match({
  "location": "RegistrationPageSteps.correctPageUrlShouldBeDisplayed()"
});
formatter.result({
  "duration": 8445863,
  "status": "passed"
});
formatter.match({
  "location": "RegistrationPageSteps.userOpensRegistrationForm()"
});
formatter.result({
  "duration": 191447811,
  "status": "passed"
});
formatter.match({
  "location": "RegistrationPageSteps.registrationFormShouldBeOpen()"
});
formatter.result({
  "duration": 96287959,
  "status": "passed"
});
formatter.match({
  "location": "RegistrationPageSteps.correctFieldsShouldBeDisplayedInTheRegistrationForm()"
});
formatter.result({
  "duration": 1158370492,
  "status": "passed"
});
formatter.after({
  "duration": 801433258,
  "status": "passed"
});
});