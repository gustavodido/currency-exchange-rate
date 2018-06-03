package service.scheduling;

import org.springframework.scheduling.annotation.Scheduled;
import service.infrastructure.annotations.Task;

@Task
public class CheckExchangeRateTask {
    @Scheduled(fixedRate = 1000)
    public void run() {
        System.out.println("Scheduler....");
    }
}
