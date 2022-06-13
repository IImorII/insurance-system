package by.atsitou.policy.search.readmodel;

import io.micronaut.configuration.kafka.annotation.KafkaListener;
import io.micronaut.configuration.kafka.annotation.OffsetReset;
import io.micronaut.configuration.kafka.annotation.Topic;
import by.atsitou.policy.service.api.v1.events.PolicyTerminatedEvent;

@KafkaListener(clientId = "policy-terminated-listener", offsetReset = OffsetReset.EARLIEST)
public class PolicyTerminatedListener extends AbstractPolicyListener {

    @Topic("policy-terminated")
    void onPolicyTerminated(PolicyTerminatedEvent event) {
        saveMappedPolicy(event.getPolicy());
    }
}
