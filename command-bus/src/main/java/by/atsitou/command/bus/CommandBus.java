package by.atsitou.command.bus;

import by.atsitou.command.bus.api.Command;
import by.atsitou.command.bus.api.Query;

public interface CommandBus {
    <R, C extends Command<R>> R executeCommand(C command);

    <R, Q extends Query<R>> R executeQuery(Q query);
}
