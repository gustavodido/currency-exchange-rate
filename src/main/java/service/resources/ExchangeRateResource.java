package service.resources;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.Getter;
import org.springframework.hateoas.ResourceSupport;
import service.models.ExchangeRate;

@Getter
public class ExchangeRateResource extends ResourceSupport {
    @JsonUnwrapped
    private final ExchangeRate exchangeRate;

    public ExchangeRateResource(ExchangeRate exchangeRate) {
        this.exchangeRate = exchangeRate;

//        add(ControllerLinkBuilder.linkTo(methodOn(AccomplishmentController.class).get(exchangeRate.getId())).withSelfRel());
//        add(ControllerLinkBuilder.linkTo(methodOn(ProfileController.class).get(exchangeRate.getProfileId())).withRel("profile"));
    }
}
