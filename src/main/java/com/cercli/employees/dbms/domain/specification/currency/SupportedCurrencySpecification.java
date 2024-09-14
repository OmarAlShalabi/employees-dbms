package com.cercli.employees.dbms.domain.specification.currency;

import com.cercli.employees.dbms.domain.entity.Currency;
import com.cercli.employees.dbms.domain.entity.Result;
import com.cercli.employees.dbms.domain.specification.Specification;
import org.springframework.lang.NonNull;

import java.util.Set;

public class SupportedCurrencySpecification implements Specification<Currency> {

    private final Set<String> supportedCurrencies;

    public SupportedCurrencySpecification(final @NonNull Set<String> supportedCurrencies) {
        this.supportedCurrencies = supportedCurrencies;
    }

    @Override
    public Result<Currency> isSatisfiedBy(final @NonNull Currency obj) {
        if (!supportedCurrencies.contains(obj.getCode())) {
            return new Result<>(obj, true, String.format("Currency %s is not supported! supported currencies: %s",
                    obj.getCode(), supportedCurrencies));
        }
        return new Result<>(obj, false, "");
    }
}
