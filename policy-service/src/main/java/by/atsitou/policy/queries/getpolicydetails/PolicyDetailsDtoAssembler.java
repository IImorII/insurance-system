package by.atsitou.policy.queries.getpolicydetails;

import by.atsitou.policy.domain.Cover;
import by.atsitou.policy.domain.PolicyVersion;
import by.atsitou.policy.service.api.v1.queries.getpolicydetails.dto.PolicyDetailsDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import by.atsitou.policy.domain.Policy;

import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
final class PolicyDetailsDtoAssembler {

    static PolicyDetailsDto map(Policy policy) {
        PolicyVersion policyVersion = policy.versions().lastVersion();

        return new PolicyDetailsDto(
                policy.getNumber(),
                policyVersion.getVersionValidityPeriod().getFrom(),
                policyVersion.getVersionValidityPeriod().getTo(),
                policyVersion.getPolicyHolder().getFullName(),
                policyVersion.getTotalPremiumAmount(),
                policyVersion.getProductCode(),
                policyVersion.getAccountNumber(),
                policyVersion.getCovers().stream()
                        .map(Cover::toString)
                        .sorted()
                        .collect(Collectors.toSet())
        );
    }
}
