Feature: Create New Employee
  As a System User
  I want to create a new Employee

  Scenario: Successful Employee Creation
    When I want to create a new employee with the following Data.
      |  fullName | position   | email                  | salary | currencyCode   |
      |   Mike    |   SDE 2    | mike@mailService.com   | 4000   | USD            |
    When I want to edit the previous employee information to have a salary of (8000)
    Then I want to retrieve the newly created employee using their employee Id, and check updated salary info, and last modified date.
