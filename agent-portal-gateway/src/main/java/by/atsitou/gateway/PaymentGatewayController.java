package by.atsitou.gateway;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import by.atsitou.gateway.client.v1.PaymentGatewayClient;
import by.atsitou.payment.service.api.v1.PolicyAccountDto;

import javax.inject.Inject;
import java.util.Collection;

@Controller("/api/payments")
public class PaymentGatewayController {

    @Inject
    private PaymentGatewayClient paymentClient;

    @Get("/accounts")
    Collection<PolicyAccountDto> accounts() {
        return paymentClient.accounts();
    }
}
