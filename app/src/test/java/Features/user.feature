Feature: Validating user register and login
  Scenario Outline: Verify if user is able to register successfully
    Given user payload with "<firstname>" "<lastname>" "<email>" "<password>"
    When calls the "AddUserAPI" with http "post" request
    Then API call was successful with status code 201
    # And response body has "status" equal to "Created"

    Examples:
              |firstname   | lastname   | email     | password  |
              # |<firstname> | <lastname> | <email>   | <password>|
              |Test15     | User15    | contact_test15@gmail.com | myPassword15|

    Scenario Outline: verify if user is able to login successfully
      Given user payload "<email>" "<password>"
      When calls the "UserLoginAPI" with http "post" request
      Then API call was successful with status code 200
      # And response body has "status" equal to "OK"

      Examples:
                |email         | password   |
                |contact_test8@gmail.com | myPassword8|


    Scenario: verify whether user can be fetched or not
      Given Authorization header with login token
      When calls the "GetUserAPI" with http "get" request
      Then API call was successful with status code 200


    Scenario Outline: verify if patch function is working
      Given user payload with "<firstname>" "<lastname>" "<email>" "<password>"
      When calls the "UpdateUserAPI" with http "patch" request
      Then API call was successful with status code 201
      # And response body has "status" equal to "Created"

      Examples:
        |firstname   | lastname   | email     | password  |
              # |<firstname> | <lastname> | <email>   | <password>|
        |TestPatch1     | patchUser1    | contact_test1patch@gmail.com | myPassword2patch|

