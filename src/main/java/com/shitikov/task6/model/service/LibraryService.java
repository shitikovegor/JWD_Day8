package com.shitikov.task6.model.service;

import com.shitikov.task6.model.entity.Book;
import com.shitikov.task6.model.exception.ProjectException;

import java.util.List;
import java.util.Optional;

public interface LibraryService {
    void addBook(String name, List<String> authors, String publishingHouse, int pages) throws ProjectException;

    void removeBook(Book book) throws ProjectException;

    Optional<Book> findById(String id);

    List<Book> findByName(String name);

    List<Book> findByAuthor(String author);

    List<Book> findByPublishingHouse(String publishingHouse);

    List<Book> findByPages(int pages);

    List<Book> sortBooksById();

    List<Book> sortBooksByName();

    List<Book> sortBooksByAuthor();

    List<Book> sortBooksByPublishingHouse();

    List<Book> sortBooksByPages();

    List<Book> findAll();
}
