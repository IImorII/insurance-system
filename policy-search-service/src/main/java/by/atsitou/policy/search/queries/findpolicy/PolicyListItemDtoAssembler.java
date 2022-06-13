package by.atsitou.policy.search.queries.findpolicy;

import by.atsitou.policy.search.readmodel.PolicyView;
import by.atsitou.policy.search.service.api.v1.queries.findpolicy.dto.PolicyListItemDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
final class PolicyListItemDtoAssembler {

    static PolicyListItemDto map(PolicyView policy) {
        return new PolicyListItemDto(
                policy.getNumber(),
                policy.getDateFrom(),
                policy.getDateTo(),
                policy.getPolicyHolder()
        );
    }
}
