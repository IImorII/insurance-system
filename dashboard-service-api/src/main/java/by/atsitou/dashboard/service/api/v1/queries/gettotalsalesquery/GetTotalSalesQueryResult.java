package by.atsitou.dashboard.service.api.v1.queries.gettotalsalesquery;

import by.atsitou.dashboard.service.api.v1.queries.gettotalsalesquery.dto.SalesDto;

import java.util.Map;

import io.micronaut.core.annotation.Introspected;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Introspected
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetTotalSalesQueryResult {
    private SalesDto total;
    private Map<String,SalesDto> perProductTotal;
}
