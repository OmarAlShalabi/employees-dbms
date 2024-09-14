package com.cercli.employees.dbms.domain.specification;

import com.cercli.employees.dbms.domain.entity.DomainEntity;
import org.springframework.lang.NonNull;

/**
 * Specification pattern to validate against business rule
 * @param <T> domain Entiy
 */
public interface Specification<T extends DomainEntity> {
    boolean isSatisfiedBy(final @NonNull T obj);
}
