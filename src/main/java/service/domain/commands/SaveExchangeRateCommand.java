package service.domain.commands;

import service.domain.models.ExchangeRate;
import service.domain.repositories.ExchangeRateRepository;
import service.infrastructure.annotations.Command;

@Command
public class SaveExchangeRateCommand {
    private final ExchangeRateRepository exchangeRateRepository;

    public SaveExchangeRateCommand(ExchangeRateRepository exchangeRateRepository) {
        this.exchangeRateRepository = exchangeRateRepository;
    }

    public void run(ExchangeRate exchangeRate) {
        exchangeRateRepository.save(exchangeRate);
    }
}
