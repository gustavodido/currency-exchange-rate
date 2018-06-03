package service.domain.queries;

import service.domain.models.ExchangeRate;
import service.infrastructure.annotations.Query;

import java.time.Instant;
import java.util.Collections;
import java.util.List;

@Query
public class GetExchangeRatesByDateRangeQuery {
    public List<ExchangeRate> run(Instant from, Instant to) {
        return Collections.singletonList(ExchangeRate.builder().build());
    }
}
