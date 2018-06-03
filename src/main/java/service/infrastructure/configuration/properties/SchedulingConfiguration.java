package service.infrastructure.configuration.properties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SchedulingConfiguration {
    private long interval = 0L;
    private boolean enabled = false;
}
