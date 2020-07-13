package com.shitikov.task6.controller.command.impl;

import com.shitikov.task6.controller.command.Command;
import com.shitikov.task6.controller.exception.ControllerException;
import com.shitikov.task6.model.entity.Book;
import com.shitikov.task6.service.LibraryService;
import com.shitikov.task6.service.exception.LibraryServiceException;
import com.shitikov.task6.service.impl.LibraryServiceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddBookCommand implements Command {
    private static final String GOOD_RESPONSE = "Book added to library";
    private static final String BAD_RESPONSE = "Something went wrong. ";

    @Override
    public Map<String, List<Book>> execute(Map<String, String> parameters) {
        LibraryService libraryService = new LibraryServiceImpl();
        Map<String, List<Book>> response = new HashMap<>();

        try {
            String name = parameters.get("name");
            List<String> authors = CommandService.getInstance().parseAuthors(parameters.get("authors"));
            String publishingHouse = parameters.get("publishingHouse");
            int pages = CommandService.getInstance().parsePages(parameters.get("pages"));

            libraryService.addBook(name, authors, publishingHouse, pages);
            response.put(GOOD_RESPONSE, null);
        } catch (ControllerException | LibraryServiceException ex) {
            response.put(BAD_RESPONSE.concat(ex.getMessage()), null);
        }

        return response;
    }
}
