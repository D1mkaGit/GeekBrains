Feature: Cart tests

  Scenario: Adding to cart
    Given I open web browser to main page
    When I click on add to cart button from main UI
    Then opens cart page with same product and price
    When I click on delete button
    Then cart goes empty

  Scenario: Adding to cart from product page
    Given I open web browser to main page
    And I navigate first product
    When I click on add to cart button from product page
    Then opens cart page with same product and price
    When I click on delete button
    Then cart goes empty


