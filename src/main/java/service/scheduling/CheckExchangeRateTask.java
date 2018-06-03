package service.scheduling;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import service.domain.queries.GetRateFromCurrencyConverterApiQuery;
import service.infrastructure.annotations.Task;

@Task
@ConditionalOnProperty(name = "application.scheduling.enabled", havingValue = "true")
public class CheckExchangeRateTask {
    private final GetRateFromCurrencyConverterApiQuery getRateFromCurrencyConverterApiQuery;

    public CheckExchangeRateTask(GetRateFromCurrencyConverterApiQuery getRateFromCurrencyConverterApiQuery) {
        this.getRateFromCurrencyConverterApiQuery = getRateFromCurrencyConverterApiQuery;
    }

    @Scheduled(fixedRateString = "${application.scheduling.interval}")
    public void run() {
        System.out.println("Scheduler...." + getRateFromCurrencyConverterApiQuery.run().getValue());
    }
}
