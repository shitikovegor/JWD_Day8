package com.shitikov.task6.model.factory;

import com.shitikov.task6.model.dao.BookListDAO;
import com.shitikov.task6.model.dao.impl.BookListDAOImpl;

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
