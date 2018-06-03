package service.models;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.Getter;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.Resources;
import service.controllers.ExchangeRateController;

import java.time.Instant;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Getter
public class ExchangeRateResource extends ResourceSupport {
    @JsonUnwrapped
    private final ExchangeRate exchangeRate;

    public ExchangeRateResource(ExchangeRate exchangeRate) {
        this.exchangeRate = exchangeRate;

//        add(ControllerLinkBuilder.linkTo(methodOn(AccomplishmentController.class).get(exchangeRate.getId())).withSelfRel());
//        add(ControllerLinkBuilder.linkTo(methodOn(ProfileController.class).get(exchangeRate.getProfileId())).withRel("profile"));
    }

    public static Resources<ExchangeRateResource> embedAsResources(List<ExchangeRate> exchangeRates, Instant from, Instant to) {
        List<ExchangeRateResource> resources = exchangeRates
                .stream()
                .map(ExchangeRateResource::new)
                .collect(toList());

        return new Resources<>(resources, linkTo(methodOn(ExchangeRateController.class).history(from, to)).withRel("self"));
    }
}
