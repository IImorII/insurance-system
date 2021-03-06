package by.atsitou.dashboard.elastic;

import by.atsitou.dashboard.domain.LocalDateRange;
import by.atsitou.dashboard.domain.PolicyDocument;
import by.atsitou.dashboard.infrastructure.adapters.elastic.PolicyElasticRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import by.atsitou.dashboard.domain.TotalSalesQuery;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PolicyElasticRepositoryGetTotalSalesTest extends EmbeddedElasticTest {

    @BeforeAll
    public void seedData() {
        List<PolicyDocument> docs = Arrays.asList(
                new PolicyDocument(
                        "111-001",
                        LocalDate.of(2019, 1, 1),
                        LocalDate.of(2019, 12, 31),
                        "John Smith",
                        "SAFE_HOUSE",
                        BigDecimal.valueOf(1000),
                        "m.smith"),
                new PolicyDocument(
                        "111-002",
                        LocalDate.of(2019, 2, 1),
                        LocalDate.of(2020, 1, 31),
                        "John Smith",
                        "SAFE_HOUSE",
                        BigDecimal.valueOf(1000),
                        "m.smith"),
                new PolicyDocument(
                        "111-003",
                        LocalDate.of(2019, 2, 1),
                        LocalDate.of(2020, 2, 28),
                        "John Smith",
                        "SAFE_CAR",
                        BigDecimal.valueOf(1000),
                        "m.smith"),
                new PolicyDocument(
                        "111-004",
                        LocalDate.of(2019, 3, 1),
                        LocalDate.of(2020, 3, 31),
                        "John Smith",
                        "SAFE_CAR",
                        BigDecimal.valueOf(1000),
                        "m.smith"),
                new PolicyDocument(
                        "111-005",
                        LocalDate.of(2019, 4, 1),
                        LocalDate.of(2020, 4, 30),
                        "John Smith",
                        "SAFE_FARM",
                        BigDecimal.valueOf(1000),
                        "m.smith")
        );

        PolicyElasticRepository repository = policyElasticRepository();

        docs.forEach(d -> repository.save(d));
    }

    @Test
    public void canFindTotal() {
        TotalSalesQuery.Result total = policyElasticRepository().getTotalSales(TotalSalesQuery.builder()
                .filterByProductCode(null)
                .filterBySalesDate(LocalDateRange.between(LocalDate.of(2019,1,1),LocalDate.of(2019,12,31)))
                .build()
        );

        assertEquals(Long.valueOf(5L), total.getTotal().getPoliciesCount());
        assertEquals(new BigDecimal("5000.00"), total.getTotal().getPremiumAmount());

        assertEquals(Long.valueOf(1L), total.getPerProductTotal().get("SAFE_FARM").getPoliciesCount());
        assertEquals(new BigDecimal("1000.0"), total.getPerProductTotal().get("SAFE_FARM").getPremiumAmount());
    }

    @Test
    public void canFindTotalFilteredByProduct() {
        TotalSalesQuery.Result total = policyElasticRepository().getTotalSales(TotalSalesQuery.builder()
                .filterByProductCode("SAFE_FARM")
                .filterBySalesDate(LocalDateRange.between(LocalDate.of(2019,1,1),LocalDate.of(2019,12,31)))
                .build()
        );

        assertEquals(Long.valueOf(1L), total.getTotal().getPoliciesCount());
        assertEquals(new BigDecimal("1000.00"), total.getTotal().getPremiumAmount());

        assertEquals(Long.valueOf(1L), total.getPerProductTotal().get("SAFE_FARM").getPoliciesCount());
        assertEquals(new BigDecimal("1000.0"), total.getPerProductTotal().get("SAFE_FARM").getPremiumAmount());
    }

    @Test
    public void canFindTotalFilteredBySalesDates() {
        TotalSalesQuery.Result total = policyElasticRepository().getTotalSales(TotalSalesQuery.builder()
                .filterByProductCode(null)
                .filterBySalesDate(LocalDateRange.between(LocalDate.of(2019,1,1),LocalDate.of(2019,2,28)))
                .build()
        );

        assertEquals(Long.valueOf(3L), total.getTotal().getPoliciesCount());
        assertEquals(new BigDecimal("3000.00"), total.getTotal().getPremiumAmount());

        assertEquals(Long.valueOf(2L), total.getPerProductTotal().get("SAFE_HOUSE").getPoliciesCount());
        assertEquals(new BigDecimal("2000.0"), total.getPerProductTotal().get("SAFE_HOUSE").getPremiumAmount());
    }
}
