package by.atsitou.gateway.client.v1;


import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.retry.annotation.Retryable;
import by.atsitou.dashboard.service.api.v1.DashboardOperations;
import by.atsitou.dashboard.service.api.v1.queries.getagentssalesquery.GetAgentsSalesQuery;
import by.atsitou.dashboard.service.api.v1.queries.getagentssalesquery.GetAgentsSalesQueryResult;
import by.atsitou.dashboard.service.api.v1.queries.getsalestrendsquery.GetSalesTrendsQuery;
import by.atsitou.dashboard.service.api.v1.queries.getsalestrendsquery.GetSalesTrendsQueryResult;
import by.atsitou.dashboard.service.api.v1.queries.gettotalsalesquery.GetTotalSalesQuery;
import by.atsitou.dashboard.service.api.v1.queries.gettotalsalesquery.GetTotalSalesQueryResult;

@Client(id = "dashboard-service", path = "/dashboard")
@Retryable(attempts = "2", delay = "2s")
public interface DashboardGatewayClient extends DashboardOperations {

    @Override
    @Post("/totalsales")
    GetTotalSalesQueryResult queryTotalSales(@Body GetTotalSalesQuery query);

    @Override
    @Post("/trends")
    GetSalesTrendsQueryResult querySalesTrends(@Body GetSalesTrendsQuery query);

    @Override
    @Post("/agentssales")
    GetAgentsSalesQueryResult queryAgentsSales(@Body GetAgentsSalesQuery query);

}
