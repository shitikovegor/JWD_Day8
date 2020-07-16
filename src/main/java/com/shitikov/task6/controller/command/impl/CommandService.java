package com.shitikov.task6.controller.command.impl;

import com.shitikov.task6.controller.command.exception.CommandException;

public class CommandService {
    static final String BAD_RESPONSE = "Something went wrong. ";
    static final String FOUND_RESPONSE = "Books found";

    private static CommandService instance;

    private CommandService() {
    }

    public static CommandService getInstance() {
        if (instance == null) {
            instance = new CommandService();
        }
        return instance;
    }

    int parsePages(String pages) throws CommandException {
        try {
            return Integer.parseInt(pages);
        } catch (NumberFormatException e) {
            throw new CommandException("Parameter is incorrect.");
        }
    }
}
