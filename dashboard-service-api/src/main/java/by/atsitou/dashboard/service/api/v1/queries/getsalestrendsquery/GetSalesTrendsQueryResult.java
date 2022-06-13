package by.atsitou.dashboard.service.api.v1.queries.getsalestrendsquery;

import by.atsitou.dashboard.service.api.v1.queries.getsalestrendsquery.dto.PeriodSalesDto;

import java.util.List;

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
public class GetSalesTrendsQueryResult {
    List<PeriodSalesDto> periodSales;
}
