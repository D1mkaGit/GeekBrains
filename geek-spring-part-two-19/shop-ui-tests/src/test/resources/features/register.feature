Feature: Register

  Scenario Outline: Successful Register and appras on profile page
    Given I open web browser
    When I open home page and navigate to register page
    And I provide username as "<username>", emailAddress as "<emailAddress>" and password as "<password>"
    And I click on register button
    Then name should be "<name>" and email should be "<emailAddress>" on user page
    Then delete test user with name "<name>"


    Examples:
      | username | emailAddress | password | name|
      | test | test@test.ee | test | test |