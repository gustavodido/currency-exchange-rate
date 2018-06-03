package service.domain.queries;

import org.springframework.web.client.RestTemplate;
import service.domain.models.ConverterApiQueryDto;
import service.domain.models.ExchangeRate;
import service.infrastructure.annotations.Query;
import service.infrastructure.configuration.properties.ApplicationConfiguration;

import java.time.Instant;

@Query
public class GetRateFromCurrencyConverterApiQuery {
    private final RestTemplate restTemplate;
    private final ApplicationConfiguration applicationConfiguration;

    public GetRateFromCurrencyConverterApiQuery(RestTemplate restTemplate, ApplicationConfiguration applicationConfiguration) {
        this.restTemplate = restTemplate;
        this.applicationConfiguration = applicationConfiguration;
    }

    public ExchangeRate run() {
        String uri = applicationConfiguration.getUrls().getCurrencyConverterApi() + "?q=EUR_USD&compact=y";

        ConverterApiQueryDto dto = restTemplate.getForEntity(uri, ConverterApiQueryDto.class)
                .getBody();

        return ExchangeRate.builder()
                .timestamp(Instant.now())
                .value(dto.getValue())
                .build();
    }
}
