package com.shitikov.task6.service.impl;

import com.shitikov.task6.model.builder.BookBuilder;
import com.shitikov.task6.model.dao.BookListDAO;
import com.shitikov.task6.model.entity.Book;
import com.shitikov.task6.model.exception.BookDAOException;
import com.shitikov.task6.model.factory.DAOFactory;
import com.shitikov.task6.service.LibraryService;
import com.shitikov.task6.service.exception.LibraryServiceException;
import com.shitikov.task6.validator.BookValidator;

import java.util.List;
import java.util.Optional;

public class LibraryServiceImpl implements LibraryService {
    @Override
    public void addBook(String name, List<String> authors, String publishingHouse, int pages) throws LibraryServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        BookListDAO bookListDAO = daoFactory.getBookListDao();
        BookValidator validator = BookValidator.getInstance();

        boolean parametersAreCorrect = validator.nameIsCorrect(name) &&
                validator.authorsAreCorrect(authors) &&
                validator.publishingHouseIsCorrect(publishingHouse) &&
                validator.pagesAreCorrect(pages);

        if (parametersAreCorrect) {
            Book book = new BookBuilder()
                    .buildName(name)
                    .buildAuthors(authors)
                    .buildPublishingHouse(publishingHouse)
                    .buildPages(pages)
                    .buildBook();
            try {
                bookListDAO.addBook(book);
            } catch (BookDAOException e) {
                String errorMessage = "Error of adding book. ";
                throw new LibraryServiceException(errorMessage.concat(e.getMessage()));
            }
        } else {
            throw new LibraryServiceException("Parameters are incorrect.");
        }
    }

    @Override
    public void removeBook(Book book) throws LibraryServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        BookListDAO bookListDAO = daoFactory.getBookListDao();

        try {
            bookListDAO.removeBook(book);
        } catch (BookDAOException e) {
            String errorMessage = "Error of removing book. ";
            throw new LibraryServiceException(errorMessage.concat(e.getMessage()));
        }

    }

    @Override
    public Optional<Book> findById(String id) {
        DAOFactory daoFactory = DAOFactory.getInstance();
        BookListDAO bookListDAO = daoFactory.getBookListDao();

        return bookListDAO.findById(id);
    }

    @Override
    public List<Book> findByName(String name) throws LibraryServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        BookListDAO bookListDAO = daoFactory.getBookListDao();
        BookValidator validator = BookValidator.getInstance();

        if (!validator.nameIsCorrect(name)) {
            throw new LibraryServiceException("Parameter is incorrect.");
        }
        return bookListDAO.findByName(name);
    }

    @Override
    public List<Book> findByAuthor(String author) throws LibraryServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        BookListDAO bookListDAO = daoFactory.getBookListDao();
        BookValidator validator = BookValidator.getInstance();

        if (!validator.authorIsCorrect(author)) {
            throw new LibraryServiceException("Parameter is incorrect.");
        }

        return bookListDAO.findByAuthor(author);
    }

    @Override
    public List<Book> findByPublishingHouse(String publishingHouse) throws LibraryServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        BookListDAO bookListDAO = daoFactory.getBookListDao();
        BookValidator validator = BookValidator.getInstance();

        if (!validator.publishingHouseIsCorrect(publishingHouse)) {
            throw new LibraryServiceException("Parameter is incorrect.");
        }

        return bookListDAO.findByPublishingHouse(publishingHouse);
    }

    @Override
    public List<Book> findByPages(int pages) throws LibraryServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        BookListDAO bookListDAO = daoFactory.getBookListDao();
        BookValidator validator = BookValidator.getInstance();

        if (!validator.pagesAreCorrect(pages)) {
            throw new LibraryServiceException("Parameter is incorrect.");
        }

        return bookListDAO.findByPages(pages);
    }

    @Override
    public List<Book> sortBooksById() {
        DAOFactory daoFactory = DAOFactory.getInstance();
        BookListDAO bookListDAO = daoFactory.getBookListDao();

        return bookListDAO.sortBooksById();
    }

    @Override
    public List<Book> sortBooksByName() {
        DAOFactory daoFactory = DAOFactory.getInstance();
        BookListDAO bookListDAO = daoFactory.getBookListDao();

        return bookListDAO.sortBooksByName();
    }

    @Override
    public List<Book> sortBooksByAuthor() {
        DAOFactory daoFactory = DAOFactory.getInstance();
        BookListDAO bookListDAO = daoFactory.getBookListDao();

        return bookListDAO.sortBooksByAuthor();
    }

    @Override
    public List<Book> sortBooksByPublishingHouse() {
        DAOFactory daoFactory = DAOFactory.getInstance();
        BookListDAO bookListDAO = daoFactory.getBookListDao();

        return bookListDAO.sortBooksByPublishingHouse();
    }

    @Override
    public List<Book> sortBooksByPages() {
        DAOFactory daoFactory = DAOFactory.getInstance();
        BookListDAO bookListDAO = daoFactory.getBookListDao();

        return bookListDAO.sortBooksByPages();
    }

    @Override
    public List<Book> findAll() {
        DAOFactory daoFactory = DAOFactory.getInstance();
        BookListDAO bookListDAO = daoFactory.getBookListDao();

        return bookListDAO.findAll();
    }
}
