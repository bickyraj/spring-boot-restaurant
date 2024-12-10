Feature: i want to withdraw some balance
  from my account
  Scenario: withdraw balance
    Given I have 1000 euro in my account
    When I withdraw 400 euro
    Then the remaining balance should be 600 euro