package service.domain.messaging;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import service.domain.commands.SaveExchangeRateCommand;
import service.domain.queries.GetRateFromCurrencyConverterApiQuery;

@RunWith(MockitoJUnitRunner.class)
public class CheckExchangeRateSubscriberTest {
    @Mock
    private GetRateFromCurrencyConverterApiQuery getRateFromCurrencyConverterApiQuery;

    @Mock
    private SaveExchangeRateCommand saveExchangeRateCommand;

    @InjectMocks
    private CheckExchangeRateSubscriber checkRateSubscriber;




}