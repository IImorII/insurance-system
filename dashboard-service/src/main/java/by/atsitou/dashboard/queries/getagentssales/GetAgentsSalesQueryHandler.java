package by.atsitou.dashboard.queries.getagentssales;

import by.atsitou.dashboard.domain.AgentSalesQuery;
import by.atsitou.dashboard.domain.LocalDateRange;
import by.atsitou.dashboard.domain.PolicyRepository;
import by.atsitou.dashboard.service.api.v1.queries.getagentssalesquery.GetAgentsSalesQuery;
import by.atsitou.dashboard.service.api.v1.queries.getagentssalesquery.GetAgentsSalesQueryResult;
import lombok.RequiredArgsConstructor;
import by.atsitou.command.bus.QueryHandler;

import javax.inject.Singleton;

@Singleton
@RequiredArgsConstructor
public class GetAgentsSalesQueryHandler implements QueryHandler<GetAgentsSalesQueryResult, GetAgentsSalesQuery> {

    private final PolicyRepository policyRepository;

    @Override
    public GetAgentsSalesQueryResult handle(GetAgentsSalesQuery query) {
        AgentSalesQuery.Result agentsSales = policyRepository.getAgentSales(AgentSalesQuery.builder()
                .filterByAgentLogin(query.getAgentLogin())
                .filterByProductCode(query.getProductCode())
                .filterBySalesDate(LocalDateRange.between(query.getSaleDateFrom(),query.getSaleDateTo()))
                .build());
        return GetAgentsSalesQueryResultAssembler.assemble(agentsSales);
    }
}
