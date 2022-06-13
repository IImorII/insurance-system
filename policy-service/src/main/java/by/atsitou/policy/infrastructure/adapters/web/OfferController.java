package by.atsitou.policy.infrastructure.adapters.web;

import by.atsitou.command.bus.CommandBus;
import by.atsitou.policy.service.api.v1.OfferOperations;
import by.atsitou.policy.service.api.v1.commands.createoffer.CreateOfferCommand;
import by.atsitou.policy.service.api.v1.commands.createoffer.CreateOfferResult;

import io.micronaut.http.annotation.Controller;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import io.micronaut.validation.Validated;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Validated
@Controller("/offers")
public class OfferController implements OfferOperations {

    private final CommandBus bus;

    @ExecuteOn(TaskExecutors.IO)
    @Override
    public CreateOfferResult create(CreateOfferCommand cmd) {
        return bus.executeCommand(cmd);
    }
}
