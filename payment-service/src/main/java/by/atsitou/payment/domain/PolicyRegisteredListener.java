package by.atsitou.payment.domain;

import by.atsitou.policy.service.api.v1.events.PolicyRegisteredEvent;
import by.atsitou.policy.service.api.v1.events.dto.PolicyDto;

import java.util.Optional;

import io.micronaut.configuration.kafka.annotation.KafkaListener;
import io.micronaut.configuration.kafka.annotation.OffsetReset;
import io.micronaut.configuration.kafka.annotation.Topic;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@KafkaListener(offsetReset = OffsetReset.EARLIEST)
public class PolicyRegisteredListener {

    private final PolicyAccountRepository policyAccountRepository;
    private final PolicyAccountNumberGenerator policyAccountNumberGenerator;

    @Topic("policy-registered")
    void onPolicyRegistered(PolicyRegisteredEvent event) {
        Optional<PolicyAccount> accountOpt = policyAccountRepository.findByPolicyNumber(event.getPolicy().getNumber());

        if (!accountOpt.isPresent())
            createAccount(event.getPolicy());
    }

    private void createAccount(PolicyDto policy) {
        PolicyAccount newAccount = new PolicyAccount(policy.getNumber(), policyAccountNumberGenerator.generate());
        newAccount.expectedPayment(policy.getTotalPremium(),policy.getFrom());
        policyAccountRepository.save(newAccount);
    }

}
