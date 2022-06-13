package by.atsitou.policy.commands;

import by.atsitou.policy.domain.PolicyRepository;
import by.atsitou.policy.service.api.v1.commands.terminatepolicy.TerminatePolicyCommand;
import by.atsitou.policy.service.api.v1.commands.terminatepolicy.TerminatePolicyResult;
import by.atsitou.policy.service.api.v1.events.PolicyTerminatedEvent;
import by.atsitou.policy.service.api.v1.events.dto.PolicyDto;
import by.atsitou.policy.shared.exceptions.BusinessException;
import lombok.RequiredArgsConstructor;
import by.atsitou.command.bus.CommandHandler;
import by.atsitou.policy.domain.Policy;
import by.atsitou.policy.infrastructure.adapters.kafka.EventPublisher;

import javax.inject.Singleton;
import java.time.LocalDate;
import java.util.Optional;

@Singleton
@RequiredArgsConstructor
public class TerminatePolicyHandler implements CommandHandler<TerminatePolicyResult, TerminatePolicyCommand> {

    private final PolicyRepository policyRepository;
    private final EventPublisher eventPublisher;

    @Override
    public TerminatePolicyResult handle(TerminatePolicyCommand cmd) {
        Optional<Policy> policyOpt = policyRepository.findByNumber(cmd.getPolicyNumber());
        if (!policyOpt.isPresent()) {
            throw new BusinessException("POLICY NOT FOUND");
        }
        Policy policy = policyOpt.get();
        policy.terminate(LocalDate.now());
        policyRepository.save(policy);
        eventPublisher.policyTerminatedEvent(policy.getNumber(), createEvent(policy));
        return TerminatePolicyResult.success(policy.getNumber());
    }

    private PolicyTerminatedEvent createEvent(Policy policy) {
        return new PolicyTerminatedEvent(new PolicyDto(
                policy.getNumber(),
                policy.versions().lastVersion().getVersionValidityPeriod().getFrom(),
                policy.versions().lastVersion().getVersionValidityPeriod().getTo(),
                policy.versions().lastVersion().getPolicyHolder().getFullName(),
                policy.versions().lastVersion().getProductCode(),
                policy.versions().lastVersion().getTotalPremiumAmount(),
                null
        ));
    }
}
