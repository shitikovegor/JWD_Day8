package com.shitikov.task8.controller;

import com.shitikov.task8.controller.command.Command;
import com.shitikov.task8.controller.command.provider.CommandProvider;
import com.shitikov.task8.model.entity.Book;

import java.util.List;
import java.util.Map;

public class LibraryController {
    private static LibraryController instance;

    public static LibraryController getInstance() {
        if (instance == null) {
            instance = new LibraryController();
        }
        return instance;
    }

    public Map<String, List<Book>> processRequest(String commandName, Map<String, String> parameters) {
        Map<String, List<Book>> response;

        Command command = CommandProvider.defineCommand(commandName);

        response = command.execute(parameters);

        return response;
    }
}
