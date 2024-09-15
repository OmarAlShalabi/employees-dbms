package com.cercli.employees.dbms.infrastructure.configuration.beans.specifications;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.Set;

@Configuration
@ConfigurationProperties(prefix = "specs.leave-request")
@Getter
@Setter
public class LeaveRequestConfig {

    private Set<String> supportedTypes;
    private Map<String, Set<String>> overlappingTypes;
}
