package service.support;

import service.domain.models.ConverterApiQueryDto;
import service.domain.models.ExchangeRate;

import java.time.LocalDateTime;
import java.util.List;

import static java.time.ZoneOffset.UTC;
import static java.util.Arrays.asList;

public class Stubs {
    public static ExchangeRate oldRate() {
        return ExchangeRate.builder()
                .value(1.12345)
                .timestamp(LocalDateTime.of(2018, 6, 1, 1, 1, 1).toInstant(UTC))
                .build();
    }

    public static ExchangeRate latestRate() {
        return ExchangeRate.builder()
                .value(2.65431)
                .timestamp(LocalDateTime.of(2018, 6, 2, 2, 2, 2).toInstant(UTC))
                .build();
    }

    public static List<ExchangeRate> rates() {
        return asList(oldRate(), latestRate());
    }

    public static ConverterApiQueryDto latestRateDto() {
        return ConverterApiQueryDto.builder()
                .value(latestRate().getValue())
                .build();
    }
}
