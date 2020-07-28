package com.shitikov.task8.model.service.impl;

import com.shitikov.task8.model.builder.BookBuilder;
import com.shitikov.task8.model.dao.BookListDao;
import com.shitikov.task8.model.entity.Book;
import com.shitikov.task8.model.exception.DaoException;
import com.shitikov.task8.model.factory.DAOFactory;
import com.shitikov.task8.model.service.LibraryService;
import com.shitikov.task8.model.exception.LibraryServiceException;
import com.shitikov.task8.model.entity.KeyType;
import com.shitikov.task8.validator.BookValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LibraryServiceImpl implements LibraryService {
    private static final Logger logger = Logger.getLogger(LibraryServiceImpl.class.getName());

    @Override
    public boolean add(String name, String authors, String publishingHouse, String pages) throws LibraryServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        BookListDao bookListDAO = daoFactory.getBookListDao();
        BookValidator validator = BookValidator.getInstance();
        boolean isBookAdded;

        boolean parametersAreCorrect = validator.isStringParameterCorrect(name) &&
                validator.isStringParameterCorrect(authors) &&
                validator.isStringParameterCorrect(publishingHouse) &&
                validator.arePagesCorrect(pages);

        if (parametersAreCorrect) {
            Book book = new BookBuilder()
                    .buildName(name)
                    .buildAuthors(authors)
                    .buildPublishingHouse(publishingHouse)
                    .buildPages(Integer.parseInt(pages))
                    .buildBook();
            try {
                isBookAdded = bookListDAO.add(book);
            } catch (DaoException e) {
                String errorMessage = "Error of adding book. ";
                throw new LibraryServiceException(errorMessage.concat(e.getMessage()), e);
            }
        } else {
            throw new LibraryServiceException("Parameters are incorrect.");
        }
        return isBookAdded;
    }

    @Override
    public boolean remove(String name, String authors, String publishingHouse, String pages) throws LibraryServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        BookListDao bookListDAO = daoFactory.getBookListDao();
        BookValidator validator = BookValidator.getInstance();
        boolean isBookRemoved;

        boolean parametersAreCorrect = validator.isStringParameterCorrect(name) &&
                validator.isStringParameterCorrect(authors) &&
                validator.isStringParameterCorrect(publishingHouse) &&
                validator.arePagesCorrect(pages);

        if (parametersAreCorrect) {
            Book book = new BookBuilder()
                    .buildName(name)
                    .buildAuthors(authors)
                    .buildPublishingHouse(publishingHouse)
                    .buildPages(Integer.parseInt(pages))
                    .buildBook();
            try {
                isBookRemoved = bookListDAO.remove(book);
            } catch (DaoException e) {
                throw new LibraryServiceException("Error during removing. ", e);
            }
        } else {
            throw new LibraryServiceException("Parameters are incorrect.");
        }
        return isBookRemoved;
    }

    @Override
    public boolean removeById(String id) throws LibraryServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        BookListDao bookListDAO = daoFactory.getBookListDao();
        BookValidator validator = BookValidator.getInstance();
        boolean isBookRemoved;

        if (validator.isIdCorrect(id)) {
            try {
                isBookRemoved = bookListDAO.remove(Integer.parseInt(id));
            } catch (DaoException e) {
                throw new LibraryServiceException("Error during removing. ", e);
            }
        } else {
            throw new LibraryServiceException("Parameters are incorrect.");
        }
        return isBookRemoved;
    }

    @Override
    public Optional<Book> findById(String id) {
        DAOFactory daoFactory = DAOFactory.getInstance();
        BookListDao bookListDAO = daoFactory.getBookListDao();
        BookValidator validator = BookValidator.getInstance();

        Optional<Book> foundBook = Optional.empty();

        if (validator.isIdCorrect(id)) {
            try {
                foundBook = bookListDAO.findById(Integer.parseInt(id));
            } catch (DaoException e) {
                logger.log(Level.INFO, "Exception: ", e);
            }
        }
        return foundBook;
    }
    @Override
    public List<Book> findByKey(KeyType key, String parameter) {
        DAOFactory daoFactory = DAOFactory.getInstance();
        BookListDao bookListDAO = daoFactory.getBookListDao();
        BookValidator validator = BookValidator.getInstance();
        List<Book> foundBooks = new ArrayList<>();

        boolean isParameterValid = (key == KeyType.PAGES) ? validator.arePagesCorrect(parameter) :
                    validator.isStringParameterCorrect(parameter);

        if (isParameterValid) {
            try {
                foundBooks = bookListDAO.findByKey(key, parameter);
            } catch (DaoException e) {
                logger.log(Level.INFO, "Exception: ", e);
                return foundBooks;
            }
        }
        return foundBooks;
    }

    @Override
    public List<Book> sortByKey(KeyType key) {
        DAOFactory daoFactory = DAOFactory.getInstance();
        BookListDao bookListDAO = daoFactory.getBookListDao();

        try {
            List<Book> sortedBooks = bookListDAO.sortByKey(key);
            return sortedBooks;
            } catch (DaoException e) {
                logger.log(Level.INFO, "Exception: ", e);
                return new ArrayList<>();
            }
    }

    @Override
    public List<Book> findAll() {
        DAOFactory daoFactory = DAOFactory.getInstance();
        BookListDao bookListDAO = daoFactory.getBookListDao();

        List<Book> books;
        try {
            books = bookListDAO.findAll();
        } catch (DaoException e) {
            books = new ArrayList<>();
        }
        return books;
    }
}
