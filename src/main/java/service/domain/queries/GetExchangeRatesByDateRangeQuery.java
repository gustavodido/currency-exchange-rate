package service.domain.queries;

import service.domain.models.ExchangeRate;
import service.domain.repositories.ExchangeRateRepository;
import service.infrastructure.annotations.Query;

import java.time.Instant;
import java.util.List;

@Query
public class GetExchangeRatesByDateRangeQuery {
    private final ExchangeRateRepository exchangeRateRepository;

    public GetExchangeRatesByDateRangeQuery(ExchangeRateRepository exchangeRateRepository) {
        this.exchangeRateRepository = exchangeRateRepository;
    }

    public List<ExchangeRate> run(Instant from, Instant to) {
        return exchangeRateRepository.findByTimestampBetween(from, to);
    }
}
