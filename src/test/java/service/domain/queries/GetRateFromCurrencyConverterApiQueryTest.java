package service.domain.queries;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import service.domain.models.ConverterApiQueryDto;
import service.domain.models.ExchangeRate;
import service.infrastructure.configuration.properties.ApplicationConfiguration;
import service.infrastructure.configuration.properties.UrlsConfiguration;
import service.infrastructure.providers.TimeProvider;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.ACCEPTED;
import static service.support.Stubs.latestRate;
import static service.support.Stubs.latestRateDto;

@RunWith(MockitoJUnitRunner.class)
public class GetRateFromCurrencyConverterApiQueryTest {
    @Mock
    private RestTemplate restTemplate;

    @Mock
    private ApplicationConfiguration applicationConfiguration;

    @Mock
    private TimeProvider timeProvider;

    @InjectMocks
    private GetRateFromCurrencyConverterApiQuery getRateFromCurrencyConverterApiQuery;

    @Before
    public void setUp() {
        when(timeProvider.now()).thenReturn(latestRate().getTimestamp());
        when(applicationConfiguration.getUrls()).thenReturn(new UrlsConfiguration());
    }

    @Test
    public void shouldGetExchangeRateFromApi() {
        when(restTemplate.getForEntity(anyString(), eq(ConverterApiQueryDto.class)))
                .thenReturn(new ResponseEntity<>(latestRateDto(), ACCEPTED));

        ExchangeRate actualRate = getRateFromCurrencyConverterApiQuery.run();

        assertThat(actualRate).isEqualTo(latestRate());
    }

}