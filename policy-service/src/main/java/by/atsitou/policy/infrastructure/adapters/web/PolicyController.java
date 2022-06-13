package by.atsitou.policy.infrastructure.adapters.web;

import by.atsitou.command.bus.CommandBus;
import by.atsitou.policy.service.api.v1.PolicyOperations;
import by.atsitou.policy.service.api.v1.commands.createpolicy.CreatePolicyCommand;
import by.atsitou.policy.service.api.v1.commands.createpolicy.CreatePolicyResult;
import by.atsitou.policy.service.api.v1.commands.terminatepolicy.TerminatePolicyCommand;
import by.atsitou.policy.service.api.v1.commands.terminatepolicy.TerminatePolicyResult;
import by.atsitou.policy.service.api.v1.queries.getpolicydetails.GetPolicyDetailsQuery;
import by.atsitou.policy.service.api.v1.queries.getpolicydetails.GetPolicyDetailsQueryResult;

import io.micronaut.http.annotation.Controller;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import io.micronaut.validation.Validated;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Validated
@Controller("/policies")
public class PolicyController implements PolicyOperations {

    private final CommandBus bus;

    @ExecuteOn(TaskExecutors.IO)
    @Override
    public GetPolicyDetailsQueryResult get(String policyNumber) {
        return bus.executeQuery(new GetPolicyDetailsQuery(policyNumber));
    }

    @ExecuteOn(TaskExecutors.IO)
    @Override
    public CreatePolicyResult create(CreatePolicyCommand cmd) {
        return bus.executeCommand(cmd);
    }

    @ExecuteOn(TaskExecutors.IO)
    @Override
    public TerminatePolicyResult terminate(TerminatePolicyCommand cmd) {
        return bus.executeCommand(cmd);
    }
}
