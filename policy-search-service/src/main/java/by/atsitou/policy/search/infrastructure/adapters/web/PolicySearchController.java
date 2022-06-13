package by.atsitou.policy.search.infrastructure.adapters.web;

import by.atsitou.policy.search.service.api.v1.PolicySearchOperations;
import io.micronaut.http.annotation.Controller;
import io.reactivex.Maybe;
import lombok.RequiredArgsConstructor;
import by.atsitou.command.bus.CommandBus;
import by.atsitou.policy.search.service.api.v1.queries.findpolicy.FindPolicyQuery;
import by.atsitou.policy.search.service.api.v1.queries.findpolicy.FindPolicyQueryResult;

@RequiredArgsConstructor
@Controller("/policies")
public class PolicySearchController implements PolicySearchOperations {

    private final CommandBus bus;

    @Override
    public Maybe<FindPolicyQueryResult> policies(String queryText) {
        return bus.executeQuery(new FindPolicyQuery(queryText));
    }
}
