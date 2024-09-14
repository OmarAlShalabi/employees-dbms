package com.cercli.employees.dbms.infrastructure.configuration.beans.ports;

import com.cercli.employees.dbms.application.port.input.EmployeeInputPort;
import com.cercli.employees.dbms.application.port.output.EmployeeOutputPort;
import com.cercli.employees.dbms.infrastructure.configuration.beans.specifications.SpecificationsConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.lang.NonNull;

@Configuration
@Import({SpecificationsConfig.class})
public class PortsConfig {

    private final SpecificationsConfig specificationsConfig;

    @Value("${server.time-zone}")
    private String serverTimeZone;

    @Value("${server.is-debug}")
    private boolean isDebug;

    public PortsConfig(final @NonNull SpecificationsConfig specificationsConfig) {
        this.specificationsConfig = specificationsConfig;
    }

    @Bean
    public EmployeeOutputPort employeeOutputPort() {
        return new EmployeeOutputPort();
    }

    @Bean
    public EmployeeInputPort employeeInputPort() {
        return new EmployeeInputPort(employeeOutputPort(), specificationsConfig.createNewEmployeeSpecificaions(),
                specificationsConfig.updateEmployeeSpecificaions(), serverTimeZone, isDebug);
    }
}
