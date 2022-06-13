package by.atsitou.policy.search.service.api.v1;

import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.QueryValue;
import io.reactivex.Maybe;
import by.atsitou.policy.search.service.api.v1.queries.findpolicy.FindPolicyQueryResult;

public interface PolicySearchOperations {

    @Get
    Maybe<FindPolicyQueryResult> policies(@QueryValue("q") String queryText);
}
