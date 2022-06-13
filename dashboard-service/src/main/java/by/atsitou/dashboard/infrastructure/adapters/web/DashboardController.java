package by.atsitou.dashboard.infrastructure.adapters.web;


import io.micronaut.http.annotation.Controller;
import io.micronaut.validation.Validated;
import lombok.RequiredArgsConstructor;
import by.atsitou.command.bus.CommandBus;
import by.atsitou.dashboard.service.api.v1.DashboardOperations;
import by.atsitou.dashboard.service.api.v1.queries.getagentssalesquery.GetAgentsSalesQuery;
import by.atsitou.dashboard.service.api.v1.queries.getagentssalesquery.GetAgentsSalesQueryResult;
import by.atsitou.dashboard.service.api.v1.queries.getsalestrendsquery.GetSalesTrendsQuery;
import by.atsitou.dashboard.service.api.v1.queries.getsalestrendsquery.GetSalesTrendsQueryResult;
import by.atsitou.dashboard.service.api.v1.queries.gettotalsalesquery.GetTotalSalesQuery;
import by.atsitou.dashboard.service.api.v1.queries.gettotalsalesquery.GetTotalSalesQueryResult;


@RequiredArgsConstructor
@Validated
@Controller("/dashboard")
public class DashboardController implements DashboardOperations {

    private final CommandBus bus;

    @Override
    public GetTotalSalesQueryResult queryTotalSales(GetTotalSalesQuery query) {
        return bus.executeQuery(query);
    }

    @Override
    public GetSalesTrendsQueryResult querySalesTrends(GetSalesTrendsQuery query) {
        return bus.executeQuery(query);
    }

    @Override
    public GetAgentsSalesQueryResult queryAgentsSales(GetAgentsSalesQuery query) {
        return bus.executeQuery(query);
    }
}
