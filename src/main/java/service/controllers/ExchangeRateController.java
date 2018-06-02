package service.controllers;

import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import service.models.ExchangeRate;
import service.resources.ExchangeRateResource;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.Arrays.asList;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController("/api/rate")
public class ExchangeRateController {

    @GetMapping("/{id}")
    public ExchangeRateResource get(@PathVariable LocalDateTime id) {
        return new ExchangeRateResource(new ExchangeRate());
    }

    @GetMapping("/latest")
    public ExchangeRateResource latest() {
        return new ExchangeRateResource(new ExchangeRate());
    }

    @GetMapping("/history")
    public Resources<ExchangeRateResource> history(@RequestParam LocalDateTime from, @RequestParam LocalDateTime to) {
        List<ExchangeRateResource> models = asList(latest(), latest());
        return new Resources<>(models, linkTo(methodOn(ExchangeRateController.class).history(from, to)).withRel("self"));
    }
}
