Feature: Template application

  Scenario: Open configured application
    Given the configured application is opened
    Then the page heading should be "Example Domain"
