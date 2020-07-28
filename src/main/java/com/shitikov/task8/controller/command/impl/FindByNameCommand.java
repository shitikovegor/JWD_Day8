package com.shitikov.task8.controller.command.impl;

import com.shitikov.task8.controller.command.Command;
import com.shitikov.task8.controller.command.type.CommandResponse;
import com.shitikov.task8.model.entity.Book;
import com.shitikov.task8.model.service.LibraryService;
import com.shitikov.task8.model.service.impl.LibraryServiceImpl;
import com.shitikov.task8.model.entity.KeyType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindByNameCommand implements Command {

    @Override
    public Map<String, List<Book>> execute(Map<String, String> parameters) {
        LibraryService libraryService = new LibraryServiceImpl();
        Map<String, List<Book>> response = new HashMap<>();

        if (parameters.containsKey(KeyType.NAME.getName())) {
            String name = parameters.get("name");
            List<Book> foundBooks = libraryService.findByKey(KeyType.NAME, name);
            response.put(CommandResponse.FOUND_RESPONSE.getMessage(), foundBooks);
        }
        return response;
    }
}
