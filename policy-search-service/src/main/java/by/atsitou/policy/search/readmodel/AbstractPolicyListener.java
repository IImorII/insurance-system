package by.atsitou.policy.search.readmodel;

import by.atsitou.policy.service.api.v1.events.dto.PolicyDto;

import javax.inject.Inject;

abstract class AbstractPolicyListener {

    @Inject
    private PolicyViewRepository policyViewRepository;

    void saveMappedPolicy(PolicyDto policy) {
        PolicyView view = PolicyViewAssembler.map(policy);
        policyViewRepository.save(view);
    }
}
