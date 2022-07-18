package com.healthpulse.backend.api.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "health")
@Setter
@Getter
public class AppProperties {
    private String session;
    private String baseUrl;
    private String clientId;
    private String clientSecret;
    private String consentManagerUrl;
}
