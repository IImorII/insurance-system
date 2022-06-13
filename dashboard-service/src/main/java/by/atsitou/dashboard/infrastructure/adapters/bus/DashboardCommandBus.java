package by.atsitou.dashboard.infrastructure.adapters.bus;

import by.atsitou.command.bus.MicronautCommandBus;
import by.atsitou.command.bus.Registry;

import javax.inject.Singleton;

@Singleton
public class DashboardCommandBus extends MicronautCommandBus {
    public DashboardCommandBus(Registry registry) {
        super(registry);
    }
}
