package by.atsitou.dashboard.queries.gettotalsales;

import by.atsitou.dashboard.domain.LocalDateRange;
import by.atsitou.dashboard.service.api.v1.queries.gettotalsalesquery.GetTotalSalesQueryResult;
import lombok.RequiredArgsConstructor;
import by.atsitou.command.bus.QueryHandler;
import by.atsitou.dashboard.domain.PolicyRepository;
import by.atsitou.dashboard.domain.TotalSalesQuery;
import by.atsitou.dashboard.service.api.v1.queries.gettotalsalesquery.GetTotalSalesQuery;

import javax.inject.Singleton;


@Singleton
@RequiredArgsConstructor
public class GetTotalSalesQueryHandler implements QueryHandler<GetTotalSalesQueryResult, GetTotalSalesQuery> {

    private final PolicyRepository policyRepository;

    @Override
    public GetTotalSalesQueryResult handle(GetTotalSalesQuery query) {
        TotalSalesQuery.Result totalSales = policyRepository.getTotalSales(TotalSalesQuery.builder()
                .filterByProductCode(query.getProductCode())
                .filterBySalesDate(LocalDateRange.between(query.getSaleDateFrom(), query.getSaleDateTo()))
                .build());
        return GetTotalSalesQueryResultAssembler.assemble(totalSales);
    }
}
