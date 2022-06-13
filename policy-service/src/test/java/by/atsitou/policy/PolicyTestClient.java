package by.atsitou.policy;

import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.client.annotation.Client;
import by.atsitou.policy.service.api.v1.commands.createpolicy.CreatePolicyCommand;
import by.atsitou.policy.service.api.v1.commands.createpolicy.CreatePolicyResult;
import by.atsitou.policy.service.api.v1.commands.terminatepolicy.TerminatePolicyCommand;
import by.atsitou.policy.service.api.v1.commands.terminatepolicy.TerminatePolicyResult;
import by.atsitou.policy.service.api.v1.queries.getpolicydetails.GetPolicyDetailsQueryResult;

@Client(id = "/policy-service", path = "/policies")
public interface PolicyTestClient {

    @Get("/{policyNumber}")
    GetPolicyDetailsQueryResult get(String policyNumber);

    @Post("/")
    CreatePolicyResult create(@Body CreatePolicyCommand cmd);

    @Post("/terminate")
    TerminatePolicyResult terminate(@Body TerminatePolicyCommand cmd);

}
