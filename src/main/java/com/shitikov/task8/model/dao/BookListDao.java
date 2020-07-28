package com.shitikov.task8.model.dao;

import com.shitikov.task8.model.entity.Book;
import com.shitikov.task8.model.exception.DaoException;
import com.shitikov.task8.model.entity.KeyType;

import java.util.List;

public interface BookListDao extends BaseDao<Integer, Book> {
    List<Book> findByKey(KeyType key, String parameter) throws DaoException;
    List<Book> sortByKey(KeyType key) throws DaoException;
}
