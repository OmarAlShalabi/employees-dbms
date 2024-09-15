Feature: Leave Request
  As a system user
  I want to create new Leave Requests with specific types

  Scenario: Successful Leave Request Creation
    When I create a new Leave Request With Type ("Work Remotely") from current time to next (7) days
    Then I should get new Leave Request With its Id.

  Scenario: Successful Overlapping Leave Requests
    Given I create a new Leave Request With Type ("Annual Leave") from current time to next (7) days
    When I create a new Leave Request With Type ("Work Remotely") from current time to next (3) days
    Then I should get new Leave Request With its Id.
