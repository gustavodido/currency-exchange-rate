package service.scheduling;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "application.scheduling.enabled", havingValue = "true")
public class CheckNewExchangeRatesTaskWrapper {
    private final CheckNewExchangeRatesTask checkNewExchangeRatesTask;

    public CheckNewExchangeRatesTaskWrapper(CheckNewExchangeRatesTask checkNewExchangeRatesTask) {
        this.checkNewExchangeRatesTask = checkNewExchangeRatesTask;
    }

    @Scheduled(fixedRateString = "${application.scheduling.interval}")
    public void run() {
        checkNewExchangeRatesTask.run();
    }
}
