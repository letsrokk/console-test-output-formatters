#language: en
Feature: Test TextContext

  @good
  Scenario: Create and reuse Helper
    When when in first class we create Helper instance
    Then we can use same instance in different class

  @good
  Scenario: Create and reuse object
    When when in first class we create object
    Then we can use existing object