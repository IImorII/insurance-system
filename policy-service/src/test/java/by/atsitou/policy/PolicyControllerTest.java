package by.atsitou.policy;

import by.atsitou.policy.domain.Offer;
import by.atsitou.policy.domain.OfferRepository;
import by.atsitou.policy.domain.OfferStatus;
import by.atsitou.policy.service.api.v1.commands.createpolicy.CreatePolicyCommand;
import by.atsitou.policy.service.api.v1.commands.createpolicy.CreatePolicyResult;
import by.atsitou.policy.service.api.v1.commands.createpolicy.dto.PersonDto;
import by.atsitou.policy.service.api.v1.queries.getpolicydetails.GetPolicyDetailsQueryResult;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import io.micronaut.context.ApplicationContext;
import io.micronaut.runtime.server.EmbeddedServer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PolicyControllerTest {

    private static EmbeddedServer server;
    private static PolicyTestClient client;

    @BeforeAll
    public static void setup() {
        server = ApplicationContext.run(EmbeddedServer.class);
        client = server.getApplicationContext().createBean(PolicyTestClient.class, server.getURL());
    }

    @Test
    public void testGetPolicyByNumber() {
        String policyNumber = "1234";
        GetPolicyDetailsQueryResult policy = client.get(policyNumber);

        assertNotNull(policy);
        assertNotNull(policy.getPolicy());
        Assertions.assertEquals(policyNumber, policy.getPolicy().getNumber());
    }

    @Test
    public void testCreatePolicy() {
        //given: offer with number 111 exists
        Map<String, BigDecimal> coverPrices = new HashMap<>();
        coverPrices.put("C1", new BigDecimal("100"));
        coverPrices.put("C2", new BigDecimal("99"));
        Offer offer111 = new Offer(
                null,
                "111",
                "TRI",
                LocalDate.of(2018, 8, 1),
                LocalDate.of(2018, 8, 10),
                new HashMap<>(),
                new BigDecimal("199"),
                coverPrices,
                OfferStatus.NEW,
                LocalDate.now()
        );
        server.getApplicationContext().getBean(OfferRepository.class).save(offer111);

        //when policy creation is requested
        CreatePolicyCommand cmd = new CreatePolicyCommand(
                "111",
                new PersonDto("Timmy", "Lamb", "111111111116"),
                "admin");

        CreatePolicyResult result = client.create(cmd);

        //then policy is created and number is assigned
        assertNotNull(result);
        assertNotNull(result.getPolicyNumber());
    }

    @AfterAll
    public static void cleanup() {
        if (server != null)
            server.stop();

    }
}
