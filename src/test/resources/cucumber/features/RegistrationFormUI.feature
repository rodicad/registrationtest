Feature: Access website under test

  @mainPage
  Scenario: Print url
    When I open website under test
    Then correct page url should be displayed
 #   And registration container should display correct data
    When user clicks on registration button
    Then registration form should be open
    And correct fields should be displayed in the registration form


