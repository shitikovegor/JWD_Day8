package com.shitikov.task8.model.service;

import com.shitikov.task8.model.entity.Book;
import com.shitikov.task8.model.exception.LibraryServiceException;
import com.shitikov.task8.model.entity.KeyType;

import java.util.List;
import java.util.Optional;

public interface LibraryService {
    boolean add(String name, String authors, String publishingHouse, String pages) throws LibraryServiceException;
    boolean remove(String name, String authors, String publishingHouse, String pages) throws LibraryServiceException;
    boolean removeById(String id) throws LibraryServiceException;
    Optional<Book> findById(String id) ;
    List<Book> findByKey(KeyType key, String parameter);
    List<Book> sortByKey(KeyType key);
    List<Book> findAll();
}
