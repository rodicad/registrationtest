Feature: Access website under test

  @mainPage
  Scenario: Print url
    When I open website under test
    Then correct page url should be displayed
    And registration container should display correct data
    When user clickon registration button
    Then registration form should be open


