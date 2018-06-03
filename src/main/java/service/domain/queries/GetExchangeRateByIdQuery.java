package service.domain.queries;

import service.infrastructure.annotations.Query;
import service.domain.models.ExchangeRate;

import java.time.Instant;

@Query
public class GetExchangeRateByIdQuery {
    public ExchangeRate run(Instant id) {
        return ExchangeRate.builder().build();
    }
}
