package by.atsitou.dashboard.queries.getsalestrends;

import by.atsitou.dashboard.domain.LocalDateRange;
import by.atsitou.dashboard.domain.PolicyRepository;
import by.atsitou.dashboard.domain.SalesTrendsQuery;
import by.atsitou.dashboard.domain.TimeAggregationUnit;
import by.atsitou.dashboard.service.api.v1.queries.getsalestrendsquery.GetSalesTrendsQuery;
import by.atsitou.dashboard.service.api.v1.queries.getsalestrendsquery.GetSalesTrendsQueryResult;
import lombok.RequiredArgsConstructor;
import by.atsitou.command.bus.QueryHandler;

import javax.inject.Singleton;

@Singleton
@RequiredArgsConstructor
public class GetSalesTrendsQueryHandler implements QueryHandler<GetSalesTrendsQueryResult, GetSalesTrendsQuery> {

    private final PolicyRepository policyRepository;

    @Override
    public GetSalesTrendsQueryResult handle(GetSalesTrendsQuery query) {
        SalesTrendsQuery.Result salesTrends = policyRepository.getSalesTrends(SalesTrendsQuery.builder()
                .filterByProductCode(query.getProductCode())
                .filterBySalesDate(LocalDateRange.between(query.getSaleDateFrom(),query.getSaleDateTo()))
                .aggregationUnit(TimeAggregationUnit.valueOf(query.getAggregationUnitCode()))
                .build());
        return GetSalesTrendsQueryResultAssembler.assemble(salesTrends);
    }
}
