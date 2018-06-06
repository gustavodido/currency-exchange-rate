package service.domain.queries;

import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import service.domain.exceptions.CurrencyConverterApiUnavailableException;
import service.domain.models.dtos.FixerApiQueryDto;
import service.infrastructure.annotations.Query;
import service.infrastructure.configuration.ApplicationConfiguration;

@Query
public class GetRateFromFixerApiQuery {
    private final RestTemplate restTemplate;
    private final ApplicationConfiguration applicationConfiguration;

    public GetRateFromFixerApiQuery(RestTemplate restTemplate, ApplicationConfiguration applicationConfiguration) {
        this.restTemplate = restTemplate;
        this.applicationConfiguration = applicationConfiguration;
    }

    public FixerApiQueryDto run() {
        String uri = applicationConfiguration.getProviders().getFixerApi();

        try {
            return restTemplate.getForEntity(uri, FixerApiQueryDto.class).getBody();
        } catch (RestClientException e) {
            throw new CurrencyConverterApiUnavailableException(uri, e);
        }
    }
}
