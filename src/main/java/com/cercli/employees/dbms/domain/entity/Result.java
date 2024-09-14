package com.cercli.employees.dbms.domain.entity;

import lombok.Getter;
import org.springframework.lang.NonNull;

/**
 * Wrapper class to include Domain Entity with possible business errors.
 * @param <T> Domain Entity Object
 */
@Getter
public class Result<T extends DomainEntity> {

    private final T entity;
    private final boolean failed;
    private final String errorMessage;

    public Result(final @NonNull T entity, final @NonNull boolean failed, final @NonNull String errorMessage) {
        this.entity = entity;
        this.failed = failed;
        this.errorMessage = errorMessage;
    }
}
