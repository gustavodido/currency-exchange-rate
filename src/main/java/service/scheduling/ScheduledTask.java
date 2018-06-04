package service.scheduling;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import service.domain.subscribers.TaskSubscriber;
import service.infrastructure.providers.TimeProvider;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static java.util.Optional.ofNullable;

@Component
@ConditionalOnProperty(name = "application.scheduling.enabled", havingValue = "true")
public class ScheduledTask {
    private final List<TaskSubscriber> subscribers;
    private final TimeProvider timeProvider;

    public ScheduledTask(List<TaskSubscriber> subscribers, TimeProvider timeProvider) {
        this.subscribers = subscribers;
        this.timeProvider = timeProvider;
    }

    @Scheduled(fixedRateString = "${application.scheduling.interval}")
    public void run() {
        Instant instant = timeProvider.now();

        ofNullable(subscribers)
                .orElseGet(ArrayList::new)
                .forEach(subscriber -> subscriber.onExecute(instant));
    }
}
