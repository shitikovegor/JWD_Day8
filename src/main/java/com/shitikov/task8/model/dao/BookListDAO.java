package com.shitikov.task8.model.dao;

import com.shitikov.task8.model.entity.Book;
import com.shitikov.task8.model.exception.BookDAOException;

import java.util.List;
import java.util.Optional;

public interface BookListDAO {
    void add(Book book) throws BookDAOException;

    void remove(Book book) throws BookDAOException;

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

    List<Book> findAll() throws BookDAOException;
}
