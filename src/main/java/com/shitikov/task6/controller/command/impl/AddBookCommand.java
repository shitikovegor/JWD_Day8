package com.shitikov.task6.controller.command.impl;

import com.shitikov.task6.controller.command.Command;
import com.shitikov.task6.controller.command.type.CommandResponse;
import com.shitikov.task6.controller.command.type.KeyType;
import com.shitikov.task6.model.entity.Book;
import com.shitikov.task6.service.LibraryService;
import com.shitikov.task6.service.exception.LibraryServiceException;
import com.shitikov.task6.service.impl.LibraryServiceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddBookCommand implements Command {
    @Override
    public Map<String, List<Book>> execute(Map<String, String> parameters) {
        LibraryService libraryService = new LibraryServiceImpl();
        Map<String, List<Book>> response = new HashMap<>();

        if (parameters.containsKey(KeyType.NAME.getName())
                && parameters.containsKey(KeyType.AUTHOR.getName())
                && parameters.containsKey(KeyType.PUBLISHING_HOUSE.getName())
                && parameters.containsKey(KeyType.PAGES.getName())) {

            try {
                String name = parameters.get("name");
                String authors = parameters.get("author");
                String publishingHouse = parameters.get("publishingHouse");
                String pages = parameters.get("pages");

                libraryService.add(name, authors, publishingHouse, pages);
                response.put(CommandResponse.ADD_RESPONSE.getMessage(), libraryService.findAll());
            } catch (LibraryServiceException ex) {
                response.put(CommandResponse.BAD_RESPONSE.getMessage().concat(ex.getMessage()), new ArrayList<>());
            }
        }
        return response;
    }
}
