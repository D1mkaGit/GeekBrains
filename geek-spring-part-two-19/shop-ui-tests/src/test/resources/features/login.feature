Feature: Login

  Scenario Outline: Successful Login to the admin page and logout after
    Given I open web browser to admin login page
    And I provide username as "<username>" and password as "<password>"
    And I click on login button
    Then name should be "<name>"
    When Open dropdown menu
    And click logout button
    Then user logged out

    Examples:
      | username | password | name |
      | admin | admin | admin |

  Scenario Outline: Successful Login to the user page and logout after
    Given I open web browser to login page
    And I provide username as "<username>" and password as "<password>" on user login page
    And I click on login button on user login page
    Then name should be "<name>" on user page
    When Open account dropdown menu on site and click logout
    Then user logged out from profile

    Examples:
      | username | password | name |
      | guest | admin | guest |

