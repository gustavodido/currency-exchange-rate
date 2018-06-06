package service.integration;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import service.domain.exceptions.CurrencyConverterApiUnavailableException;
import service.domain.queries.GetRateFromCurrencyConverterApiQuery;
import service.domain.queries.GetRateFromFixerApiQuery;
import service.infrastructure.providers.TimeProvider;
import service.scheduling.CheckNewExchangeRatesTask;
import service.support.IntegrationTest;

import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static service.support.Stubs.newFixerApiDto;
import static service.support.Stubs.newRate;
import static service.support.Stubs.newConverterApiDto;

public class ScheduledTaskIntegrationTest extends IntegrationTest {

    @MockBean
    private TimeProvider timeProvider;

    @MockBean
    private GetRateFromCurrencyConverterApiQuery getRateFromCurrencyConverterApiQuery;

    @MockBean
    private GetRateFromFixerApiQuery getRateFromFixerApiQuery;

    @Autowired
    private CheckNewExchangeRatesTask checkNewExchangeRatesTask;

    @Before
    public void setUp() {
        when(timeProvider.now()).thenReturn(newRate().getTimestamp());
    }

    @Test
    public void shouldCheckNewRateWithCurrencyConverter() throws Exception {
        when(getRateFromCurrencyConverterApiQuery.run()).thenReturn(newConverterApiDto());

        checkNewExchangeRatesTask.run();

        checkLatestRate();
    }

    @Test
    public void shouldFallbackToFixerIfCurrencyConverterIsUnavailable() throws Exception {
        when(getRateFromCurrencyConverterApiQuery.run())
                .thenThrow(CurrencyConverterApiUnavailableException.class);

        when(getRateFromFixerApiQuery.run()).thenReturn(newFixerApiDto());

        checkNewExchangeRatesTask.run();

        checkLatestRate();
    }

    private void checkLatestRate() throws Exception {
        this.mockMvc.perform(get("/api/rates/latest"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.timestamp", is(newRate().getTimestamp().toString())))
                .andExpect(jsonPath("$.value", is(newRate().getValue())));
    }
}
