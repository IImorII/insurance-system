package by.atsitou.command.bus;

import by.atsitou.command.bus.api.Command;

public interface CommandHandler<R, C extends Command<R>> {
    R handle(C command);
}
