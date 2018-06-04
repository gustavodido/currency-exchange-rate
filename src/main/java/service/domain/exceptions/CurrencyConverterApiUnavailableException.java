package service.domain.exceptions;

import org.springframework.web.client.RestClientException;

import static java.lang.String.format;

public class CurrencyConverterApiUnavailableException extends RuntimeException {
    public CurrencyConverterApiUnavailableException(String uri, RestClientException e) {
        super(format("It was not possible to fetch %s.", uri), e);
    }
}
