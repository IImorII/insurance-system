package by.atsitou.dashboard.service.api.v1.queries.getagentssalesquery;

import by.atsitou.command.bus.api.Query;

import java.time.LocalDate;

import io.micronaut.core.annotation.Introspected;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Introspected
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetAgentsSalesQuery implements Query<GetAgentsSalesQueryResult> {
    private String agentLogin;
    private String productCode;
    private LocalDate saleDateFrom;
    private LocalDate saleDateTo;
}
