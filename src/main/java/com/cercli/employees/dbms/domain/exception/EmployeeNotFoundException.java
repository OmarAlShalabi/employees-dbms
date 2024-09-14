package com.cercli.employees.dbms.domain.exception;

import org.springframework.lang.NonNull;

public class EmployeeNotFoundException extends RuntimeException {

    public EmployeeNotFoundException(final @NonNull String message) {
        super(message);
    }
}
