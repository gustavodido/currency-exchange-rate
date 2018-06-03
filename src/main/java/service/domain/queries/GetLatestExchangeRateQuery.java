package service.domain.queries;

import service.domain.models.ExchangeRate;
import service.infrastructure.annotations.Query;

@Query
public class GetLatestExchangeRateQuery {
    public ExchangeRate run() {
        return ExchangeRate.builder().build();
    }
}
