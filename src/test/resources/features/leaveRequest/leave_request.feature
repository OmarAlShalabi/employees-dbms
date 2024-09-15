Feature: Leave Request
  As a system user
  I want to create new Leave Requests with specific types

  Scenario: Successful Leave Request Creation
    When I create a new Leave Request With Type ("WORK_REMOTELY") from current time to next (7) days
    Then I should get new Leave Request With its Id.

  Scenario: Successful Overlapping Leave Requests
    Given I create a new Leave Request With Type ("ANNUAL_LEAVE") from current time to next (7) days
    When I create a new Leave Request With Type ("WORK_REMOTELY") from current time to next (3) days
    Then I should get new Leave Request With its Id.

  Scenario: Unsuccessful Overlapping Leave Requests
    Given I create a new Leave Request With Type ("ANNUAL_LEAVE") from current time to next (7) days
    When I create a new Leave Request With Type ("SICK_LEAVE") from current time to next (3) days
    Then New Request Should be denied with the following error ("cannot overlap request of type (SICK_LEAVE) with other types of leaves")
