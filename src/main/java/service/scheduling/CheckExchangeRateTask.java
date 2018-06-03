package service.scheduling;

import org.springframework.scheduling.annotation.Scheduled;
import service.domain.queries.GetRateFromCurrencyConverterApiQuery;
import service.infrastructure.annotations.Task;

@Task
public class CheckExchangeRateTask {
    private final GetRateFromCurrencyConverterApiQuery getRateFromCurrencyConverterApiQuery;

    public CheckExchangeRateTask(GetRateFromCurrencyConverterApiQuery getRateFromCurrencyConverterApiQuery) {
        this.getRateFromCurrencyConverterApiQuery = getRateFromCurrencyConverterApiQuery;
    }

    @Scheduled(fixedRate = 1000)
    public void run() {
        System.out.println("Scheduler...." + getRateFromCurrencyConverterApiQuery.run().getValue());
    }
}
