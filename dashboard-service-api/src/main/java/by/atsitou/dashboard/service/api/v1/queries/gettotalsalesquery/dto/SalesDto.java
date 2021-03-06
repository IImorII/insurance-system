package by.atsitou.dashboard.service.api.v1.queries.gettotalsalesquery.dto;

import java.math.BigDecimal;

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
public class SalesDto {
    private Long policiesCount;
    private BigDecimal premiumAmount;
}
