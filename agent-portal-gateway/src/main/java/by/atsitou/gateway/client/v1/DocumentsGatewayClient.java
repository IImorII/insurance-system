package by.atsitou.gateway.client.v1;

import io.micronaut.http.client.annotation.Client;
import io.micronaut.retry.annotation.Retryable;
import by.atsitou.documents.api.DocumentsOperations;
import by.atsitou.documents.api.queries.finddocuments.FindDocumentsResult;

@Client(id = "documents-service", path = "/documents")
@Retryable(attempts = "2", delay = "2s")
public interface DocumentsGatewayClient extends DocumentsOperations {

    @Override
    FindDocumentsResult find(String policyNumber);
}
