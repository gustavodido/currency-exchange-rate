package service.controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.ServletRequestAttributes;
import service.domain.models.ExchangeRate;
import service.domain.models.ExchangeRateResource;
import service.domain.queries.GetExchangeRateByTimestampQuery;
import service.domain.queries.GetExchangeRatesByDateRangeQuery;
import service.domain.queries.GetLatestExchangeRateQuery;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.web.context.request.RequestContextHolder.setRequestAttributes;
import static service.support.Stubs.currentRate;
import static service.support.Stubs.previousRate;
import static service.support.Stubs.rates;

@RunWith(MockitoJUnitRunner.class)
public class ExchangeRateControllerTest {
    @Mock
    private GetExchangeRateByTimestampQuery getExchangeRateByIdQuery;

    @Mock
    private GetLatestExchangeRateQuery getLatestExchangeRateQuery;

    @Mock
    private GetExchangeRatesByDateRangeQuery getExchangeRatesByDateRangeQuery;

    @InjectMocks
    private ExchangeRateController exchangeRateController;

    @Before
    public void setup() {
        setRequestAttributes(new ServletRequestAttributes(new MockHttpServletRequest()));
    }

    @Test
    public void shouldReturnExchangeRateById() {
        when(getExchangeRateByIdQuery.run(currentRate().getTimestamp())).thenReturn(currentRate());

        ExchangeRate actualRate = exchangeRateController.get(currentRate().getTimestamp())
                .getExchangeRate();

        assertThat(actualRate).isEqualTo(currentRate());
    }

    @Test
    public void shouldReturnLatestExchangeRate() {
        when(getLatestExchangeRateQuery.run()).thenReturn(currentRate());

        ExchangeRate actualRate = exchangeRateController.latest()
                .getExchangeRate();

        assertThat(actualRate).isEqualTo(currentRate());
    }

    @Test
    public void shouldReturnExchangeRateByDateRange() {
        when(getExchangeRatesByDateRangeQuery.run(currentRate().getTimestamp(), previousRate().getTimestamp()))
                .thenReturn(rates());

        List<ExchangeRate> actualRates =
                exchangeRateController.history(currentRate().getTimestamp(), previousRate().getTimestamp())
                        .getContent()
                        .stream()
                        .map(ExchangeRateResource::getExchangeRate)
                        .collect(toList());

        assertThat(actualRates).isEqualTo(rates());

    }
}