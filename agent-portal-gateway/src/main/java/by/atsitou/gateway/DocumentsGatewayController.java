package by.atsitou.gateway;

import io.micronaut.security.rules.SecurityRule;
import by.atsitou.documents.api.queries.finddocuments.FindDocumentsResult;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.security.annotation.Secured;
import by.atsitou.gateway.client.v1.DocumentsGatewayClient;

import javax.inject.Inject;

@Secured(SecurityRule.IS_AUTHENTICATED)
@Controller("/api/documents")
public class DocumentsGatewayController {

    @Inject
    private DocumentsGatewayClient client;

    @Get("/{policyNumber}")
    FindDocumentsResult find(String policyNumber) {
        return client.find(policyNumber);
    }
}

