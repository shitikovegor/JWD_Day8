package com.shitikov.task8.controller.command.impl;

import com.shitikov.task8.controller.command.Command;
import com.shitikov.task8.controller.command.type.CommandResponse;
import com.shitikov.task8.model.entity.Book;
import com.shitikov.task8.model.service.LibraryService;
import com.shitikov.task8.model.exception.LibraryServiceException;
import com.shitikov.task8.model.service.impl.LibraryServiceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SortByPublishingHouseCommand implements Command {
    private static final String SORT_TYPE = "by publishing house";

    @Override
    public Map<String, List<Book>> execute(Map<String, String> parameters) {
        LibraryService libraryService = new LibraryServiceImpl();
        Map<String, List<Book>> response = new HashMap<>();

        List<Book> sortedBooks = null;
        try {
            sortedBooks = libraryService.sortByPublishingHouse();
            response.put(CommandResponse.SORT_RESPONSE.getMessage().concat(SORT_TYPE), sortedBooks);
        } catch (LibraryServiceException e) {
            response.put(CommandResponse.SORT_RESPONSE.getMessage().concat(SORT_TYPE), sortedBooks);
        }
        return response;
    }
}
