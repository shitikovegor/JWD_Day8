package com.shitikov.task8.model.service;

import com.shitikov.task8.model.entity.Book;
import com.shitikov.task8.model.exception.LibraryServiceException;
import com.shitikov.task8.util.KeyType;

import java.util.List;
import java.util.Optional;

public interface LibraryService {
    void add(String name, String authors, String publishingHouse, String pages) throws LibraryServiceException;

    void remove(String name, String authors, String publishingHouse, String pages) throws LibraryServiceException;

    void removeById(String id) throws LibraryServiceException;

    Optional<Book> findById(String id) ;

    List<Book> findByKey(KeyType key, String parameter);

    List<Book> sortByKey(KeyType key);

    List<Book> findByName(String name);

    List<Book> findByAuthor(String author);

    List<Book> findByPublishingHouse(String publishingHouse);

    List<Book> findByPages(String pages);

    List<Book> sortById() throws LibraryServiceException;

    List<Book> sortByName() throws LibraryServiceException;

    List<Book> sortByAuthor() throws LibraryServiceException;

    List<Book> sortByPublishingHouse() throws LibraryServiceException;

    List<Book> sortByPages() throws LibraryServiceException;

    List<Book> findAll();
}
