package service.domain.repositories;

import org.springframework.data.repository.CrudRepository;
import service.domain.models.ExchangeRate;

import java.time.Instant;
import java.util.List;

public interface ExchangeRateRepository extends CrudRepository<ExchangeRate, Instant> {
    ExchangeRate findFirstByOrderByTimestampDesc();
    List<ExchangeRate> findByTimestampBetween(Instant from, Instant to);
}
