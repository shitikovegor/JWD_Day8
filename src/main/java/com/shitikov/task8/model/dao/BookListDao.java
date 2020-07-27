package com.shitikov.task8.model.dao;

import com.shitikov.task8.model.entity.Book;
import com.shitikov.task8.model.exception.DaoException;
import com.shitikov.task8.util.KeyType;

import java.util.List;

public interface BookListDao extends BaseDao<Integer, Book> {
    List<Book> findByKey(KeyType key, String parameter) throws DaoException;

    List<Book> sortByKey(KeyType key) throws DaoException;

    List<Book> findByName(String name) throws DaoException;

    List<Book> findByAuthor(String author) throws DaoException;

    List<Book> findByPublishingHouse(String publishingHouse) throws DaoException;

    List<Book> findByPages(int pages) throws DaoException;

    List<Book> sortById() throws DaoException;

    List<Book> sortByName() throws DaoException;

    List<Book> sortByAuthor() throws DaoException;

    List<Book> sortByPublishingHouse() throws DaoException;

    List<Book> sortByPages() throws DaoException;
}
