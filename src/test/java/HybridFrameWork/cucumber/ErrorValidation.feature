@errorValidation
Feature: Error Validation

  @TC_002
  Scenario Outline: verify error message is displayed on failure login
    Given I have landed on ecommerce site
    When Log in to the site using <username> and <password>
    Then login error message is displayed

    Examples: 
      | username    | password         |
      | VK@mail.com | "iQc#fRS377n8j_" |
