#language: en
Feature: Test TextContext

  Scenario: Create and reuse Helper
    When when in first class we create Helper instance
    Then we can use same instance in different class

  Scenario: Create and reuse object
    When when in first class we create object
    Then we can use existing object