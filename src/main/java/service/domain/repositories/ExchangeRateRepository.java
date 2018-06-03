package service.domain.repositories;

import org.springframework.data.repository.CrudRepository;
import service.domain.models.ExchangeRate;
import service.domain.models.ExchangeRateResource;

import java.time.Instant;
import java.util.List;

public interface ExchangeRateRepository extends CrudRepository<ExchangeRateResource, Instant> {
    ExchangeRate findLastOrderByTimestamp();
    List<ExchangeRate> findByTimestampBetween(Instant from, Instant to);
}
