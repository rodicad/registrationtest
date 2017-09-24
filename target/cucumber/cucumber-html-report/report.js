$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("RegistrationFormUI.feature");
formatter.feature({
  "line": 1,
  "name": "Access website under test",
  "description": "",
  "id": "access-website-under-test",
  "keyword": "Feature"
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
  "line": 7,
  "name": "registration container should display correct data",
  "keyword": "And "
});
formatter.step({
  "line": 8,
  "name": "user clickon registration button",
  "keyword": "When "
});
formatter.step({
  "line": 9,
  "name": "registration form should be open",
  "keyword": "Then "
});
formatter.match({
  "location": "RegistrationPageSteps.openWebsite()"
});
formatter.result({
  "duration": 2974096003,
  "status": "passed"
});
formatter.match({
  "location": "RegistrationPageSteps.correctPageUrlShouldBeDisplayed()"
});
formatter.result({
  "duration": 15419879,
  "status": "passed"
});
formatter.match({
  "location": "RegistrationPageSteps.registrationFormShouldDisplayCorrectData()"
});
formatter.result({
  "duration": 83519,
  "status": "passed"
});
formatter.match({
  "location": "RegistrationPageSteps.userOpensRegistrationForm()"
});
formatter.result({
  "duration": 68379090,
  "error_message": "org.openqa.selenium.ElementNotVisibleException: element not visible\n  (Session info: chrome\u003d60.0.3112.113)\n  (Driver info: chromedriver\u003d2.29.461591 (62ebf098771772160f391d75e589dc567915b233),platform\u003dWindows NT 6.1.7601 SP1 x86_64) (WARNING: The server did not provide any stacktrace information)\nCommand duration or timeout: 44 milliseconds\nBuild info: version: \u00273.0.1\u0027, revision: \u00271969d75\u0027, time: \u00272016-10-18 09:49:13 -0700\u0027\nSystem info: host: \u0027WHOL5001542\u0027, ip: \u0027192.168.1.34\u0027, os.name: \u0027Windows 7\u0027, os.arch: \u0027amd64\u0027, os.version: \u00276.1\u0027, java.version: \u00271.8.0_91\u0027\nDriver info: org.openqa.selenium.chrome.ChromeDriver\nCapabilities [{applicationCacheEnabled\u003dfalse, rotatable\u003dfalse, mobileEmulationEnabled\u003dfalse, networkConnectionEnabled\u003dfalse, chrome\u003d{chromedriverVersion\u003d2.29.461591 (62ebf098771772160f391d75e589dc567915b233), userDataDir\u003dC:\\Users\\rodicad\\AppData\\Local\\Temp\\scoped_dir25680_13959}, takesHeapSnapshot\u003dtrue, pageLoadStrategy\u003dnormal, databaseEnabled\u003dfalse, handlesAlerts\u003dtrue, hasTouchScreen\u003dfalse, version\u003d60.0.3112.113, platform\u003dXP, browserConnectionEnabled\u003dfalse, nativeEvents\u003dtrue, acceptSslCerts\u003dtrue, locationContextEnabled\u003dtrue, webStorageEnabled\u003dtrue, browserName\u003dchrome, takesScreenshot\u003dtrue, javascriptEnabled\u003dtrue, cssSelectorsEnabled\u003dtrue, unexpectedAlertBehaviour\u003d}]\nSession ID: 4cd7c32df9ca11214b96a196c4b81500\r\n\tat sun.reflect.NativeConstructorAccessorImpl.newInstance0(Native Method)\r\n\tat sun.reflect.NativeConstructorAccessorImpl.newInstance(NativeConstructorAccessorImpl.java:62)\r\n\tat sun.reflect.DelegatingConstructorAccessorImpl.newInstance(DelegatingConstructorAccessorImpl.java:45)\r\n\tat java.lang.reflect.Constructor.newInstance(Constructor.java:423)\r\n\tat org.openqa.selenium.remote.ErrorHandler.createThrowable(ErrorHandler.java:216)\r\n\tat org.openqa.selenium.remote.ErrorHandler.throwIfResponseFailed(ErrorHandler.java:168)\r\n\tat org.openqa.selenium.remote.RemoteWebDriver.execute(RemoteWebDriver.java:635)\r\n\tat org.openqa.selenium.remote.RemoteWebElement.execute(RemoteWebElement.java:274)\r\n\tat org.openqa.selenium.remote.RemoteWebElement.click(RemoteWebElement.java:84)\r\n\tat sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\r\n\tat sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\r\n\tat sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\r\n\tat java.lang.reflect.Method.invoke(Method.java:498)\r\n\tat org.openqa.selenium.support.pagefactory.internal.LocatingElementHandler.invoke(LocatingElementHandler.java:51)\r\n\tat com.sun.proxy.$Proxy31.click(Unknown Source)\r\n\tat com.maxbet.registration.model.pages.MaxebtMainPage.openRegistrationForm(MaxebtMainPage.java:83)\r\n\tat com.maxbet.registration.stepdefs.RegistrationPageSteps.userOpensRegistrationForm(RegistrationPageSteps.java:44)\r\n\tat ✽.When user clickon registration button(RegistrationFormUI.feature:8)\r\n",
  "status": "failed"
});
formatter.match({
  "location": "RegistrationPageSteps.registrationFormShouldBeOpen()"
});
formatter.result({
  "status": "skipped"
});
});