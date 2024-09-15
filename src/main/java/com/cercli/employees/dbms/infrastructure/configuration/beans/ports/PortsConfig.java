package com.cercli.employees.dbms.infrastructure.configuration.beans.ports;

import com.cercli.employees.dbms.application.port.input.EmployeeInputPort;
import com.cercli.employees.dbms.infrastructure.configuration.beans.adapters.AdaptersConfig;
import com.cercli.employees.dbms.infrastructure.configuration.beans.server.ServerConfig;
import com.cercli.employees.dbms.infrastructure.configuration.beans.specifications.SpecificationsConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.lang.NonNull;

@Configuration
@Import({SpecificationsConfig.class, AdaptersConfig.class, ServerConfig.class})
public class PortsConfig {

    private final SpecificationsConfig specificationsConfig;
    private final AdaptersConfig adaptersConfig;
    private final ServerConfig serverConfig;

    public PortsConfig(final @NonNull SpecificationsConfig specificationsConfig,
                       final @NonNull AdaptersConfig adaptersConfig,
                       final @NonNull ServerConfig serverConfig) {
        this.specificationsConfig = specificationsConfig;
        this.adaptersConfig = adaptersConfig;
        this.serverConfig = serverConfig;
    }

    @Bean
    public EmployeeInputPort employeeInputPort() {
        return new EmployeeInputPort(adaptersConfig.postgresEmployeesAdapter(),
                specificationsConfig.createNewEmployeeSpecificaions(),
                specificationsConfig.updateEmployeeSpecificaions(), serverConfig.getTimeZone(), serverConfig.isLogData());
    }
}
