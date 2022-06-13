package by.atsitou.policy.commands;

import by.atsitou.command.bus.CommandHandler;
import by.atsitou.policy.service.api.v1.commands.createpolicy.CreatePolicyCommand;
import by.atsitou.policy.service.api.v1.commands.createpolicy.CreatePolicyResult;
import by.atsitou.policy.service.api.v1.events.PolicyRegisteredEvent;
import by.atsitou.policy.service.api.v1.events.dto.PolicyDto;
import by.atsitou.policy.domain.AgentRef;
import by.atsitou.policy.domain.Offer;
import by.atsitou.policy.domain.OfferRepository;
import by.atsitou.policy.domain.Person;
import by.atsitou.policy.domain.Policy;
import by.atsitou.policy.domain.PolicyFactory;
import by.atsitou.policy.domain.PolicyRepository;
import by.atsitou.policy.infrastructure.adapters.kafka.EventPublisher;

import java.time.LocalDate;

import javax.inject.Singleton;
import javax.transaction.Transactional;

import lombok.RequiredArgsConstructor;

@Singleton
@RequiredArgsConstructor
public class CreatePolicyHandler implements CommandHandler<CreatePolicyResult, CreatePolicyCommand> {

    private final PolicyRepository policyRepository;
    private final OfferRepository offerRepository;
    private final PolicyFactory policyFactory = new PolicyFactory();
    private final EventPublisher eventPublisher;

    @Transactional
    @Override
    public CreatePolicyResult handle(CreatePolicyCommand cmd) {
        Offer offer = offerRepository.getByNumber(cmd.getOfferNumber());
        if (offer.isExpired(LocalDate.now())) {
            throw new RuntimeException("Offer has expired");
        }
        Person policyHolder = new Person(cmd.getPolicyHolder().getFirstName(), cmd.getPolicyHolder().getLastName(), cmd.getPolicyHolder().getTaxId());
        AgentRef agent = AgentRef.of(cmd.getAgentLogin());
        Policy policy = policyFactory.fromOffer(offer, policyHolder, agent);
        policyRepository.save(policy);
        eventPublisher.policyRegisteredEvent(policy.getNumber(), createEvent(policy));
        return new CreatePolicyResult(policy.getNumber());
    }

    private PolicyRegisteredEvent createEvent(Policy policy) {
        return new PolicyRegisteredEvent(
                new PolicyDto(
                        policy.getNumber(),
                        policy.versions().lastVersion().getVersionValidityPeriod().getFrom(),
                        policy.versions().lastVersion().getVersionValidityPeriod().getTo(),
                        policy.versions().lastVersion().getPolicyHolder().getFullName(),
                        policy.versions().lastVersion().getProductCode(),
                        policy.versions().lastVersion().getTotalPremiumAmount(),
                        null
                )
        );
    }
}
