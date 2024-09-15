package com.cercli.employees.dbms.infrastructure.configuration.beans.specifications;

import com.cercli.employees.dbms.application.specification.employee.UniqueEmailSpecification;
import com.cercli.employees.dbms.application.specification.leaveRequest.LeaveRequestTimeSpecification;
import com.cercli.employees.dbms.application.specification.leaveRequest.LeaveRequestTypeSpecification;
import com.cercli.employees.dbms.application.specification.leaveRequest.OverlappingRequestsSpecification;
import com.cercli.employees.dbms.domain.entity.Employee;
import com.cercli.employees.dbms.application.specification.CompositeSpecification;
import com.cercli.employees.dbms.application.specification.Specification;
import com.cercli.employees.dbms.application.specification.employee.SupportedCurrencySpecification;
import com.cercli.employees.dbms.application.specification.employee.EmailFormatSpecification;
import com.cercli.employees.dbms.application.specification.employee.FullNameLengthSpecification;
import com.cercli.employees.dbms.domain.entity.LeaveRequest;
import com.cercli.employees.dbms.infrastructure.configuration.beans.adapters.AdaptersConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.lang.NonNull;

import java.util.ArrayList;
import java.util.List;

@Configuration
@Import({SupportedCurrenciesConfig.class, AdaptersConfig.class, LeaveRequestConfig.class})
public class SpecificationsConfig {

    @Value("${specs.employee.max-full-name-length}")
    private int maxFullNameLength;

    private final SupportedCurrenciesConfig supportedCurrenciesConfig;
    private final AdaptersConfig adaptersConfig;
    private final LeaveRequestConfig leaveRequestConfig;

    public SpecificationsConfig(final @NonNull SupportedCurrenciesConfig supportedCurrenciesConfig,
                                final @NonNull AdaptersConfig adaptersConfig,
                                final @NonNull LeaveRequestConfig leaveRequestConfig) {
        this.supportedCurrenciesConfig = supportedCurrenciesConfig;
        this.adaptersConfig = adaptersConfig;
        this.leaveRequestConfig = leaveRequestConfig;
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
    public Specification<Employee> uniqueEmailSpecification() {
        return new UniqueEmailSpecification(adaptersConfig.postgresEmployeesAdapter());
    }

    @Bean
    public CompositeSpecification<Employee> createNewEmployeeSpecifications() {
        final List<Specification<Employee>> specifications = new ArrayList<>();
        specifications.add(emailFormatSpecification());
        specifications.add(uniqueEmailSpecification());
        specifications.add(fullNameLengthSpecification());
        specifications.add(supportedCurrencySpecification());
        return new CompositeSpecification<>(specifications);
    }

    @Bean
    public CompositeSpecification<Employee> updateEmployeeSpecifications() {
        final List<Specification<Employee>> specifications = new ArrayList<>();
        specifications.add(emailFormatSpecification());
        specifications.add(uniqueEmailSpecification());
        specifications.add(fullNameLengthSpecification());
        specifications.add(supportedCurrencySpecification());
        return new CompositeSpecification<>(specifications);
    }

    @Bean
    public Specification<Employee> supportedCurrencySpecification() {
        return new SupportedCurrencySpecification(supportedCurrenciesConfig.getSupportedCurrencies());
    }

    @Bean
    public LeaveRequestTimeSpecification leaveRequestTimeSpecification() {
        return new LeaveRequestTimeSpecification();
    }

    @Bean
    public LeaveRequestTypeSpecification leaveRequestTypeSpecification() {
        return new LeaveRequestTypeSpecification(leaveRequestConfig.getSupportedTypes());
    }

    @Bean
    public OverlappingRequestsSpecification overlappingRequestsSpecification() {
        return new OverlappingRequestsSpecification(leaveRequestConfig.getOverlappingTypes());
    }

    @Bean
    public CompositeSpecification<LeaveRequest> createNewLeaveRequestSpecification() {
        final List<Specification<LeaveRequest>> specifications = new ArrayList<>();
        specifications.add(leaveRequestTimeSpecification());
        specifications.add(leaveRequestTypeSpecification());
        return new CompositeSpecification<>(specifications);
    }
}
