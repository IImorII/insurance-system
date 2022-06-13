package by.atsitou.policy.infrastructure.adapters.kafka;

import by.atsitou.policy.service.api.v1.events.PolicyRegisteredEvent;
import by.atsitou.policy.service.api.v1.events.PolicyTerminatedEvent;
import io.micronaut.configuration.kafka.annotation.KafkaClient;
import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.Topic;

@KafkaClient
public interface EventPublisher {

    @Topic("policy-registered")
    void policyRegisteredEvent(@KafkaKey String policyNumber, PolicyRegisteredEvent event);

    @Topic("policy-terminated")
    void policyTerminatedEvent(@KafkaKey String policyNumber, PolicyTerminatedEvent event);
}
