package service.integration;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import service.domain.queries.GetRateFromCurrencyConverterApiQuery;
import service.scheduling.CheckNewExchangeRatesTask;
import service.infrastructure.providers.TimeProvider;
import service.support.IntegrationTest;

import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static service.support.Stubs.newRate;
import static service.support.Stubs.newRateDto;

public class ScheduledTaskIntegrationTest extends IntegrationTest {

    @MockBean
    private TimeProvider timeProvider;

    @MockBean
    private GetRateFromCurrencyConverterApiQuery getRateFromCurrencyConverterApiQuery;

    @Autowired
    private CheckNewExchangeRatesTask checkNewExchangeRatesTask;

    @Before
    public void setUp() {
        // We don't want to hit the original service
        when(getRateFromCurrencyConverterApiQuery.run()).thenReturn(newRateDto());
        when(timeProvider.now()).thenReturn(newRate().getTimestamp());
    }

    @Test
    public void shouldCheckNewRateAndReturnItAsLatestRate() throws Exception {
        checkNewExchangeRatesTask.run();

        this.mockMvc.perform(get("/api/rates/latest"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.timestamp", is(newRate().getTimestamp().toString())))
                .andExpect(jsonPath("$.value", is(newRate().getValue())));
    }
}
