package by.atsitou.policy.service.api.v1.queries.getpolicydetails;

import by.atsitou.command.bus.api.Query;

import io.micronaut.core.annotation.Introspected;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Introspected
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetPolicyDetailsQuery implements Query<GetPolicyDetailsQueryResult> {
    private String number;
}
