package service.domain.queries;

import service.domain.exceptions.ExchangeRateNotFoundException;
import service.domain.models.ExchangeRate;
import service.domain.repositories.ExchangeRateRepository;
import service.infrastructure.annotations.Query;

@Query
public class GetLatestExchangeRateQuery {
    private final ExchangeRateRepository exchangeRateRepository;

    public GetLatestExchangeRateQuery(ExchangeRateRepository exchangeRateRepository) {
        this.exchangeRateRepository = exchangeRateRepository;
    }

    public ExchangeRate run() {
        return exchangeRateRepository.findFirstByOrderByTimestampDesc()
                .orElseThrow(ExchangeRateNotFoundException::new);
    }
}
