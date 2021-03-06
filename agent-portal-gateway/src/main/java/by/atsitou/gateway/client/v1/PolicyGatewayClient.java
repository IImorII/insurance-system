package by.atsitou.gateway.client.v1;

import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.retry.annotation.Retryable;
import by.atsitou.policy.service.api.v1.PolicyOperations;
import by.atsitou.policy.service.api.v1.commands.createoffer.CreateOfferCommand;
import by.atsitou.policy.service.api.v1.commands.createoffer.CreateOfferResult;
import by.atsitou.policy.service.api.v1.commands.createpolicy.CreatePolicyCommand;
import by.atsitou.policy.service.api.v1.commands.createpolicy.CreatePolicyResult;
import by.atsitou.policy.service.api.v1.commands.terminatepolicy.TerminatePolicyCommand;
import by.atsitou.policy.service.api.v1.commands.terminatepolicy.TerminatePolicyResult;
import by.atsitou.policy.service.api.v1.queries.getpolicydetails.GetPolicyDetailsQueryResult;

import javax.validation.constraints.NotNull;

@Client(id = "policy-service")
@Retryable(attempts = "2", delay = "2s")
public interface PolicyGatewayClient extends PolicyOperations {

    @Post("/offers")
    CreateOfferResult createOffer(@Body @NotNull CreateOfferCommand cmd);

    @Override
    @Get("/policies/{policyNumber}")
    GetPolicyDetailsQueryResult get(String policyNumber);

    @Override
    @Post("/policies")
    CreatePolicyResult create(CreatePolicyCommand cmd);

    @Override
    @Post("/policies/terminate")
    TerminatePolicyResult terminate(TerminatePolicyCommand cmd);
}
