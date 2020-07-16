package com.shitikov.task6.service;

import com.shitikov.task6.model.entity.Book;
import com.shitikov.task6.service.exception.LibraryServiceException;

import java.util.List;
import java.util.Optional;

public interface LibraryService {
    void add(String name, String authors, String publishingHouse, int pages) throws LibraryServiceException;

    void remove(String name, String authors, String publishingHouse, int pages) throws LibraryServiceException;

    Optional<Book> findById(String id);

    List<Book> findByName(String name);

    List<Book> findByAuthor(String author);

    List<Book> findByPublishingHouse(String publishingHouse);

    List<Book> findByPages(int pages);

    List<Book> sortById();

    List<Book> sortByName();

    List<Book> sortByAuthor();

    List<Book> sortByPublishingHouse();

    List<Book> sortByPages();

    List<Book> findAll();
}
