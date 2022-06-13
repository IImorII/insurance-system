package by.atsitou.policy.service.api.v1;

import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Post;
import javax.validation.constraints.NotNull;
import by.atsitou.policy.service.api.v1.commands.createoffer.CreateOfferCommand;
import by.atsitou.policy.service.api.v1.commands.createoffer.CreateOfferResult;

public interface OfferOperations {
    @Post("/")
    CreateOfferResult create(@Body @NotNull CreateOfferCommand cmd);
}
