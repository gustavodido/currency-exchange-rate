package service.scheduling;

import java.time.Instant;

public interface TaskSubscriber {
    void onExecute(Instant timestamp);
}
