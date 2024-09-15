package com.cercli.employees.dbms.domain.exception;

import org.springframework.lang.NonNull;

public class LeaveRequestCreationException extends RuntimeException {

    public LeaveRequestCreationException(final @NonNull String message) {
        super(message);
    }
}
