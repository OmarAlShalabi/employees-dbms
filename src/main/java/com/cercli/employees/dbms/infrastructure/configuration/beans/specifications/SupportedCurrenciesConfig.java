package com.cercli.employees.dbms.infrastructure.configuration.beans.specifications;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

@Configuration
@ConfigurationProperties(prefix = "specs.currency")
@Getter
@Setter
public class SupportedCurrenciesConfig {
    private Set<String> supportedCurrencies;
}
