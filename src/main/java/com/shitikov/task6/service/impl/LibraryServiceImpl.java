package com.shitikov.task6.service.impl;

import com.shitikov.task6.model.builder.BookBuilder;
import com.shitikov.task6.model.dao.BookListDAO;
import com.shitikov.task6.model.entity.Book;
import com.shitikov.task6.model.exception.BookDAOException;
import com.shitikov.task6.model.factory.DAOFactory;
import com.shitikov.task6.service.LibraryService;
import com.shitikov.task6.service.exception.LibraryServiceException;
import com.shitikov.task6.validator.BookValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LibraryServiceImpl implements LibraryService {
    @Override
    public void add(String name, String authors, String publishingHouse, String pages) throws LibraryServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        BookListDAO bookListDAO = daoFactory.getBookListDao();
        BookValidator validator = BookValidator.getInstance();

        int pagesNumber = parsePages(pages);
        boolean parametersAreCorrect = validator.isNameCorrect(name) &&
                validator.isAuthorCorrect(authors) &&
                validator.isPublishingHouseCorrect(publishingHouse) &&
                validator.arePagesCorrect(pagesNumber);

        if (parametersAreCorrect) {
            Book book = new BookBuilder()
                    .buildName(name)
                    .buildAuthors(authors)
                    .buildPublishingHouse(publishingHouse)
                    .buildPages(pagesNumber)
                    .buildBook();
            try {
                bookListDAO.add(book);
            } catch (BookDAOException e) {
                String errorMessage = "Error of adding book. ";
                throw new LibraryServiceException(errorMessage.concat(e.getMessage()));
            }
        } else {
            throw new LibraryServiceException("Parameters are incorrect.");
        }
    }

    @Override
    public void remove(String name, String authors, String publishingHouse, String pages) throws LibraryServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        BookListDAO bookListDAO = daoFactory.getBookListDao();
        BookValidator validator = BookValidator.getInstance();

        int pagesNumber = parsePages(pages);

        boolean parametersAreCorrect = validator.isNameCorrect(name) &&
                validator.isAuthorCorrect(authors) &&
                validator.isPublishingHouseCorrect(publishingHouse) &&
                validator.arePagesCorrect(pagesNumber);

        if (parametersAreCorrect) {
            Book book = new BookBuilder()
                    .buildName(name)
                    .buildAuthors(authors)
                    .buildPublishingHouse(publishingHouse)
                    .buildPages(pagesNumber)
                    .buildBook();
            try {
                bookListDAO.remove(book);
            } catch (BookDAOException e) {
                throw new LibraryServiceException(e.getMessage());
            }
        } else {
            throw new LibraryServiceException("Parameters are incorrect.");
        }
    }

    @Override
    public Optional<Book> findById(String id) {
        DAOFactory daoFactory = DAOFactory.getInstance();
        BookListDAO bookListDAO = daoFactory.getBookListDao();

        return bookListDAO.findById(id);
    }

    @Override
    public List<Book> findByName(String name) {
        DAOFactory daoFactory = DAOFactory.getInstance();
        BookListDAO bookListDAO = daoFactory.getBookListDao();
        BookValidator validator = BookValidator.getInstance();

        List<Book> foundBooks = new ArrayList<>();

        if (validator.isNameCorrect(name)) {
            foundBooks = bookListDAO.findByName(name);
        }
        return foundBooks;
    }

    @Override
    public List<Book> findByAuthor(String author) {
        DAOFactory daoFactory = DAOFactory.getInstance();
        BookListDAO bookListDAO = daoFactory.getBookListDao();
        BookValidator validator = BookValidator.getInstance();

        List<Book> foundBooks = new ArrayList<>();

        if (validator.isAuthorCorrect(author)) {
            foundBooks = bookListDAO.findByAuthor(author);
        }
        return foundBooks;
    }

    @Override
    public List<Book> findByPublishingHouse(String publishingHouse) {
        DAOFactory daoFactory = DAOFactory.getInstance();
        BookListDAO bookListDAO = daoFactory.getBookListDao();
        BookValidator validator = BookValidator.getInstance();

        List<Book> foundBooks = new ArrayList<>();

        if (validator.isPublishingHouseCorrect(publishingHouse)) {
            foundBooks = bookListDAO.findByPublishingHouse(publishingHouse);
        }
        return foundBooks;
    }

    @Override
    public List<Book> findByPages(String pages) {
        DAOFactory daoFactory = DAOFactory.getInstance();
        BookListDAO bookListDAO = daoFactory.getBookListDao();
        BookValidator validator = BookValidator.getInstance();

        List<Book> foundBooks = new ArrayList<>();

        try {
            int pagesNumber = parsePages(pages);
            if (validator.arePagesCorrect(pagesNumber)) {
                foundBooks = bookListDAO.findByPages(pagesNumber);
            }
            return foundBooks;
        } catch (LibraryServiceException e) {
            return foundBooks;
        }
    }

    @Override
    public List<Book> sortById() {
        DAOFactory daoFactory = DAOFactory.getInstance();
        BookListDAO bookListDAO = daoFactory.getBookListDao();

        return bookListDAO.sortById();
    }

    @Override
    public List<Book> sortByName() {
        DAOFactory daoFactory = DAOFactory.getInstance();
        BookListDAO bookListDAO = daoFactory.getBookListDao();

        return bookListDAO.sortByName();
    }

    @Override
    public List<Book> sortByAuthor() {
        DAOFactory daoFactory = DAOFactory.getInstance();
        BookListDAO bookListDAO = daoFactory.getBookListDao();

        return bookListDAO.sortByAuthor();
    }

    @Override
    public List<Book> sortByPublishingHouse() {
        DAOFactory daoFactory = DAOFactory.getInstance();
        BookListDAO bookListDAO = daoFactory.getBookListDao();

        return bookListDAO.sortByPublishingHouse();
    }

    @Override
    public List<Book> sortByPages() {
        DAOFactory daoFactory = DAOFactory.getInstance();
        BookListDAO bookListDAO = daoFactory.getBookListDao();

        return bookListDAO.sortByPages();
    }

    @Override
    public List<Book> findAll() {
        DAOFactory daoFactory = DAOFactory.getInstance();
        BookListDAO bookListDAO = daoFactory.getBookListDao();

        return bookListDAO.findAll();
    }

    private int parsePages(String pages) throws LibraryServiceException {
        try {
            return Integer.parseInt(pages);
        } catch (NumberFormatException e) {
            throw new LibraryServiceException("Parameter is incorrect.");
        }
    }
}
