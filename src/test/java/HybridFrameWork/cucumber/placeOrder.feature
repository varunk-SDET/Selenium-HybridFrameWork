@Checkout
Feature: Place order

  Background: 
    Given I have landed on ecommerce site


  @TC_001
  Scenario Outline: Positive test of placing an order
    Given Log in to the site using <username> and <password>
    When I add <productName> <sizeOption> <colorOption> to cart
    And Navigate to checkout page and place order <shippingMethod>
    Then order placed message is displayed

    Examples: 
      | username    | password          | productName        | colorOption | shippingMethod | sizeOption |
      | VK@mail.com | "iQc#fRS377n8j_9" | Atlas Fitness Tank | Blue        | Fixed          | M          |
