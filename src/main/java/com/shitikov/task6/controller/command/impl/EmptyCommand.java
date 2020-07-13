package com.shitikov.task6.controller.command.impl;

import com.shitikov.task6.controller.command.Command;
import com.shitikov.task6.model.entity.Book;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmptyCommand implements Command {
    private static final String MESSAGE = "Something went wrong.";

    @Override
    public Map<String, List<Book>> execute(Map<String, String> parameters) {
        Map<String, List<Book>> response = new HashMap<>();
        response.put(MESSAGE, null);

        return response;
    }
}
