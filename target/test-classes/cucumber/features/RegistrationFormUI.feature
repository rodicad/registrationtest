Feature: Happy path for registration

  @register_with_cookie
  Scenario: Print url
    When I open website under test
    Then correct page url should be displayed
    And cookie "_flow2,1" is set
    When user clicks on registration button
    Then registration form should be open
    When user registers with valid data
      | Ana | Dana | 01 | 01 | 1980 | strada Cepei | 11315 | Cluj | test@playtech.com | 645876555 | testAccount | testPssword |
    Then a deposit form should be displayed
    When user fills in deposit form with valid data
      | 4265037583286897 | 03 | 2019 | 218 |
    Then deposit should finish successfully



  @register_with_no_cookie
  Scenario: Print url
    When I open website under test
    Then correct page url should be displayed
    And cookie "_flow2,1" is deleted
    When user clicks on registration button
    Then registration form should be open
    When user registers with valid data
      | Ana | Dana | 01 | 01 | 1980 | strada Cepei | 11315 | Cluj | test@playtech.com | 645876555 | testAccount | testPssword |
    Then a deposit form should be displayed
    When user fills in deposit form with valid data
      | 4265032684276897 | 04 | 2019 | 338 |
    Then deposit should finish successfully



