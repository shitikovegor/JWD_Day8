package com.shitikov.task8.service;

import com.shitikov.task8.model.entity.Book;
import com.shitikov.task8.service.exception.LibraryServiceException;

import java.util.List;
import java.util.Optional;

public interface LibraryService {
    void add(String name, String authors, String publishingHouse, String pages) throws LibraryServiceException;

    void remove(String name, String authors, String publishingHouse, String pages) throws LibraryServiceException;

    Optional<Book> findById(String id);

    List<Book> findByName(String name);

    List<Book> findByAuthor(String author);

    List<Book> findByPublishingHouse(String publishingHouse);

    List<Book> findByPages(String pages);

    List<Book> sortById();

    List<Book> sortByName();

    List<Book> sortByAuthor();

    List<Book> sortByPublishingHouse();

    List<Book> sortByPages();

    List<Book> findAll();
}
