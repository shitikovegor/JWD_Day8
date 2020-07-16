package com.shitikov.task6.controller.command.impl;

import com.shitikov.task6.controller.command.Command;
import com.shitikov.task6.controller.command.type.KeyType;
import com.shitikov.task6.controller.command.exception.CommandException;
import com.shitikov.task6.model.entity.Book;
import com.shitikov.task6.service.LibraryService;
import com.shitikov.task6.service.exception.LibraryServiceException;
import com.shitikov.task6.service.impl.LibraryServiceImpl;

import java.util.*;

public class RemoveBookCommand implements Command {
    private static final String GOOD_RESPONSE = "Book removed from library";
    private static final String NOT_FOUND_RESPONSE = "Book doesn't exist in library";


    @Override
    public Map<String, List<Book>> execute(Map<String, String> parameters) {
        LibraryService libraryService = new LibraryServiceImpl();
        Map<String, List<Book>> response = new HashMap<>();

        if (parameters.containsKey(KeyType.ID.getName())
                && parameters.containsKey(KeyType.NAME.getName())
                && parameters.containsKey(KeyType.AUTHOR.getName())
                && parameters.containsKey(KeyType.PUBLISHING_HOUSE.getName())
                && parameters.containsKey(KeyType.PAGES.getName())) {

            try {
                String name = parameters.get("name");
                String authors = parameters.get("author");
                String publishingHouse = parameters.get("publishingHouse");
                int pages = CommandService.getInstance().parsePages(parameters.get("pages"));

                try {
                    libraryService.remove(name, authors, publishingHouse, pages);
                    response.put(GOOD_RESPONSE, libraryService.findAll());
                } catch (LibraryServiceException lse) {
                    if (lse.getMessage().equals("Book doesn't exist in library.")) {
                        response.put(NOT_FOUND_RESPONSE, libraryService.findAll());
                    }
                    response.put(CommandService.BAD_RESPONSE.concat(lse.getMessage()), new ArrayList<>());
                }
            } catch (CommandException ce) {
                response.put(CommandService.BAD_RESPONSE.concat(ce.getMessage()), new ArrayList<>());
            }
        }
        return response;
    }
}
