package com.cercli.employees.dbms.infrastructure.configuration.beans.specifications;

import com.cercli.employees.dbms.domain.entity.Employee;
import com.cercli.employees.dbms.domain.specification.CompositeSpecification;
import com.cercli.employees.dbms.domain.specification.Specification;
import com.cercli.employees.dbms.domain.specification.employee.SupportedCurrencySpecification;
import com.cercli.employees.dbms.domain.specification.employee.EmailFormatSpecification;
import com.cercli.employees.dbms.domain.specification.employee.FullNameLengthSpecification;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.lang.NonNull;

import java.util.ArrayList;
import java.util.List;

@Configuration
@Import({SupportedCurrenciesConfig.class})
public class SpecificationsConfig {

    @Value("${specs.employee.max-full-name-length}")
    private int maxFullNameLength;

    private final SupportedCurrenciesConfig supportedCurrenciesConfig;

    public SpecificationsConfig(final @NonNull SupportedCurrenciesConfig supportedCurrenciesConfig) {
        this.supportedCurrenciesConfig = supportedCurrenciesConfig;
    }

    @Bean
    public Specification<Employee> emailFormatSpecification() {
        return new EmailFormatSpecification();
    }

    @Bean
    public Specification<Employee> fullNameLengthSpecification() {
        return new FullNameLengthSpecification(maxFullNameLength);
    }

    @Bean
    public CompositeSpecification<Employee> createNewEmployeeSpecificaions() {
        final List<Specification<Employee>> specifications = new ArrayList<>();
        specifications.add(emailFormatSpecification());
        specifications.add(fullNameLengthSpecification());
        specifications.add(supportedCurrencySpecification());
        return new CompositeSpecification<>(specifications);
    }

    @Bean
    public CompositeSpecification<Employee> updateEmployeeSpecificaions() {
        final List<Specification<Employee>> specifications = new ArrayList<>();
        specifications.add(emailFormatSpecification());
        specifications.add(fullNameLengthSpecification());
        specifications.add(supportedCurrencySpecification());
        return new CompositeSpecification<>(specifications);
    }

    @Bean
    public Specification<Employee> supportedCurrencySpecification() {
        return new SupportedCurrencySpecification(supportedCurrenciesConfig.getSupportedCurrencies());
    }
}
