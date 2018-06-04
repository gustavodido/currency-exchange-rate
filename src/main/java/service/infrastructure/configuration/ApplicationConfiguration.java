package service.infrastructure.configuration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.context.annotation.Configuration;

@EnableConfigurationProperties
@ConfigurationProperties(prefix = "application")
@Configuration
@Data
public class ApplicationConfiguration {
    @NestedConfigurationProperty
    private final UrlsConfiguration urls = new UrlsConfiguration();
    @NestedConfigurationProperty
    private final SchedulingConfiguration scheduling = new SchedulingConfiguration();

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UrlsConfiguration {
        private String currencyConverterApi = "";
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    private static class SchedulingConfiguration {
        private long interval = 0L;
        private boolean enabled = false;
    }

}
