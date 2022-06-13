package by.atsitou.pricing.service.api.v1;

import by.atsitou.pricing.service.api.v1.commands.calculateprice.CalculatePriceCommand;
import by.atsitou.pricing.service.api.v1.commands.calculateprice.CalculatePriceResult;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Post;

import javax.validation.constraints.NotNull;

public interface PricingOperations {

    @Post("/calculate")
    CalculatePriceResult calculatePrice(@Body @NotNull CalculatePriceCommand cmd);
}
