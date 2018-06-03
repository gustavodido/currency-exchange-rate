package service.api;

import org.junit.Test;
import service.support.ApiTest;

import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static service.support.Stubs.latestRate;
import static service.support.Stubs.oldRate;

public class ExchangeRateApiTest extends ApiTest {
    @Test
    public void shouldGetExchangeRateByTimestamp() throws Exception {
        this.mockMvc.perform(get("/api/rates/" + oldRate().getTimestamp()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.timestamp", is(oldRate().getTimestamp().toString())))
                .andExpect(jsonPath("$.value", is(oldRate().getValue())));
    }

    @Test
    public void shouldGetLatestExchangeRate() throws Exception {
        this.mockMvc.perform(get("/api/rates/latest"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.timestamp", is(latestRate().getTimestamp().toString())))
                .andExpect(jsonPath("$.value", is(latestRate().getValue())));
    }

    @Test
    public void shouldGetExchangeRatesByTimestampRange() throws Exception {
        this.mockMvc.perform(get("/api/rates/history")
                .param("from", oldRate().getTimestamp().toString())
                .param("to", latestRate().getTimestamp().toString()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded.exchangeRateResourceList[*].timestamp", hasItems(oldRate().getTimestamp().toString(), latestRate().getTimestamp().toString())))
                .andExpect(jsonPath("$._embedded.exchangeRateResourceList[*].value", hasItems(oldRate().getValue(), latestRate().getValue())));
    }
}
