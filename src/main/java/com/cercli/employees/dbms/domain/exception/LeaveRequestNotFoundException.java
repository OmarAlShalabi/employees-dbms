package com.cercli.employees.dbms.domain.exception;

import org.springframework.lang.NonNull;

public class LeaveRequestNotFoundException extends RuntimeException {

    public LeaveRequestNotFoundException(final @NonNull String message) {
        super(message);
    }
}
