package com.shitikov.task6.model.dao;

import com.shitikov.task6.model.entity.Book;
import com.shitikov.task6.model.exception.BookDAOException;

import java.util.List;
import java.util.Optional;

public interface BookListDAO {
    void addBook(Book book) throws BookDAOException;

    void removeBook(Book book) throws BookDAOException;

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
