package com.cercli.employees.dbms.application.usecase.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

/**
 * DTO to update Employee info by ID.
 */
@AllArgsConstructor
@Getter
public class UpdateEmployeeCommand {

    private final String employeeId;
    private final String fullName;
    private final String email;
    private final String position;
    private final String currencyCode;
    private final BigDecimal salary;
}
