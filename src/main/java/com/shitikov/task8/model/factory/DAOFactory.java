package com.shitikov.task8.model.factory;

import com.shitikov.task8.model.dao.BookListDAO;
import com.shitikov.task8.model.dao.impl.BookListDAOImpl;

public final class DAOFactory {
    private static final DAOFactory instance = new DAOFactory();
    private final BookListDAO bookListDAOImpl = new BookListDAOImpl();

    private DAOFactory() {
    }

    public static DAOFactory getInstance() {
        return instance;
    }

    public BookListDAO getBookListDao() {
        return bookListDAOImpl;
    }
}
