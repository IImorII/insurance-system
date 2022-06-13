package by.atsitou.policy.infrastructure.adapters.restclient;

import by.atsitou.pricing.service.api.v1.PricingOperations;
import by.atsitou.pricing.service.api.v1.commands.calculateprice.CalculatePriceCommand;
import by.atsitou.pricing.service.api.v1.commands.calculateprice.CalculatePriceResult;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.client.annotation.Client;

@Client(id = "pricing-service")
public interface PricingClient extends PricingOperations {
    @Override
    @Post("/pricing/calculate")
    CalculatePriceResult calculatePrice(CalculatePriceCommand cmd);
}