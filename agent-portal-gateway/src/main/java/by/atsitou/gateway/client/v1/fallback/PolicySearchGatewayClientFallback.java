package by.atsitou.gateway.client.v1.fallback;

import io.micronaut.retry.annotation.Fallback;
import io.reactivex.Maybe;
import by.atsitou.policy.search.service.api.v1.PolicySearchOperations;
import by.atsitou.policy.search.service.api.v1.queries.findpolicy.FindPolicyQueryResult;

import javax.inject.Singleton;

@Singleton
@Fallback
public class PolicySearchGatewayClientFallback implements PolicySearchOperations {
    @Override
    public Maybe<FindPolicyQueryResult> policies(String queryText) {
        return Maybe.just(FindPolicyQueryResult.empty());
    }
}
