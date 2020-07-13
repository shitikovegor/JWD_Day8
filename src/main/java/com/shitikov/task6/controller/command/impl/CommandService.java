package com.shitikov.task6.controller.command.impl;

import com.shitikov.task6.controller.exception.ControllerException;

import java.util.ArrayList;
import java.util.List;

public class CommandService {
    private static final String DELIMITER = ",";
    private static CommandService instance;

    private CommandService() {
    }

    public static CommandService getInstance() {
        if (instance == null) {
            instance = new CommandService();
        }
        return instance;
    }

    List<String> parseAuthors(String authors) throws ControllerException {
        if (authors == null) {
            throw new ControllerException("List of authors is null.");
        }

        List<String> result = new ArrayList<>();
        String[] authorsForFill = authors.split(DELIMITER);

        for (String author : authorsForFill) {
            result.add(author.strip());
        }

        return result;
    }

    int parsePages(String pages) throws ControllerException {
        try {
            return Integer.parseInt(pages);
        } catch (NumberFormatException e) {
            throw new ControllerException("Parameter is incorrect.");
        }
    }
}
