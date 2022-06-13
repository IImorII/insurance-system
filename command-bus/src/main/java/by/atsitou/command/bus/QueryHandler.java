package by.atsitou.command.bus;

import by.atsitou.command.bus.api.Query;

public interface QueryHandler<R, C extends Query<R>> {
    R handle(C var1);
}
