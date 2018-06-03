package service.domain.queries;

import org.springframework.web.client.RestTemplate;
import service.domain.models.ConverterApiQueryDto;
import service.domain.models.ExchangeRate;
import service.infrastructure.annotations.Query;
import service.infrastructure.configuration.properties.ApplicationConfiguration;
import service.infrastructure.providers.TimeProvider;

@Query
public class GetRateFromCurrencyConverterApiQuery {
    private final RestTemplate restTemplate;
    private final ApplicationConfiguration applicationConfiguration;
    private final TimeProvider timeProvider;

    public GetRateFromCurrencyConverterApiQuery(RestTemplate restTemplate, ApplicationConfiguration applicationConfiguration, TimeProvider timeProvider) {
        this.restTemplate = restTemplate;
        this.applicationConfiguration = applicationConfiguration;
        this.timeProvider = timeProvider;
    }

    public ExchangeRate run() {
        String uri = applicationConfiguration.getUrls().getCurrencyConverterApi();

        ConverterApiQueryDto dto = restTemplate.getForEntity(uri, ConverterApiQueryDto.class)
                .getBody();

        return ExchangeRate.builder()
                .timestamp(timeProvider.now())
                .value(dto.getValue())
                .build();
    }
}
