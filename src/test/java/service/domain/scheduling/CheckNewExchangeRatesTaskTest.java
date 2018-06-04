package service.domain.scheduling;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.context.ApplicationEventPublisher;
import service.domain.queries.GetRateFromCurrencyConverterApiQuery;
import service.infrastructure.providers.TimeProvider;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static service.support.Stubs.latestRate;
import static service.support.Stubs.latestRateDto;
import static service.support.Stubs.latestRateNewEvent;

@RunWith(MockitoJUnitRunner.class)
public class CheckNewExchangeRatesTaskTest {
    @Mock
    private GetRateFromCurrencyConverterApiQuery getRateFromCurrencyConverterApiQuery;

    @Mock
    private TimeProvider timeProvider;

    @Mock
    private ApplicationEventPublisher publisher;

    @InjectMocks
    private CheckNewExchangeRatesTask checkNewExchangeRatesTask;

    @Test
    public void shouldPublishNewEventWithExchangeRate() {
        when(getRateFromCurrencyConverterApiQuery.run()).thenReturn(latestRateDto());
        when(timeProvider.now()).thenReturn(latestRate().getTimestamp());

        checkNewExchangeRatesTask.run();

        verify(publisher).publishEvent(latestRateNewEvent());
    }

}