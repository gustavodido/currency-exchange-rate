package service.domain.queries;

import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import service.domain.exceptions.CurrencyConverterApiUnavailableException;
import service.domain.models.ConverterApiQueryDto;
import service.infrastructure.annotations.Query;
import service.infrastructure.configuration.ApplicationConfiguration;

@Query
public class GetRateFromCurrencyConverterApiQuery {
    private final RestTemplate restTemplate;
    private final ApplicationConfiguration applicationConfiguration;

    public GetRateFromCurrencyConverterApiQuery(RestTemplate restTemplate, ApplicationConfiguration applicationConfiguration) {
        this.restTemplate = restTemplate;
        this.applicationConfiguration = applicationConfiguration;
    }

    public ConverterApiQueryDto run() {
        String uri = applicationConfiguration.getUrls().getCurrencyConverterApi();

        try {
            return restTemplate.getForEntity(uri, ConverterApiQueryDto.class)
                    .getBody();
        } catch (RestClientException e) {
            throw new CurrencyConverterApiUnavailableException(uri, e);
        }
    }
}
