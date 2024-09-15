package com.cercli.employees.dbms.infrastructure.configuration.beans.server;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "server")
@Getter
@Setter
public class ServerConfig {

    private String timeZone;
    private boolean logData;
}
