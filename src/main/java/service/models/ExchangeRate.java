package service.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.Instant;

@Data
@Entity
public class ExchangeRate {
    @Id
    private Instant timestamp;
    private double value;
}