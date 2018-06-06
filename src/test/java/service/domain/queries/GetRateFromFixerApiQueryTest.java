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
import service.domain.models.dtos.FixerApiQueryDto;
import service.infrastructure.configuration.ApplicationConfiguration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.ACCEPTED;
import static service.support.Stubs.latestFixerApiDto;

@RunWith(MockitoJUnitRunner.class)
public class GetRateFromFixerApiQueryTest {
    @Mock
    private RestTemplate restTemplate;

    @Mock
    private ApplicationConfiguration applicationConfiguration;

    @InjectMocks
    private GetRateFromFixerApiQuery getRateFromFixerApiQuery;

    @Before
    public void setUp() {
        when(applicationConfiguration.getProviders()).thenReturn(new ApplicationConfiguration.ProvidersConfiguration());
    }

    @Test
    public void shouldGetExchangeRateFromApi() {
        when(restTemplate.getForEntity(anyString(), eq(FixerApiQueryDto.class)))
                .thenReturn(new ResponseEntity<>(latestFixerApiDto(), ACCEPTED));

        FixerApiQueryDto actualRate = getRateFromFixerApiQuery.run();

        assertThat(actualRate).isEqualTo(latestFixerApiDto());
    }

    @Test(expected = CurrencyConverterApiUnavailableException.class)
    public void shouldHandleInternalExceptionToDomainException() {
        when(restTemplate.getForEntity(anyString(), eq(FixerApiQueryDto.class)))
                .thenThrow(RestClientException.class);

        getRateFromFixerApiQuery.run();
    }
}