package com.cercli.employees.dbms.infrastructure.configuration.beans.adapters;

import com.cercli.employees.dbms.infrastructure.configuration.beans.server.ServerConfig;
import com.cercli.employees.dbms.infrastructure.postgres.adapter.PostgresEmployeesAdapter;
import com.cercli.employees.dbms.infrastructure.postgres.adapter.PostgresTimeOffRequestAdapter;
import com.cercli.employees.dbms.infrastructure.postgres.mapper.PostgresEmployeeMapper;
import com.cercli.employees.dbms.infrastructure.postgres.mapper.PostgresTimeOffRequestMapper;
import com.cercli.employees.dbms.infrastructure.postgres.repository.PostgresEmployeesRepository;
import com.cercli.employees.dbms.infrastructure.postgres.repository.PostgresRequestCategoryRepository;
import com.cercli.employees.dbms.infrastructure.postgres.repository.PostgresTimeOffRequestRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.lang.NonNull;

@Configuration
@Import({ServerConfig.class})
public class AdaptersConfig {

    private final PostgresEmployeesRepository employeesRepository;
    private final ServerConfig serverConfig;
    private final PostgresTimeOffRequestRepository timeOffRequestRepository;
    private final PostgresRequestCategoryRepository requestCategoryRepository;

    public AdaptersConfig(final @NonNull PostgresEmployeesRepository employeesRepository,
                          final @NonNull ServerConfig serverConfig,
                          final @NonNull PostgresTimeOffRequestRepository timeOffRequestRepository,
                          final @NonNull PostgresRequestCategoryRepository requestCategoryRepository) {
        this.employeesRepository = employeesRepository;
        this.serverConfig = serverConfig;
        this.timeOffRequestRepository = timeOffRequestRepository;
        this.requestCategoryRepository = requestCategoryRepository;
    }

    @Bean
    public PostgresEmployeeMapper postgresEmployeeMapper() {
        return new PostgresEmployeeMapper(serverConfig.getTimeZone());
    }

    @Bean
    public PostgresEmployeesAdapter postgresEmployeesAdapter() {
        return new PostgresEmployeesAdapter(postgresEmployeeMapper(), employeesRepository);
    }

    @Bean
    public PostgresTimeOffRequestMapper postgresTimeOffRequestMapper() {
        return new PostgresTimeOffRequestMapper(serverConfig.getTimeZone(), requestCategoryRepository);
    }

    @Bean
    public PostgresTimeOffRequestAdapter postgresTimeOffRequestAdapter() {
        return new PostgresTimeOffRequestAdapter(postgresTimeOffRequestMapper(), timeOffRequestRepository);
    }
}
