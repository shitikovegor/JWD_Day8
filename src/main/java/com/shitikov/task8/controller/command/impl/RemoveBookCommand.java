package com.shitikov.task8.controller.command.impl;

import com.shitikov.task8.controller.command.Command;
import com.shitikov.task8.controller.command.type.CommandResponse;
import com.shitikov.task8.controller.command.type.KeyType;
import com.shitikov.task8.model.entity.Book;
import com.shitikov.task8.service.LibraryService;
import com.shitikov.task8.service.exception.LibraryServiceException;
import com.shitikov.task8.service.impl.LibraryServiceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RemoveBookCommand implements Command {

    @Override
    public Map<String, List<Book>> execute(Map<String, String> parameters) {
        LibraryService libraryService = new LibraryServiceImpl();
        Map<String, List<Book>> response = new HashMap<>();

        if (parameters.containsKey(KeyType.NAME.getName())
                && parameters.containsKey(KeyType.AUTHOR.getName())
                && parameters.containsKey(KeyType.PUBLISHING_HOUSE.getName())
                && parameters.containsKey(KeyType.PAGES.getName())) {

            String name = parameters.get("name");
            String authors = parameters.get("author");
            String publishingHouse = parameters.get("publishingHouse");
            String pages = parameters.get("pages");

            try {
                libraryService.remove(name, authors, publishingHouse, pages);
                response.put(CommandResponse.REMOVE_RESPONSE.getMessage(),
                        libraryService.findAll());
            } catch (LibraryServiceException lse) {
                if (lse.getMessage().equals("Book doesn't exist in library.")) {
                    response.put(CommandResponse.NOT_EXIST_RESPONSE.getMessage(),
                            libraryService.findAll());
                } else {
                    response.put(CommandResponse.BAD_RESPONSE.getMessage()
                            .concat(lse.getMessage()), new ArrayList<>());
                }
            }
        }
        return response;
    }
}
