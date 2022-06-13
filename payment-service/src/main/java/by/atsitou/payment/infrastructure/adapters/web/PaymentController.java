package by.atsitou.payment.infrastructure.adapters.web;

import by.atsitou.payment.domain.PolicyAccountRepository;
import by.atsitou.payment.service.api.v1.PolicyAccountBalanceDto;
import by.atsitou.payment.service.api.v1.PolicyAccountDto;
import by.atsitou.payment.service.api.v1.exceptions.PolicyAccountNotFound;
import by.atsitou.payment.service.api.v1.operations.PaymentOperations;

import java.time.LocalDate;
import java.util.Collection;

import io.micronaut.configuration.hystrix.annotation.HystrixCommand;
import io.micronaut.http.annotation.Controller;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import lombok.RequiredArgsConstructor;

@Controller("/payment")
@RequiredArgsConstructor
public class PaymentController implements PaymentOperations {

    private final PolicyAccountRepository policyAccountRepository;

    @Override
    @HystrixCommand
    @ExecuteOn(TaskExecutors.IO)
    public Collection<PolicyAccountDto> accounts() {
        return policyAccountRepository.findAll();
    }

    @Override
    @HystrixCommand
    @ExecuteOn(TaskExecutors.IO)
    public PolicyAccountBalanceDto accountBalance(String accountNumber) {
        return policyAccountRepository.findByPolicyAccountNumber(accountNumber)
                .map(account -> new PolicyAccountBalanceDto(
                        account.getPolicyNumber(),
                        account.getPolicyAccountNumber(),
                        account.balanceAt(LocalDate.now()),
                        account.getCreated(),
                        account.getUpdated()))
                .orElseThrow(() -> new PolicyAccountNotFound(accountNumber));
    }
}
