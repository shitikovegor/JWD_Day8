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

public class SortByIdCommand implements Command {

    @Override
    public Map<String, List<Book>> execute(Map<String, String> parameters) {
        LibraryService libraryService = new LibraryServiceImpl();
        Map<String, List<Book>> response = new HashMap<>();

        List<Book> sortedBooks = libraryService.sortByKey(KeyType.ID);
        response.put(CommandResponse.SORT_RESPONSE.getMessage()
                .concat(KeyType.ID.getName()), sortedBooks);
        return response;
    }
}
