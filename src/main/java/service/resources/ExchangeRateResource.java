package service.resources;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.Getter;
import org.springframework.hateoas.ResourceSupport;
import service.models.ExchangeRate;

@Getter
public class ExchangeRateResource  extends ResourceSupport {
    @JsonUnwrapped
    private final ExchangeRate accomplishment;

    public ExchangeRateResource(ExchangeRate accomplishment) {
        this.accomplishment = accomplishment;

//        add(ControllerLinkBuilder.linkTo(methodOn(AccomplishmentController.class).get(accomplishment.getId())).withSelfRel());
//        add(ControllerLinkBuilder.linkTo(methodOn(ProfileController.class).get(accomplishment.getProfileId())).withRel("profile"));
    }
}
