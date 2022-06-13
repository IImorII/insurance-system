package by.atsitou.gateway.client.v1.fallback;

import by.atsitou.gateway.client.v1.PaymentGatewayClient;
import io.micronaut.retry.annotation.Fallback;
import by.atsitou.payment.service.api.v1.PolicyAccountBalanceDto;
import by.atsitou.payment.service.api.v1.PolicyAccountDto;

import javax.inject.Singleton;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

@Singleton
@Fallback
public class PaymentGatewayClientFallback implements PaymentGatewayClient {
    @Override
    public Collection<PolicyAccountDto> accounts() {
        return Collections.emptyList();
    }

    @Override
    public PolicyAccountBalanceDto accountBalance(String accountNumber) {
        return new PolicyAccountBalanceDto(accountNumber, null, null, new Date(), new Date());
    }
}
