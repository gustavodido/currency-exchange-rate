package service.domain.models;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class NewExchangeRateEvent {
    private ExchangeRate exchangeRate;
}
