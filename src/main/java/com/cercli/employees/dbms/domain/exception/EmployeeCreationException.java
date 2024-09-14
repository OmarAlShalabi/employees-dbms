package com.cercli.employees.dbms.domain.exception;

import org.springframework.lang.NonNull;

public class EmployeeCreationException extends RuntimeException {

    public EmployeeCreationException(final @NonNull String message) {
        super(message);
    }
}
