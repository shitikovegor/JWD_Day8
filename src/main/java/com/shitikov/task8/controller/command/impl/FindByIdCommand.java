package com.shitikov.task8.controller.command.impl;

import com.shitikov.task8.controller.command.Command;
import com.shitikov.task8.controller.command.type.CommandResponse;
import com.shitikov.task8.util.KeyType;
import com.shitikov.task8.model.entity.Book;
import com.shitikov.task8.model.service.LibraryService;
import com.shitikov.task8.model.service.impl.LibraryServiceImpl;

import java.util.*;

public class FindByIdCommand implements Command {

    @Override
    public Map<String, List<Book>> execute(Map<String, String> parameters) {
        LibraryService libraryService = new LibraryServiceImpl();
        Map<String, List<Book>> response = new HashMap<>();

        if (parameters.containsKey(KeyType.ID.getName())) {
            String id = parameters.get("id");
            Optional<Book> foundBook = libraryService.findById(id);

            if (foundBook.isPresent()) {
                List<Book> foundBooks = new ArrayList<>();
                foundBooks.add(foundBook.get());
                response.put(CommandResponse.FOUND_RESPONSE.getMessage(), foundBooks);
            } else {
                response.put(CommandResponse.BOOK_NOT_FOUND_RESPONSE.getMessage(), new ArrayList<>());
            }
        }
        return response;
    }
}
