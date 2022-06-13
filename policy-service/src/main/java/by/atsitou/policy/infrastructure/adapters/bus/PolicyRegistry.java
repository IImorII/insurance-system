package by.atsitou.policy.infrastructure.adapters.bus;

import io.micronaut.context.ApplicationContext;
import by.atsitou.command.bus.Registry;

import javax.inject.Singleton;

@Singleton
public class PolicyRegistry extends Registry {
    public PolicyRegistry(ApplicationContext applicationContext) {
        super(applicationContext);
    }
}
