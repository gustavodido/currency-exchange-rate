package service.queries;

import service.models.ExchangeRate;

public class GetLatestExchangeRateQuery {
    public ExchangeRate run() {
        return ExchangeRate.builder().build();
    }
}
