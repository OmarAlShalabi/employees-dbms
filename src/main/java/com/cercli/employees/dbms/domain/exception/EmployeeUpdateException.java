package com.cercli.employees.dbms.domain.exception;

import org.springframework.lang.NonNull;

public class EmployeeUpdateException extends RuntimeException {

    public EmployeeUpdateException(final @NonNull String message) {
        super(message);
    }
}
