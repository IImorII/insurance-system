package by.atsitou.dashboard.elastic;

import by.atsitou.dashboard.domain.PolicyDocument;
import by.atsitou.dashboard.infrastructure.adapters.elastic.PolicyElasticRepository;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.jupiter.api.Test;

import by.atsitou.dashboard.infrastructure.adapters.elastic.config.JsonConverter;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;


public class PolicyElasticRepositoryTest extends EmbeddedElasticTest {

    @Test
    public void canIndexPolicy() {
        PolicyDocument policyDocument = new PolicyDocument(
                "111-111",
                LocalDate.of(2018, 1, 1),
                LocalDate.of(2018, 12, 31),
                "John Smith",
                "SAFE_HOUSE",
                BigDecimal.valueOf(1000),
                "m.smith"
        );

        PolicyElasticRepository repository = new PolicyElasticRepository(
                new RestHighLevelClient(RestClient.builder(new HttpHost("localhost", el.getHttpPort(), "http"))),
                new JsonConverter(objectMapper())
        );

        repository.save(policyDocument);

        PolicyDocument saved = repository.findByNumber("111-111");

        assertNotNull(saved);
    }
}
