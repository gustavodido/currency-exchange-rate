package service.domain.queries;

import service.domain.exceptions.ExchangeRateNotFoundException;
import service.domain.repositories.ExchangeRateRepository;
import service.infrastructure.annotations.Query;
import service.domain.models.ExchangeRate;

import java.time.Instant;

@Query
public class GetExchangeRateByTimestampQuery {
    private final ExchangeRateRepository exchangeRateRepository;

    public GetExchangeRateByTimestampQuery(ExchangeRateRepository exchangeRateRepository) {
        this.exchangeRateRepository = exchangeRateRepository;
    }

    public ExchangeRate run(Instant timestamp) {
        return exchangeRateRepository.findById(timestamp)
                .orElseThrow(() -> new ExchangeRateNotFoundException(timestamp));
    }
}
