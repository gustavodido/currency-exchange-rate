package service.domain.commands;

import org.springframework.context.event.EventListener;
import service.domain.models.NewExchangeRateEvent;
import service.infrastructure.annotations.Command;

@Command
public class HandleNewExchangeRateEventCommand {
    private final SaveExchangeRateCommand saveExchangeRateCommand;

    public HandleNewExchangeRateEventCommand(SaveExchangeRateCommand saveExchangeRateCommand) {
        this.saveExchangeRateCommand = saveExchangeRateCommand;
    }

    @EventListener
    public void run(NewExchangeRateEvent newExchangeRateEvent) {
        saveExchangeRateCommand.run(newExchangeRateEvent.getExchangeRate());
    }
}
