package by.atsitou.policy.search.queries.findpolicy;

import by.atsitou.policy.search.readmodel.PolicyViewRepository;
import by.atsitou.policy.search.service.api.v1.queries.findpolicy.FindPolicyQuery;
import by.atsitou.policy.search.service.api.v1.queries.findpolicy.FindPolicyQueryResult;
import io.reactivex.Maybe;
import lombok.RequiredArgsConstructor;
import by.atsitou.command.bus.QueryHandler;

import javax.inject.Singleton;

@Singleton
@RequiredArgsConstructor
public class FindPolicyQueryHandler implements QueryHandler<Maybe<FindPolicyQueryResult>, FindPolicyQuery> {

    private final PolicyViewRepository policyViewRepository;

    @Override
    public Maybe<FindPolicyQueryResult> handle(FindPolicyQuery query) {
        return policyViewRepository
                .findAll(query)
                .map(PolicyQueryResultAssembler::constructResult);
    }

}
