package com.cercli.employees.dbms.domain.specification;

import com.cercli.employees.dbms.domain.entity.DomainEntity;
import com.cercli.employees.dbms.domain.entity.Result;
import org.springframework.lang.NonNull;

/**
 * Specification pattern to validate against business rule
 * @param <T> Domain Entity
 */
public interface Specification<T extends DomainEntity> {
    Result<T> isSatisfiedBy(final @NonNull T obj);
}
