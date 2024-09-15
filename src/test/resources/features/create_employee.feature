Feature: Create New Employee
  As a System User
  I want to create a new Employee

  Scenario: Successful Employee Creation
    Given I want to create a new employee with the following Data.
      |  fullName | position   | email                  | salary | currencyCode   |
      |   Mike    |   SDE 2    | mike@mailService.com   | 4000   | USD            |
    Then I want to retrieve the newly created employee using their employee Id
