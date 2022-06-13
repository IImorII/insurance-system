package by.atsitou.dashboard.infrastructure.adapters.bus;

import io.micronaut.context.ApplicationContext;
import by.atsitou.command.bus.Registry;

import javax.inject.Singleton;

@Singleton
public class DashboardRegistry extends Registry {
    public DashboardRegistry(ApplicationContext applicationContext) {
        super(applicationContext);
    }
}
