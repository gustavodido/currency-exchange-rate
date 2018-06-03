package service.domain.commands;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import service.domain.repositories.ExchangeRateRepository;

import static org.mockito.Mockito.verify;
import static service.support.Stubs.latestRate;

@RunWith(MockitoJUnitRunner.class)
public class SaveExchangeRateCommandTest {
    @Mock
    private ExchangeRateRepository exchangeRateRepository;

    @InjectMocks
    private SaveExchangeRateCommand saveExchangeRateCommand;

    @Test
    public void shouldSaveNewExchangeRate() {
        saveExchangeRateCommand.run(latestRate());

        verify(exchangeRateRepository).save(latestRate());
    }
}