package com.cercli.employees.dbms.infrastructure.configuration.beans.adapters;

import com.cercli.employees.dbms.infrastructure.configuration.beans.server.ServerConfig;
import com.cercli.employees.dbms.infrastructure.postgres.adapter.PostgresEmployeesAdapter;
import com.cercli.employees.dbms.infrastructure.postgres.mapper.PostgresEmployeeMapper;
import com.cercli.employees.dbms.infrastructure.postgres.repository.PostgresEmployeesRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.lang.NonNull;

@Configuration
@Import({ServerConfig.class})
public class AdaptersConfig {

    private final PostgresEmployeesRepository employeesRepository;
    private final ServerConfig serverConfig;

    public AdaptersConfig(final @NonNull PostgresEmployeesRepository employeesRepository,
                          final @NonNull ServerConfig serverConfig) {
        this.employeesRepository = employeesRepository;
        this.serverConfig = serverConfig;
    }

    @Bean
    public PostgresEmployeeMapper postgresEmployeeMapper() {
        return new PostgresEmployeeMapper(serverConfig.getTimeZone());
    }

    @Bean
    public PostgresEmployeesAdapter postgresEmployeesAdapter() {
        return new PostgresEmployeesAdapter(postgresEmployeeMapper(), employeesRepository);
    }
}
