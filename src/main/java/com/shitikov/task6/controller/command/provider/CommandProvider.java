package com.shitikov.task6.controller.command.provider;

import com.shitikov.task6.controller.command.Command;
import com.shitikov.task6.controller.command.commandType.CommandType;
import com.shitikov.task6.controller.command.impl.EmptyCommand;

import java.util.Arrays;
import java.util.Optional;

public final class CommandProvider {

    private CommandProvider() {
    }

    public static Command defineCommand(String commandName) {

        Optional<Command> command = Arrays.stream(CommandType.values())
                .filter(commandType -> commandType.getName().equalsIgnoreCase(commandName))
                .findFirst().map(CommandType::getCommand);

        if (command.isPresent()) {
            return command.get();
        }
        return new EmptyCommand();
    }
}
