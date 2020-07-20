package com.shitikov.task8.controller.command.provider;

import com.shitikov.task8.controller.command.Command;
import com.shitikov.task8.controller.command.type.CommandType;

import java.util.Arrays;
import java.util.Optional;

public final class CommandProvider {

    private CommandProvider() {
    }

    public static Command defineCommand(String commandName) {
        Command definedCommand;

        Optional<Command> command = Arrays.stream(CommandType.values())
                .filter(commandType -> commandType.getName().equalsIgnoreCase(commandName))
                .findFirst().map(CommandType::getCommand);

        definedCommand = command.orElseGet(CommandType.EMPTY_COMMAND::getCommand);

        return definedCommand;
    }
}
