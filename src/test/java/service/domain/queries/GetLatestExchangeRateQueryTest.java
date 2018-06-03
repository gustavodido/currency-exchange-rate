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
public class GetLatestExchangeRateQueryTest {
    @Mock
    private ExchangeRateRepository exchangeRateRepository;

    @InjectMocks
    private GetLatestExchangeRateQuery getLatestExchangeRateQuery;

    @Test
    public void shouldGetLatestRate() {
        when(exchangeRateRepository.findFirstByOrderByTimestampDesc())
                .thenReturn(of(currentRate()));

        ExchangeRate actualRate = getLatestExchangeRateQuery.run();

        assertThat(actualRate).isEqualTo(currentRate());

    }

    @Test(expected = ExchangeRateNotFoundException.class)
    public void shouldThrowExceptionIfRateDoesNotExist() {
        when(exchangeRateRepository.findFirstByOrderByTimestampDesc())
                .thenReturn(empty());

        getLatestExchangeRateQuery.run();
    }
}