package service.support;

import service.domain.models.dtos.ConverterApiQueryDto;
import service.domain.models.ExchangeRate;
import service.domain.models.NewExchangeRateEvent;
import service.domain.models.dtos.FixerApiQueryDto;

import java.util.List;

import static java.time.LocalDateTime.of;
import static java.time.ZoneOffset.UTC;
import static java.util.Arrays.asList;

public class Stubs {
    public static ExchangeRate oldRate() {
        return ExchangeRate.builder()
                .value(1.12345)
                .timestamp(of(2018, 6, 1, 1, 1, 1).toInstant(UTC))
                .build();
    }

    public static ExchangeRate latestRate() {
        return ExchangeRate.builder()
                .value(2.65431)
                .timestamp(of(2018, 6, 2, 2, 2, 2).toInstant(UTC))
                .build();
    }

    public static List<ExchangeRate> rates() {
        return asList(oldRate(), latestRate());
    }

    public static ConverterApiQueryDto latestConverterApiDto() {
        return ConverterApiQueryDto.builder()
                .value(latestRate().getValue())
                .build();
    }

    public static FixerApiQueryDto latestFixerApiDto() {
        return FixerApiQueryDto.builder()
                .value(latestRate().getValue())
                .build();
    }

    public static NewExchangeRateEvent latestRateNewEvent() {
        return NewExchangeRateEvent.builder()
                .exchangeRate(latestRate())
                .build();
    }

    public static ExchangeRate newRate() {
        return ExchangeRate.builder()
                .value(3.789)
                .timestamp(of(2018, 6, 3, 3, 3, 3).toInstant(UTC))
                .build();
    }

    public static ConverterApiQueryDto newConverterApiDto() {
        return ConverterApiQueryDto.builder()
                .value(newRate().getValue())
                .build();
    }

    public static FixerApiQueryDto newFixerApiDto() {
        return FixerApiQueryDto.builder()
                .value(newRate().getValue())
                .build();
    }
}
