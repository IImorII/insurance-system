package by.atsitou.policy.service.api.v1.commands.createpolicy;

import by.atsitou.command.bus.api.Command;
import by.atsitou.policy.service.api.v1.commands.createpolicy.dto.PersonDto;

import io.micronaut.core.annotation.Introspected;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Introspected
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreatePolicyCommand implements Command<CreatePolicyResult> {
    private String offerNumber;
    private PersonDto policyHolder;
    private String agentLogin;
}
