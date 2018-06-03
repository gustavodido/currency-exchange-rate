package service.domain.queries;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import service.domain.models.ExchangeRate;
import service.domain.repositories.ExchangeRateRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static service.support.Stubs.oldRate;
import static service.support.Stubs.latestRate;
import static service.support.Stubs.rates;

@RunWith(MockitoJUnitRunner.class)
public class GetExchangeRatesByDateRangeQueryTest {
    @Mock
    private ExchangeRateRepository exchangeRateRepository;

    @InjectMocks
    private GetExchangeRatesByDateRangeQuery getExchangeRatesByDateRangeQuery;

    @Test
    public void shouldGetExchangeRatesByTimestampRange() {
        when(exchangeRateRepository.findByTimestampBetween(latestRate().getTimestamp(), oldRate().getTimestamp()))
                .thenReturn(rates());

        List<ExchangeRate> actualRates =
                getExchangeRatesByDateRangeQuery.run(latestRate().getTimestamp(), oldRate().getTimestamp());

        assertThat(actualRates).isEqualTo(rates());
    }

}