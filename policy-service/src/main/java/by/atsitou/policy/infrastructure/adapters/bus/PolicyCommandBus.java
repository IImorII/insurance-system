package by.atsitou.policy.infrastructure.adapters.bus;

import by.atsitou.command.bus.MicronautCommandBus;
import by.atsitou.command.bus.Registry;

import javax.inject.Singleton;

@Singleton
public class PolicyCommandBus extends MicronautCommandBus {
    public PolicyCommandBus(Registry registry) {
        super(registry);
    }
}
