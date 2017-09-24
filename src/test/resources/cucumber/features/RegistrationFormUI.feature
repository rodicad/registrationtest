Feature: Access website under test

  @mainPage
  Scenario: Print url
    When I open website under test
    Then correct page url should be displayed