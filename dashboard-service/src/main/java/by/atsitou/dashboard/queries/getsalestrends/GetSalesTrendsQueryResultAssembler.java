package by.atsitou.dashboard.queries.getsalestrends;

import by.atsitou.dashboard.domain.SalesTrendsQuery;
import by.atsitou.dashboard.service.api.v1.queries.getsalestrendsquery.GetSalesTrendsQueryResult;
import by.atsitou.dashboard.service.api.v1.queries.getsalestrendsquery.dto.PeriodSalesDto;

import java.util.ArrayList;

public class GetSalesTrendsQueryResultAssembler {

    public static GetSalesTrendsQueryResult assemble(SalesTrendsQuery.Result salesTrands) {
        GetSalesTrendsQueryResult result = new GetSalesTrendsQueryResult(new ArrayList<>());
        salesTrands.getPeriodSales().forEach(periodSales ->
                result.getPeriodSales().add(new PeriodSalesDto(
                        periodSales.getPeriodDate(),
                        periodSales.getPeriod(),
                        periodSales.getSales().getPoliciesCount(),
                        periodSales.getSales().getPremiumAmount()
                )));
        return result;
    }
}
