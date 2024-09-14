package com.cercli.employees.dbms.infrastructure.configuration.beans.adapters;

import com.cercli.employees.dbms.infrastructure.postgres.adapter.PostgresEmployeesAdapter;
import com.cercli.employees.dbms.infrastructure.postgres.mapper.PostgresEmployeeMapper;
import com.cercli.employees.dbms.infrastructure.postgres.repository.PostgresEmployeesRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;

@Configuration
public class AdaptersConfig {

    private final PostgresEmployeesRepository employeesRepository;

    public AdaptersConfig(final @NonNull PostgresEmployeesRepository employeesRepository) {
        this.employeesRepository = employeesRepository;
    }

    @Bean
    public PostgresEmployeeMapper postgresEmployeeMapper() {
        return new PostgresEmployeeMapper();
    }

    @Bean
    public PostgresEmployeesAdapter postgresEmployeesAdapter() {
        return new PostgresEmployeesAdapter(postgresEmployeeMapper(), employeesRepository);
    }
}
