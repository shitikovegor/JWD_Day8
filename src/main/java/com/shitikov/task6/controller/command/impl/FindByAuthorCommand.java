package com.shitikov.task6.controller.command.impl;

import com.shitikov.task6.controller.command.Command;
import com.shitikov.task6.controller.command.type.CommandResponse;
import com.shitikov.task6.controller.command.type.KeyType;
import com.shitikov.task6.model.entity.Book;
import com.shitikov.task6.service.LibraryService;
import com.shitikov.task6.service.impl.LibraryServiceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindByAuthorCommand implements Command {

    @Override
    public Map<String, List<Book>> execute(Map<String, String> parameters) {
        LibraryService libraryService = new LibraryServiceImpl();
        Map<String, List<Book>> response = new HashMap<>();

        if (parameters.containsKey(KeyType.AUTHOR.getName())) {
            String author = parameters.get("author");
            List<Book> foundBooks = libraryService.findByAuthor(author);
            response.put(CommandResponse.FOUND_RESPONSE.getMessage(), foundBooks);
        }
        return response;
    }
}
