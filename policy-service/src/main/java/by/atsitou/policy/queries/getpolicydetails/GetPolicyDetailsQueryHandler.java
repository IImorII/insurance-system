package by.atsitou.policy.queries.getpolicydetails;

import by.atsitou.command.bus.QueryHandler;
import by.atsitou.policy.domain.PolicyRepository;
import by.atsitou.policy.service.api.v1.queries.getpolicydetails.GetPolicyDetailsQuery;
import by.atsitou.policy.service.api.v1.queries.getpolicydetails.GetPolicyDetailsQueryResult;
import by.atsitou.policy.shared.exceptions.BusinessException;
import by.atsitou.policy.domain.Policy;

import java.util.Optional;

import javax.inject.Singleton;

import io.micronaut.transaction.annotation.ReadOnly;
import lombok.RequiredArgsConstructor;

@Singleton
@RequiredArgsConstructor
public class GetPolicyDetailsQueryHandler implements QueryHandler<GetPolicyDetailsQueryResult, GetPolicyDetailsQuery> {

    private final PolicyRepository policyRepository;

    @ReadOnly
    @Override
    public GetPolicyDetailsQueryResult handle(GetPolicyDetailsQuery query) {
        Optional<Policy> policyOpt = policyRepository.findByNumber(query.getNumber());
        if (!policyOpt.isPresent())
            throw new BusinessException("POLICY NOT FOUND");

        return new GetPolicyDetailsQueryResult(PolicyDetailsDtoAssembler.map(policyOpt.get()));
    }
}
