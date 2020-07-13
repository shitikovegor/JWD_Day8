package com.shitikov.task6.controller.command.impl;

import com.shitikov.task6.controller.command.Command;
import com.shitikov.task6.model.entity.Book;
import com.shitikov.task6.service.LibraryService;
import com.shitikov.task6.service.exception.LibraryServiceException;
import com.shitikov.task6.service.impl.LibraryServiceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindByNameCommand implements Command {
    private static final String GOOD_RESPONSE = "Books found";
    private static final String BAD_RESPONSE = "Something went wrong. ";

    @Override
    public Map<String, List<Book>> execute(Map<String, String> parameters) {
        LibraryService libraryService = new LibraryServiceImpl();
        Map<String, List<Book>> response = new HashMap<>();

        try {
            String name = parameters.get("name");
            List<Book> foundBooks = libraryService.findByName(name);
            response.put(GOOD_RESPONSE, foundBooks);
        } catch (LibraryServiceException lse) {
            response.put(BAD_RESPONSE.concat(lse.getMessage()), null);
        }

        return response;
    }
}
