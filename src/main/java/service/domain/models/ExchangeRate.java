package service.domain.models;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.Instant;

@Data
@Entity
@Builder
public class ExchangeRate {
    @Id
    private Instant timestamp;
    private double value;
}
