package com.cercli.employees.dbms.application.specification;

import com.cercli.employees.dbms.domain.entity.DomainEntity;
import org.springframework.lang.NonNull;

import java.util.List;

/**
 * Class representing a chain of specifications with AND operator
 */
public class CompositeSpecification<T extends DomainEntity> implements Specification<T> {

    private final List<Specification<T>> specifications;

    public CompositeSpecification(final @NonNull List<Specification<T>> specifications) {
        this.specifications = specifications;
    }

    @Override
    public Result<T> isSatisfiedBy(final @NonNull T obj) {
        for (final Specification<T> specification : specifications) {
            final Result<T> result = specification.isSatisfiedBy(obj);
            if (specification.isSatisfiedBy(obj).isFailed()) {
                return result;
            }
        }
        return new Result<>(obj, false, "");
    }
}
