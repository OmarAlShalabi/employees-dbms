package com.cercli.employees.dbms.application.specification;

import com.cercli.employees.dbms.domain.entity.DomainEntity;
import org.springframework.lang.NonNull;

/**
 * Specification pattern to validate against business rule
 * @param <T> Domain Entity
 */
public interface Specification<T extends DomainEntity> {
    Result<T> isSatisfiedBy(final @NonNull T obj);
}
