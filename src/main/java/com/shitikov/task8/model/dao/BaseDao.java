package com.shitikov.task8.model.dao;

import com.shitikov.task8.model.entity.Entity;
import com.shitikov.task8.model.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface BaseDao<K, T extends Entity> {

    List<T> findAll() throws DaoException;
    Optional<T> findById(K id) throws DaoException;
    boolean add(T t) throws DaoException;
    boolean remove(T t) throws DaoException;
    boolean remove(K id) throws DaoException;
}
