package service.models;

import lombok.Value;

import java.math.BigDecimal;

@Value
public class ExchangeRate {
    private BigDecimal value;
}
