package by.atsitou.dashboard.infrastructure.adapters.elastic;

import by.atsitou.dashboard.domain.AgentSalesQuery;
import by.atsitou.dashboard.domain.SalesTrendsQuery;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import by.atsitou.dashboard.domain.TotalSalesQuery;

abstract class QueryAdapter<TQuery, TQueryResult> {
    protected TQuery query;

    QueryAdapter(TQuery query) {
        this.query = query;
    }

    abstract SearchRequest buildQuery();
    abstract TQueryResult extractResult(SearchResponse searchResponse);

    static TotalSalesQueryAdapter of(TotalSalesQuery query) {
        return new TotalSalesQueryAdapter(query);
    }

    static SalesTrendsQueryAdapter of(SalesTrendsQuery query) {
        return new SalesTrendsQueryAdapter(query);
    }

    static AgentSalesQueryAdapter of(AgentSalesQuery query) {
        return new AgentSalesQueryAdapter(query);
    }
}
