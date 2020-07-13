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
import java.util.Optional;

public class RemoveBookCommand implements Command {
    private static final String GOOD_RESPONSE = "Book removed from library";
    private static final String NOT_FOUND_RESPONSE = "Book doesn't exist in library";
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

            Optional<Book> bookOptional = findBook(libraryService, name, authors, publishingHouse, pages);
            if (bookOptional.isPresent()) {
                Book book = bookOptional.get();
                libraryService.removeBook(book);
                response.put(GOOD_RESPONSE, null);
            } else {
                response.put(NOT_FOUND_RESPONSE, null);
            }

        } catch (ControllerException | LibraryServiceException ex) {
            response.put(BAD_RESPONSE.concat(ex.getMessage()), null);
        }

        return response;
    }

    private Optional<Book> findBook(LibraryService libraryService, String name,
                                   List<String> authors, String publishingHouse,
                                   int pages) throws LibraryServiceException {

        List<Book> foundBooks = libraryService.findByName(name);

        return foundBooks.stream()
                .filter(book -> book.getName().equals(name)
                        && book.getAuthors().equals(authors)
                        && book.getPublishingHouse().equals(publishingHouse)
                        && book.getPages() == pages)
                .findFirst();
    }
}
