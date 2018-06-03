package service.queries;

import service.models.ExchangeRate;

import java.time.Instant;

public class GetExchangeRateByIdQuery {
    public ExchangeRate run(Instant id) {
        return ExchangeRate.builder().build();
    }
}
