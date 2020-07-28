package com.shitikov.task8.model.dao.impl;

import com.shitikov.task8.model.builder.BookBuilder;
import com.shitikov.task8.model.dao.BookListDao;
import com.shitikov.task8.model.entity.Book;
import com.shitikov.task8.model.exception.DaoException;
import com.shitikov.task8.model.exception.PoolException;
import com.shitikov.task8.model.pool.ConnectionPool;
import com.shitikov.task8.model.entity.KeyType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.shitikov.task8.model.dao.impl.ColumnName.*;

public class BookListDaoImpl implements BookListDao {
    private static final String SEARCH_PARAMETER_REGEX = "%%%s%%";

    private static final String SQL_INSERT = "INSERT INTO books(name, author, publishingHouse, pages) VALUES(?,?,?,?)";
    private static final String SQL_DELETE = "DELETE FROM books WHERE name = ? AND author = ? AND publishingHouse = ? AND pages = ?";
    private static final String SQL_DELETE_BY_ID = "DELETE FROM books WHERE id = ?";
    private static final String SQL_FIND_ALL = "SELECT id, name, author, publishingHouse, pages FROM books";
    private static final String SQL_FIND_BY_ID = "SELECT id, name, author, publishingHouse, pages FROM books WHERE id = ?";
    private static final String SQL_FIND_BY_KEY = "SELECT id, name, author, publishingHouse, pages " +
            "FROM books WHERE %s LIKE ?";
    private static final String SQL_SORT_BY_KEY = "SELECT id, name, author, publishingHouse, pages FROM books ORDER BY %s";
    private static final String SQL_FIND_BOOK = "SELECT id, name, author, publishingHouse, pages " +
            "FROM books WHERE name = ? AND author = ? AND publishingHouse = ? AND pages = ?";

    @Override
    public boolean add(Book book) throws DaoException {
        if (book == null) {
            throw new DaoException("Book is null.");
        }
        boolean isBookAdded = false;
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {

            if (!isBookInLibrary(connection, book)) {
                try(PreparedStatement statement = connection.prepareStatement(SQL_INSERT)) {
                    statement.setString(1, book.getName());
                    statement.setString(2, book.getAuthorsAsString());
                    statement.setString(3, book.getPublishingHouse());
                    statement.setInt(4, book.getPages());
                    int result = statement.executeUpdate();
                    isBookAdded = result != 0;
                }
            }
        } catch (PoolException | SQLException se) {
            throw new DaoException("Connection error. ", se);
        }
        return isBookAdded;
    }

    @Override
    public boolean remove(Book book) throws DaoException {
        boolean isBookRemoved;

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_DELETE)) {

            statement.setString(1, book.getName());
            statement.setString(2, book.getAuthorsAsString());
            statement.setString(3, book.getPublishingHouse());
            statement.setInt(4, book.getPages());
            int result = statement.executeUpdate();
            isBookRemoved = result != 0;
        } catch (PoolException | SQLException se) {
            throw new DaoException("Connection error. ", se);
        }
        return isBookRemoved;
    }

    @Override
    public boolean remove(Integer id) throws DaoException {
        boolean isBookRemoved;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_DELETE_BY_ID)) {

            statement.setInt(1, id);
            int result = statement.executeUpdate();
            isBookRemoved = result != 0;
        } catch (PoolException | SQLException e) {
            throw new DaoException("Connection error. ", e);
        }
        return isBookRemoved;
    }

    @Override
    public Optional<Book> findById(Integer id) throws DaoException {
        Optional<Book> foundBook = Optional.empty();

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_ID)) {

            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Book book = new BookBuilder()
                            .buildBookId(resultSet.getInt(ID_COLUMN))
                            .buildName(resultSet.getString(NAME_COLUMN))
                            .buildAuthors(resultSet.getString(AUTHOR_COLUMN))
                            .buildPublishingHouse(resultSet.getString(PUBLISHING_HOUSE_COLUMN))
                            .buildPages(resultSet.getInt(PAGES_COLUMN))
                            .buildBook();
                    foundBook = Optional.of(book);
                }
            }
        } catch (PoolException | SQLException e) {
            throw new DaoException("Connection error. ", e);
        }
        return foundBook;
    }

    @Override
    public List<Book> findByKey(KeyType key, String parameter) throws DaoException {
        List<Book> books;
        String request = String.format(SQL_FIND_BY_KEY, key.getName());

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(request)) {

            statement.setString(1, String.format(SEARCH_PARAMETER_REGEX, parameter));
            try (ResultSet resultSet = statement.executeQuery()) {
                books = fillBooks(resultSet);
            }
        } catch (PoolException | SQLException se) {
            throw new DaoException("Connection error. ", se);
        }
        return books;
    }

    @Override
    public List<Book> sortByKey(KeyType key) throws DaoException {
        List<Book> books;
        String request = String.format(SQL_SORT_BY_KEY, key.getName());

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(request)) {

            books = fillBooks(resultSet);
        } catch (PoolException | SQLException se) {
            throw new DaoException("Connection error. ", se);
        }
        return books;
    }

    @Override
    public List<Book> findAll() throws DaoException {
        List<Book> books;

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SQL_FIND_ALL)) {

            books = fillBooks(resultSet);
        } catch (PoolException | SQLException se) {
            throw new DaoException("Connection error. ", se);
        }
        return books;
    }

    private List<Book> fillBooks(ResultSet resultSet) throws DaoException {
        List<Book> books = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Book book = new BookBuilder()
                        .buildBookId(resultSet.getInt(ID_COLUMN))
                        .buildName(resultSet.getString(NAME_COLUMN))
                        .buildAuthors(resultSet.getString(AUTHOR_COLUMN))
                        .buildPublishingHouse(resultSet.getString(PUBLISHING_HOUSE_COLUMN))
                        .buildPages(resultSet.getInt(PAGES_COLUMN))
                        .buildBook();
                books.add(book);
            }
        } catch (SQLException se) {
            throw new DaoException("Result set error. ", se);
        }
        return books;
    }

    private boolean isBookInLibrary(Connection connection, Book book) throws DaoException {
        boolean isBookInLibrary;
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_BOOK)) {

            statement.setString(1, book.getName());
            statement.setString(2, book.getAuthorsAsString());
            statement.setString(3, book.getPublishingHouse());
            statement.setInt(4, book.getPages());
            try (ResultSet resultSet = statement.executeQuery()) {
                isBookInLibrary = resultSet.next();
            }
        } catch (SQLException e) {
            throw new DaoException("Connection error. ", e);
        }
        return isBookInLibrary;
    }
}
