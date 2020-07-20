package com.shitikov.task6.controller.command.impl;

import com.shitikov.task6.controller.command.Command;
import com.shitikov.task6.controller.command.type.CommandResponse;
import com.shitikov.task6.model.entity.Book;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmptyCommand implements Command {

    @Override
    public Map<String, List<Book>> execute(Map<String, String> parameters) {
        Map<String, List<Book>> response = new HashMap<>();
        response.put(CommandResponse.BAD_RESPONSE.getMessage(), new ArrayList<>());

        return response;
    }
}
