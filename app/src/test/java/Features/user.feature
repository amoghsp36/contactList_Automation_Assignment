Feature: Validating user register and login
  Scenario Outline: Verify if user is able to register successfully
    Given user payload with "<firstname>" "<lastname>" "<email>" "<password>"
    When calls the "AddUserAPI" with http "post" request
    Then API call was successful with status code 201
    And response body has "status" equal to "Created"

    Examples:
              |firstname   | lastname   | email     | password  |
              # |<firstname> | <lastname> | <email>   | <password>|
              |Test1     | User1    | contact_test1 | myPassword2|

    Scenario Outline: verify if user is able to login successfully
      Given user payload "<email>" "<password>"
      When calls the "UserLoginAPI" with http "post" request
      Then API call was successful with status code 200
      And response body has "status" equal to "OK"

      Examples:
                |email         | password   |
                |contact_test1 | myPassword2|

    Scenario Outline: verify if patch function is working
      Given user payload with "<firstname>" "<lastname>" "<email>" "<password>"
      When calls the "UpdateUserAPI" with http "patch" request
      Then API call was successful with status code 201
      And response body has "status" equal to "Created"

      Examples:
        |firstname   | lastname   | email     | password  |
              # |<firstname> | <lastname> | <email>   | <password>|
        |TestPatch1     | patchUser1    | contact_test1 | myPassword2|