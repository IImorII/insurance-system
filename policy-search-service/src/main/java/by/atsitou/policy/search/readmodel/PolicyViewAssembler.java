package by.atsitou.policy.search.readmodel;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import by.atsitou.policy.service.api.v1.events.dto.PolicyDto;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
final class PolicyViewAssembler {

    static PolicyView map(PolicyDto policy) {
        return PolicyView.builder()
                .number(policy.getNumber())
                .dateFrom(policy.getFrom())
                .dateTo(policy.getTo())
                .policyHolder(policy.getPolicyHolder())
                .build();
    }
}
