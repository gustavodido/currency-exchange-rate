package service.domain.exceptions;

import java.time.Instant;

import static java.lang.String.format;

public class ExchangeRateNotFoundException extends RuntimeException {
    public ExchangeRateNotFoundException(Instant timestamp) {
        super(format("Could not find any exchange rate for the timestamp %s.", timestamp));
    }

    public ExchangeRateNotFoundException() {
        super("Could not find any exchange rate.");
    }
}

