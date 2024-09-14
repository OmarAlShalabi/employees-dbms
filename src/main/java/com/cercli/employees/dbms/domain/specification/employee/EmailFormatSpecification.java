package com.cercli.employees.dbms.domain.specification.employee;

import com.cercli.employees.dbms.domain.entity.Employee;
import com.cercli.employees.dbms.domain.entity.Result;
import com.cercli.employees.dbms.domain.specification.Specification;
import org.springframework.lang.NonNull;

import java.util.regex.Pattern;

public class EmailFormatSpecification implements Specification<Employee> {

    private static final String EMAIL_FORMAT_REGEX = "^(.+)@(\\S+)$";

    @Override
    public Result<Employee> isSatisfiedBy(final @NonNull Employee obj) {
        final boolean matchRes = Pattern.compile(EMAIL_FORMAT_REGEX).matcher(obj.getEmail()).matches();
        if (matchRes) {
            return new Result<>(obj, false, "");
        } else {
            return new Result<>(obj, true, "Wrong Email Format");
        }
    }
}
