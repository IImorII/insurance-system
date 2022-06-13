package by.atsitou.gateway;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import by.atsitou.gateway.client.v1.PolicyGatewayClient;
import by.atsitou.policy.service.api.v1.commands.createoffer.CreateOfferCommand;
import by.atsitou.policy.service.api.v1.commands.createoffer.CreateOfferResult;

import javax.inject.Inject;

@Secured(SecurityRule.IS_AUTHENTICATED)
@Controller("/api/offers")
public class OfferGatewayController {

    @Inject
    private PolicyGatewayClient client;

    @Post(value = "/", consumes = "application/json")
    CreateOfferResult create(CreateOfferCommand cmd) {
        return client.createOffer(cmd);
    }

}
