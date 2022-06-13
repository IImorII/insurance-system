package by.atsitou.gateway.client.v1;

import io.micronaut.http.client.annotation.Client;
import io.micronaut.retry.annotation.Retryable;
import by.atsitou.policy.search.service.api.v1.PolicySearchOperations;

@Client(id = "policy-search-service", path = "/policies")
@Retryable(attempts = "2", delay = "2s")
public interface PolicySearchGatewayClient extends PolicySearchOperations {
}
