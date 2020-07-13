package com.shitikov.task6.controller;

import com.shitikov.task6.controller.command.Command;
import com.shitikov.task6.controller.command.provider.CommandProvider;
import com.shitikov.task6.model.entity.Book;

import java.util.List;
import java.util.Map;

public class LibraryController {

    public Map<String, List<Book>> processRequest(String commandName, Map<String, String> parameters) {
        Map<String, List<Book>> response;

        Command command = CommandProvider.defineCommand(commandName);

        response = command.execute(parameters);

        return response;
    }
}
