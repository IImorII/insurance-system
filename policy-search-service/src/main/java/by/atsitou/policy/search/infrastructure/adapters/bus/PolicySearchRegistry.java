package by.atsitou.policy.search.infrastructure.adapters.bus;

import io.micronaut.context.ApplicationContext;
import by.atsitou.command.bus.Registry;

import javax.inject.Singleton;

@Singleton
public class PolicySearchRegistry extends Registry {
    public PolicySearchRegistry(ApplicationContext applicationContext) {
        super(applicationContext);
    }
}
