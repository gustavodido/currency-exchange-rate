package service.domain.subscribers;

import java.time.Instant;

public interface TaskSubscriber {
    void onExecute(Instant timestamp);
}
