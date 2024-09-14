package com.cercli.employees.dbms.domain.specification.employee;

import com.cercli.employees.dbms.domain.entity.Employee;
import com.cercli.employees.dbms.domain.entity.Result;
import com.cercli.employees.dbms.domain.specification.Specification;
import org.springframework.lang.NonNull;

public class FullNameLengthSpecification implements Specification<Employee> {

    private final int fullNameLength;

    public FullNameLengthSpecification(final int fullNameLength) {
        this.fullNameLength = fullNameLength;
    }

    @Override
    public Result<Employee> isSatisfiedBy(final @NonNull Employee obj) {
        if (obj.getFullName().length() > fullNameLength) {
            return new Result<>(obj, true, "Name exceeded max length of " + fullNameLength);
        } else {
            return new Result<>(obj, false, "");
        }
    }
}
