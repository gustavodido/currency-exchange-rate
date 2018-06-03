package service.support;

import service.domain.models.ExchangeRate;

import java.time.LocalDateTime;
import java.util.List;

import static java.time.ZoneOffset.UTC;
import static java.util.Arrays.asList;

public class Stubs {
    public static ExchangeRate currentRate() {
        return ExchangeRate.builder()
                .value(1.0d)
                .timestamp(LocalDateTime.of(2018, 6, 2, 1, 0).toInstant(UTC))
                .build();
    }

    public static ExchangeRate previousRate() {
        return ExchangeRate.builder()
                .value(2.0d)
                .timestamp(LocalDateTime.of(2018, 6, 1, 1, 0).toInstant(UTC))
                .build();
    }

    public static List<ExchangeRate> rates() {
        return asList(currentRate(), previousRate());
    }
}
