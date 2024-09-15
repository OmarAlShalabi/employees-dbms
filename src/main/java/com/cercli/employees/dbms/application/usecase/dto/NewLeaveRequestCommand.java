package com.cercli.employees.dbms.application.usecase.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.ZonedDateTime;

/**
 * DTO to include parameters to create a new employee
 */
@AllArgsConstructor
@Getter
public class NewLeaveRequestCommand {

    private final String requestType;
    private final String employeeId;
    private final ZonedDateTime startTime;
    private final ZonedDateTime endTime;
}
