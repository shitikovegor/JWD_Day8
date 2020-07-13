package com.shitikov.task6.service;

import com.shitikov.task6.model.entity.Book;
import com.shitikov.task6.service.exception.LibraryServiceException;

import java.util.List;
import java.util.Optional;

public interface LibraryService {
    void addBook(String name, List<String> authors, String publishingHouse, int pages) throws LibraryServiceException;

    void removeBook(Book book) throws LibraryServiceException;

    Optional<Book> findById(String id);

    List<Book> findByName(String name) throws LibraryServiceException;

    List<Book> findByAuthor(String author) throws LibraryServiceException;

    List<Book> findByPublishingHouse(String publishingHouse) throws LibraryServiceException;

    List<Book> findByPages(int pages) throws LibraryServiceException;

    List<Book> sortBooksById();

    List<Book> sortBooksByName();

    List<Book> sortBooksByAuthor();

    List<Book> sortBooksByPublishingHouse();

    List<Book> sortBooksByPages();

    List<Book> findAll();
}
