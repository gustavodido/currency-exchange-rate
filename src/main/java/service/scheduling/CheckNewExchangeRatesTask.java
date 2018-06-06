package service.scheduling;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import service.domain.models.ExchangeRate;
import service.domain.models.NewExchangeRateEvent;
import service.domain.models.dtos.ConverterApiQueryDto;
import service.domain.models.dtos.FixerApiQueryDto;
import service.domain.queries.GetRateFromCurrencyConverterApiQuery;
import service.domain.queries.GetRateFromFixerApiQuery;
import service.infrastructure.providers.TimeProvider;

@Component
public class CheckNewExchangeRatesTask {
    private final GetRateFromCurrencyConverterApiQuery getRateFromCurrencyConverterApiQuery;
    private final GetRateFromFixerApiQuery getRateFromFixerApiQuery;
    private final TimeProvider timeProvider;
    private final ApplicationEventPublisher publisher;

    public CheckNewExchangeRatesTask(GetRateFromCurrencyConverterApiQuery getRateFromCurrencyConverterApiQuery, GetRateFromFixerApiQuery getRateFromFixerApiQuery, TimeProvider timeProvider, ApplicationEventPublisher publisher) {
        this.getRateFromCurrencyConverterApiQuery = getRateFromCurrencyConverterApiQuery;
        this.getRateFromFixerApiQuery = getRateFromFixerApiQuery;
        this.timeProvider = timeProvider;
        this.publisher = publisher;
    }

    @HystrixCommand(fallbackMethod = "fallback")
    public void run() {
        ConverterApiQueryDto rateDto = getRateFromCurrencyConverterApiQuery.run();

        sendEvent(rateDto.getValue());
    }

    void fallback() {
        FixerApiQueryDto rateDto = getRateFromFixerApiQuery.run();

        sendEvent(rateDto.getValue());
    }

    private void sendEvent(Double value) {
        ExchangeRate exchangeRate = ExchangeRate.builder()
                .timestamp(timeProvider.now())
                .value(value)
                .build();

        NewExchangeRateEvent newExchangeRateEvent = NewExchangeRateEvent.builder()
                .exchangeRate(exchangeRate)
                .build();

        publisher.publishEvent(newExchangeRateEvent);
    }
}
