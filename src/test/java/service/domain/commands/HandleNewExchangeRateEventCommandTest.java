package service.domain.commands;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static service.support.Stubs.latestRate;
import static service.support.Stubs.latestRateNewEvent;

@RunWith(MockitoJUnitRunner.class)
public class HandleNewExchangeRateEventCommandTest {
    @Mock
    private SaveExchangeRateCommand saveExchangeRateCommand;

    @InjectMocks
    private HandleNewExchangeRateEventCommand handleNewExchangeRateEventCommand;

    @Test
    public void shouldSaveExchangeRateInTheEvent() {
        handleNewExchangeRateEventCommand.run(latestRateNewEvent());

        verify(saveExchangeRateCommand).run(latestRate());
    }

}