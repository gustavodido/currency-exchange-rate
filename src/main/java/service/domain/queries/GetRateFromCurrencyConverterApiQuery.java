package service.domain.queries;

import org.springframework.web.client.RestTemplate;
import service.domain.models.ExchangeRate;
import service.infrastructure.annotations.Query;

@Query
public class GetRateFromCurrencyConverterApiQuery {
    private final RestTemplate restTemplate;

    public GetRateFromCurrencyConverterApiQuery(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ExchangeRate run() {
        return restTemplate.getForEntity("", ExchangeRate.class)
                .getBody();
    }
}
