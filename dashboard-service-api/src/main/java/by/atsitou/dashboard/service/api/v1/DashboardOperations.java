package by.atsitou.dashboard.service.api.v1;

import by.atsitou.dashboard.service.api.v1.queries.getagentssalesquery.GetAgentsSalesQuery;
import by.atsitou.dashboard.service.api.v1.queries.getagentssalesquery.GetAgentsSalesQueryResult;
import by.atsitou.dashboard.service.api.v1.queries.getsalestrendsquery.GetSalesTrendsQuery;
import by.atsitou.dashboard.service.api.v1.queries.gettotalsalesquery.GetTotalSalesQueryResult;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Post;
import by.atsitou.dashboard.service.api.v1.queries.getsalestrendsquery.GetSalesTrendsQueryResult;
import by.atsitou.dashboard.service.api.v1.queries.gettotalsalesquery.GetTotalSalesQuery;

public interface DashboardOperations {

    @Post("/totalsales")
    GetTotalSalesQueryResult queryTotalSales(@Body GetTotalSalesQuery query);

    @Post("/trends")
    GetSalesTrendsQueryResult querySalesTrends(@Body GetSalesTrendsQuery query);

    @Post("/agentssales")
    GetAgentsSalesQueryResult queryAgentsSales(@Body GetAgentsSalesQuery query);

}
