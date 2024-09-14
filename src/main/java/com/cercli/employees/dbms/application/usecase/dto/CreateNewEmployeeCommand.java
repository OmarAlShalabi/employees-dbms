package com.cercli.employees.dbms.application.usecase.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

/**
 * DTO to include parameters to create a new employee
 */
@AllArgsConstructor
@Getter
public class CreateNewEmployeeCommand {

    private final String fullName;
    private final String email;
    private final String position;
    private final String currencyCode;
    private final BigDecimal salary;
}
