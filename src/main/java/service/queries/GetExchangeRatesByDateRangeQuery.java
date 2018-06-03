package service.queries;

import service.models.ExchangeRate;

import java.time.Instant;
import java.util.Collections;
import java.util.List;

public class GetExchangeRatesByDateRangeQuery {
    public List<ExchangeRate> run(Instant from, Instant to) {
        return Collections.singletonList(ExchangeRate.builder().build());
    }
}
