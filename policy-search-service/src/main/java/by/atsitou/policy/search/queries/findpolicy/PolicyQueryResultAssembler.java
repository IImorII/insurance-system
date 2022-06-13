package by.atsitou.policy.search.queries.findpolicy;

import by.atsitou.policy.search.service.api.v1.queries.findpolicy.dto.PolicyListItemDto;
import by.atsitou.policy.search.readmodel.PolicyView;
import by.atsitou.policy.search.service.api.v1.queries.findpolicy.FindPolicyQueryResult;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

class PolicyQueryResultAssembler {

    static FindPolicyQueryResult constructResult(List<PolicyView> policies) {
        return new FindPolicyQueryResult(
                policies.stream()
                        .map(PolicyListItemDtoAssembler::map)
                        .sorted(Comparator.comparing(PolicyListItemDto::getDateFrom, Comparator.nullsLast(Comparator.reverseOrder())))
                        .collect(Collectors.toList())
        );
    }
}