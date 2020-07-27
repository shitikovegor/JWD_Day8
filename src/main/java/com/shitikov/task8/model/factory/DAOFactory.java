package com.shitikov.task8.model.factory;

import com.shitikov.task8.model.dao.BookListDao;
import com.shitikov.task8.model.dao.impl.BookListDaoImpl;

public final class DAOFactory {
    private static final DAOFactory instance = new DAOFactory();
    private final BookListDao bookListDaoImpl = new BookListDaoImpl();

    private DAOFactory() {
    }

    public static DAOFactory getInstance() {
        return instance;
    }

    public BookListDao getBookListDao() {
        return bookListDaoImpl;
    }
}
