package com.shitikov.task6.model.service.impl;

import com.shitikov.task6.model.builder.BookBuilder;
import com.shitikov.task6.model.dao.BookListDAO;
import com.shitikov.task6.model.entity.Book;
import com.shitikov.task6.model.exception.ProjectException;
import com.shitikov.task6.model.factory.DAOFactory;
import com.shitikov.task6.model.service.LibraryService;
import com.shitikov.task6.model.validator.BookValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LibraryServiceImpl implements LibraryService {
    @Override
    public void addBook(String name, List<String> authors, String publishingHouse, int pages) throws ProjectException {
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

            bookListDAO.addBook(book);
        } else {
            throw new ProjectException("Parameters are incorrect.");
        }
    }

    @Override
    public void removeBook(Book book) throws ProjectException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        BookListDAO bookListDAO = daoFactory.getBookListDao();

        bookListDAO.removeBook(book);
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

        List<Book> result = new ArrayList<>();

        if (validator.nameIsCorrect(name)) {
            result = bookListDAO.findByName(name);
        }

        return result;
    }

    @Override
    public List<Book> findByAuthor(String author) {
        DAOFactory daoFactory = DAOFactory.getInstance();
        BookListDAO bookListDAO = daoFactory.getBookListDao();
        BookValidator validator = BookValidator.getInstance();

        List<Book> result = new ArrayList<>();

        if (validator.authorIsCorrect(author)) {
            result = bookListDAO.findByAuthor(author);
        }

        return result;
    }

    @Override
    public List<Book> findByPublishingHouse(String publishingHouse) {
        DAOFactory daoFactory = DAOFactory.getInstance();
        BookListDAO bookListDAO = daoFactory.getBookListDao();
        BookValidator validator = BookValidator.getInstance();

        List<Book> result = new ArrayList<>();

        if (validator.publishingHouseIsCorrect(publishingHouse)) {
            result = bookListDAO.findByPublishingHouse(publishingHouse);
        }

        return result;
    }

    @Override
    public List<Book> findByPages(int pages) {
        DAOFactory daoFactory = DAOFactory.getInstance();
        BookListDAO bookListDAO = daoFactory.getBookListDao();
        BookValidator validator = BookValidator.getInstance();

        List<Book> result = new ArrayList<>();

        if (validator.pagesAreCorrect(pages)) {
            result = bookListDAO.findByPages(pages);
        }

        return result;
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
