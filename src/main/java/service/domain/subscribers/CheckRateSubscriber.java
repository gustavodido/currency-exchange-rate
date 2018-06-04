package service.domain.subscribers;

import org.springframework.stereotype.Component;
import service.domain.subscribers.TaskSubscriber;

import java.time.Instant;

@Component
public class CheckRateSubscriber implements TaskSubscriber {
    @Override
    public void onExecute(Instant timestamp) {
        System.out.println("buuuu");
    }
}
