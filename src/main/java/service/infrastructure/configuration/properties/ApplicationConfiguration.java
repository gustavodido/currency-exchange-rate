package service.infrastructure.configuration.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@EnableConfigurationProperties
@ConfigurationProperties(prefix = "application")
@Configuration
@Data
public class ApplicationConfiguration {
    private final UrlsConfiguration urls = new UrlsConfiguration();
}
