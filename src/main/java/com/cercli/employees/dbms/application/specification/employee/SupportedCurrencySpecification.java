package com.cercli.employees.dbms.application.specification.employee;

import com.cercli.employees.dbms.domain.entity.Employee;
import com.cercli.employees.dbms.application.specification.Result;
import com.cercli.employees.dbms.application.specification.Specification;
import org.springframework.lang.NonNull;

import java.util.Set;

public class SupportedCurrencySpecification implements Specification<Employee> {

    private final Set<String> supportedCurrencies;

    public SupportedCurrencySpecification(final @NonNull Set<String> supportedCurrencies) {
        this.supportedCurrencies = supportedCurrencies;
    }

    @Override
    public Result<Employee> isSatisfiedBy(final @NonNull Employee obj) {
        if (!supportedCurrencies.contains(obj.getCurrencyCode())) {
            return new Result<>(obj, true, String.format("Currency %s is not supported! supported currencies: %s",
                    obj.getCurrencyCode(), supportedCurrencies));
        }
        return new Result<>(obj, false, "");
    }
}
