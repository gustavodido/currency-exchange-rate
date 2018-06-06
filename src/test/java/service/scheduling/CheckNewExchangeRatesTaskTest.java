package service.scheduling;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.context.ApplicationEventPublisher;
import service.domain.queries.GetRateFromCurrencyConverterApiQuery;
import service.domain.queries.GetRateFromFixerApiQuery;
import service.infrastructure.providers.TimeProvider;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static service.support.Stubs.latestFixerApiDto;
import static service.support.Stubs.latestRate;
import static service.support.Stubs.latestConverterApiDto;
import static service.support.Stubs.latestRateNewEvent;

@RunWith(MockitoJUnitRunner.class)
public class CheckNewExchangeRatesTaskTest {
    @Mock
    private GetRateFromCurrencyConverterApiQuery getRateFromCurrencyConverterApiQuery;

    @Mock
    private GetRateFromFixerApiQuery getRateFromFixerApiQuery;

    @Mock
    private TimeProvider timeProvider;

    @Mock
    private ApplicationEventPublisher publisher;

    @InjectMocks
    private CheckNewExchangeRatesTask checkNewExchangeRatesTask;

    @Before
    public void setUp() {
        when(timeProvider.now()).thenReturn(latestRate().getTimestamp());
    }

    @Test
    public void shouldPublishNewEventWheRunning() {
        when(getRateFromCurrencyConverterApiQuery.run()).thenReturn(latestConverterApiDto());

        checkNewExchangeRatesTask.run();

        verify(publisher).publishEvent(latestRateNewEvent());
    }

    @Test
    public void shouldPublishNewEventWhenFallingBack() {
        when(getRateFromFixerApiQuery.run()).thenReturn(latestFixerApiDto());

        checkNewExchangeRatesTask.fallback();

        verify(publisher).publishEvent(latestRateNewEvent());
    }
}