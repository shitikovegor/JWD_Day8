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

public class FindByPagesCommand implements Command {
    private static final String GOOD_RESPONSE = "Books found";
    private static final String BAD_RESPONSE = "Something went wrong. ";

    @Override
    public Map<String, List<Book>> execute(Map<String, String> parameters) {
        LibraryService libraryService = new LibraryServiceImpl();
        Map<String, List<Book>> response = new HashMap<>();

        try {
            int pages = CommandService.getInstance().parsePages(parameters.get("pages"));
            List<Book> foundBooks = libraryService.findByPages(pages);
            response.put(GOOD_RESPONSE, foundBooks);
        } catch (ControllerException | LibraryServiceException ex) {
            response.put(BAD_RESPONSE.concat(ex.getMessage()), null);
        }

        return response;
    }
}
