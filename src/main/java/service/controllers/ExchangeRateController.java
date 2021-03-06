package service.controllers;

import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import service.domain.queries.GetExchangeRateByTimestampQuery;
import service.domain.queries.GetExchangeRatesByDateRangeQuery;
import service.domain.queries.GetLatestExchangeRateQuery;

import java.time.Instant;

import static service.controllers.ExchangeRateResource.embedAsResources;
import static service.infrastructure.configuration.StaticConstants.APPLICATION_HAL_JSON;

@RestController
@RequestMapping(value = "/api/rates", produces = APPLICATION_HAL_JSON)
public class ExchangeRateController {
    private final GetExchangeRateByTimestampQuery getExchangeRateByIdQuery;
    private final GetLatestExchangeRateQuery getLatestExchangeRateQuery;
    private final GetExchangeRatesByDateRangeQuery getExchangeRatesByDateRangeQuery;

    public ExchangeRateController(GetExchangeRateByTimestampQuery getExchangeRateByIdQuery, GetLatestExchangeRateQuery getLatestExchangeRateQuery, GetExchangeRatesByDateRangeQuery getExchangeRatesByDateRangeQuery) {
        this.getExchangeRateByIdQuery = getExchangeRateByIdQuery;
        this.getLatestExchangeRateQuery = getLatestExchangeRateQuery;
        this.getExchangeRatesByDateRangeQuery = getExchangeRatesByDateRangeQuery;
    }
   @GetMapping("/{id}")
    public ExchangeRateResource get(@PathVariable Instant id) {
        return new ExchangeRateResource(getExchangeRateByIdQuery.run(id));
    }

    @GetMapping("/latest")
    public ExchangeRateResource latest() {
        return new ExchangeRateResource(getLatestExchangeRateQuery.run());
    }

    @GetMapping("/historical")
    public Resources<ExchangeRateResource> history(@RequestParam Instant from, @RequestParam Instant to) {
        return embedAsResources(getExchangeRatesByDateRangeQuery.run(from, to), from, to);
    }
}
