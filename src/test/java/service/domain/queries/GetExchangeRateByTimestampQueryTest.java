package service.domain.queries;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import service.domain.exceptions.ExchangeRateNotFoundException;
import service.domain.models.ExchangeRate;
import service.domain.repositories.ExchangeRateRepository;

import static java.util.Optional.empty;
import static java.util.Optional.of;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static service.support.Stubs.currentRate;

@RunWith(MockitoJUnitRunner.class)
public class GetExchangeRateByTimestampQueryTest {
    @Mock
    private ExchangeRateRepository exchangeRateRepository;

    @InjectMocks
    private GetExchangeRateByTimestampQuery getExchangeRateByTimestampQuery;

    @Test
    public void shouldGetRateByTimestamp() {
        when(exchangeRateRepository.findById(currentRate().getTimestamp()))
                .thenReturn(of(currentRate()));

        ExchangeRate actualRate = getExchangeRateByTimestampQuery.run(currentRate().getTimestamp());

        assertThat(actualRate).isEqualTo(currentRate());

    }

    @Test(expected = ExchangeRateNotFoundException.class)
    public void shouldThrowExceptionIfRateDoesNotExist() {
        when(exchangeRateRepository.findById(currentRate().getTimestamp()))
                .thenReturn(empty());

        getExchangeRateByTimestampQuery.run(currentRate().getTimestamp());
    }
}