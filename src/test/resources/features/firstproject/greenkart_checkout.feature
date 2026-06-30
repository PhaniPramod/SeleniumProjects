@ui @smoke @greenkart
Feature: GreenKart checkout

  Scenario: Place an order successfully
    Given the GreenKart store is open
    When I add configured products to the cart
    And I proceed to checkout
    And I place the order from the cart summary
    And I select the configured country
    And I agree to the terms and conditions
    And I proceed with the order
    Then the order success message should be displayed
