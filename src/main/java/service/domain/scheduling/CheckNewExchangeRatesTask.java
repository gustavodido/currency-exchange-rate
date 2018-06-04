package service.domain.scheduling;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import service.domain.models.ConverterApiQueryDto;
import service.domain.models.ExchangeRate;
import service.domain.models.NewExchangeRateEvent;
import service.domain.queries.GetRateFromCurrencyConverterApiQuery;
import service.infrastructure.providers.TimeProvider;

@Component
@ConditionalOnProperty(name = "application.scheduling.enabled", havingValue = "true")
public class CheckNewExchangeRatesTask {
    private final GetRateFromCurrencyConverterApiQuery getRateFromCurrencyConverterApiQuery;
    private final TimeProvider timeProvider;
    private final ApplicationEventPublisher publisher;

    public CheckNewExchangeRatesTask(GetRateFromCurrencyConverterApiQuery getRateFromCurrencyConverterApiQuery, TimeProvider timeProvider, ApplicationEventPublisher publisher) {
        this.getRateFromCurrencyConverterApiQuery = getRateFromCurrencyConverterApiQuery;
        this.timeProvider = timeProvider;
        this.publisher = publisher;
    }

    @Scheduled(fixedRateString = "${application.scheduling.interval}")
    public NewExchangeRateEvent run() {
        ConverterApiQueryDto rateDto = getRateFromCurrencyConverterApiQuery.run();

        ExchangeRate exchangeRate = ExchangeRate.builder()
                .timestamp(timeProvider.now())
                .value(rateDto.getValue())
                .build();

        NewExchangeRateEvent newExchangeRateEvent = NewExchangeRateEvent.builder()
                .exchangeRate(exchangeRate)
                .build();

        publisher.publishEvent(newExchangeRateEvent);

        return newExchangeRateEvent;
    }
}
