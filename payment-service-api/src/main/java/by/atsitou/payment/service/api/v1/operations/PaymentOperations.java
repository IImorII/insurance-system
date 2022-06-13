package by.atsitou.payment.service.api.v1.operations;

import io.micronaut.http.annotation.Get;
import by.atsitou.payment.service.api.v1.PolicyAccountDto;

import java.util.Collection;
import by.atsitou.payment.service.api.v1.PolicyAccountBalanceDto;

public interface PaymentOperations {

    @Get("/accounts")
    Collection<PolicyAccountDto> accounts();
    
    @Get("/accounts/{accountNumber}")
    PolicyAccountBalanceDto accountBalance(String accountNumber);
}
