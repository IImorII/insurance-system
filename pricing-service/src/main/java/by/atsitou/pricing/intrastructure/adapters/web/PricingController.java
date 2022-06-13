package by.atsitou.pricing.intrastructure.adapters.web;

import by.atsitou.pricing.commands.CalculatePriceHandler;
import by.atsitou.pricing.service.api.v1.PricingOperations;
import by.atsitou.pricing.service.api.v1.commands.calculateprice.CalculatePriceCommand;
import by.atsitou.pricing.service.api.v1.commands.calculateprice.CalculatePriceResult;

import io.micronaut.http.annotation.Controller;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import io.micronaut.validation.Validated;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller("/pricing")
@Validated
@RequiredArgsConstructor
@Slf4j
public class PricingController implements PricingOperations {

    private final CalculatePriceHandler calculatePriceHandler;

    @ExecuteOn(TaskExecutors.IO)
    @Override
    public CalculatePriceResult calculatePrice(CalculatePriceCommand cmd) {
        return calculatePriceHandler.handle(cmd);
    }
}
