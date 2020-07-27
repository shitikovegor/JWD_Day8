package com.shitikov.task8.controller.command.impl;

import com.shitikov.task8.controller.command.Command;
import com.shitikov.task8.controller.command.type.CommandResponse;
import com.shitikov.task8.model.entity.Book;
import com.shitikov.task8.model.service.LibraryService;
import com.shitikov.task8.model.exception.LibraryServiceException;
import com.shitikov.task8.model.service.impl.LibraryServiceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SortByAuthorCommand implements Command {
    private static final String SORT_TYPE = "by author";

    @Override
    public Map<String, List<Book>> execute(Map<String, String> parameters) {
        LibraryService libraryService = new LibraryServiceImpl();
        Map<String, List<Book>> response = new HashMap<>();

        try {
            List<Book> sortedBooks = libraryService.sortByAuthor();
            response.put(CommandResponse.SORT_RESPONSE.getMessage().concat(SORT_TYPE), sortedBooks);
        } catch (LibraryServiceException ex) {
            response.put(CommandResponse.BAD_RESPONSE.getMessage().concat(ex.getMessage()), new ArrayList<>());
        }


        return response;
    }
}
