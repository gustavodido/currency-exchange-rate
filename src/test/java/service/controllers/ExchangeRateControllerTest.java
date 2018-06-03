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
import service.domain.queries.GetExchangeRateByTimestampQuery;
import service.domain.queries.GetExchangeRatesByDateRangeQuery;
import service.domain.queries.GetLatestExchangeRateQuery;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.web.context.request.RequestContextHolder.setRequestAttributes;
import static service.support.Stubs.oldRate;
import static service.support.Stubs.latestRate;
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
        when(getExchangeRateByIdQuery.run(oldRate().getTimestamp())).thenReturn(oldRate());

        ExchangeRate actualRate = exchangeRateController.get(oldRate().getTimestamp())
                .getExchangeRate();

        assertThat(actualRate).isEqualTo(oldRate());
    }

    @Test
    public void shouldReturnLatestExchangeRate() {
        when(getLatestExchangeRateQuery.run()).thenReturn(oldRate());

        ExchangeRate actualRate = exchangeRateController.latest()
                .getExchangeRate();

        assertThat(actualRate).isEqualTo(oldRate());
    }

    @Test
    public void shouldReturnExchangeRateByDateRange() {
        when(getExchangeRatesByDateRangeQuery.run(oldRate().getTimestamp(), latestRate().getTimestamp()))
                .thenReturn(rates());

        List<ExchangeRate> actualRates =
                exchangeRateController.history(oldRate().getTimestamp(), latestRate().getTimestamp())
                        .getContent()
                        .stream()
                        .map(ExchangeRateResource::getExchangeRate)
                        .collect(toList());

        assertThat(actualRates).isEqualTo(rates());

    }
}