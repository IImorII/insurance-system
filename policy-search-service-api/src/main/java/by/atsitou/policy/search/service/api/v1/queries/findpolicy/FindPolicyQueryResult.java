package by.atsitou.policy.search.service.api.v1.queries.findpolicy;

import by.atsitou.policy.search.service.api.v1.queries.findpolicy.dto.PolicyListItemDto;

import java.util.Collections;
import java.util.List;

import io.micronaut.core.annotation.Introspected;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Introspected
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class FindPolicyQueryResult {
    private List<PolicyListItemDto> policies;

    public static FindPolicyQueryResult empty() {
        return new FindPolicyQueryResult(Collections.emptyList());
    }
}
