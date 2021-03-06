package service.domain.queries;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import service.domain.exceptions.CurrencyConverterApiUnavailableException;
import service.domain.models.dtos.ConverterApiQueryDto;
import service.infrastructure.configuration.ApplicationConfiguration;
import service.infrastructure.configuration.ApplicationConfiguration.ProvidersConfiguration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.ACCEPTED;
import static service.support.Stubs.latestConverterApiDto;

@RunWith(MockitoJUnitRunner.class)
public class GetRateFromCurrencyConverterApiQueryTest {
    @Mock
    private RestTemplate restTemplate;

    @Mock
    private ApplicationConfiguration applicationConfiguration;

    @InjectMocks
    private GetRateFromCurrencyConverterApiQuery getRateFromCurrencyConverterApiQuery;

    @Before
    public void setUp() {
        when(applicationConfiguration.getProviders()).thenReturn(new ProvidersConfiguration());
    }

    @Test
    public void shouldGetExchangeRateFromApi() {
        when(restTemplate.getForEntity(anyString(), eq(ConverterApiQueryDto.class)))
                .thenReturn(new ResponseEntity<>(latestConverterApiDto(), ACCEPTED));

        ConverterApiQueryDto actualRate = getRateFromCurrencyConverterApiQuery.run();

        assertThat(actualRate).isEqualTo(latestConverterApiDto());
    }

    @Test(expected = CurrencyConverterApiUnavailableException.class)
    public void shouldHandleInternalExceptionToDomainException() {
        when(restTemplate.getForEntity(anyString(), eq(ConverterApiQueryDto.class)))
                .thenThrow(RestClientException.class);

        getRateFromCurrencyConverterApiQuery.run();
    }
}