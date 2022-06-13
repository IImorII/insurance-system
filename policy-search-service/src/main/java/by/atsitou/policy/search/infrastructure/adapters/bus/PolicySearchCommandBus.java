package by.atsitou.policy.search.infrastructure.adapters.bus;

import by.atsitou.command.bus.MicronautCommandBus;
import by.atsitou.command.bus.Registry;

import javax.inject.Singleton;

@Singleton
public class PolicySearchCommandBus extends MicronautCommandBus {
    public PolicySearchCommandBus(Registry registry) {
        super(registry);
    }
}
