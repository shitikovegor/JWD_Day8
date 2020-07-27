package com.shitikov.task8.model.dao.impl;

import com.shitikov.task8.model.builder.BookBuilder;
import com.shitikov.task8.model.dao.BookListDao;
import com.shitikov.task8.model.entity.Book;
import com.shitikov.task8.model.entity.Library;
import com.shitikov.task8.model.exception.DaoException;
import com.shitikov.task8.model.exception.PoolException;
import com.shitikov.task8.model.pool.ConnectionPool;
import com.shitikov.task8.util.KeyType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BookListDaoImpl implements BookListDao {
    private static final String AUTHOR_DELIMITER = ", ";
    private static final String SEARCH_LIKE_REGEX = "%%%s%%";

    private static final String ID_COLUMN = "id";
    private static final String NAME_COLUMN = "name";
    private static final String AUTHOR_COLUMN = "author";
    private static final String PUBLISHING_HOUSE_COLUMN = "publishingHouse";
    private static final String PAGES_COLUMN = "pages";

//    @Language("SQL")
    private static final String SQL_INSERT = "INSERT INTO books(name, author, publishingHouse, pages) VALUES(?,?,?,?)";
    private static final String SQL_DELETE = "DELETE FROM books WHERE name = ? AND author = ? AND publishingHouse = ? AND pages = ?";
    private static final String SQL_DELETE_BY_ID = "DELETE FROM books WHERE id = ?";
    private static final String SQL_FIND_ALL = "SELECT id, name, author, publishingHouse, pages FROM books";
    private static final String SQL_FIND_BY_ID = "SELECT id, name, author, publishingHouse, pages FROM books " +
            "WHERE id = ?";
    private static final String SQL_FIND_BY_NAME = "SELECT id, name, author, publishingHouse, pages " +
            "FROM books WHERE name LIKE ?";
    private static final String SQL_FIND_BY_AUTHOR = "SELECT id, name, author, publishingHouse, pages " +
            "FROM books WHERE author LIKE ?";
    private static final String SQL_FIND_BY_PUBLISHING_HOUSE = "SELECT id, name, author, publishingHouse, pages " +
            "FROM books WHERE publishingHouse LIKE ?";
    private static final String SQL_FIND_BY_PAGES = "SELECT id, name, author, publishingHouse, pages " +
            "FROM books WHERE pages LIKE ?";
    // TODO: 27.07.2020 is possible use String.format for create request like "WHERE %s LIKE ?"?
    private static final String SQL_FIND_BY_KEY = "SELECT id, name, author, publishingHouse, pages " +
            "FROM books WHERE %s LIKE ?";
    private static final String SQL_SORT_BY_KEY = "SELECT id, name, author, publishingHouse, pages FROM books " +
            "ORDER BY %s";



    @Override
    public void add(Book book) throws DaoException {
        if (book == null) {
            throw new DaoException("Book is null.");
        }
        boolean isBookAdded = executeUpdate(book, SQL_INSERT);

        if (!isBookAdded) {
            throw new DaoException("Book didn't add. ");
        }
    }

    @Override
    public void remove(Book book) throws DaoException {
        boolean isBookRemoved = executeUpdate(book, SQL_DELETE);

        if (!isBookRemoved) {
            throw new DaoException("Book didn't remove. ");
        }
    }

    @Override
    public void remove(Integer id) throws DaoException {
        boolean isBookRemoved;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_DELETE_BY_ID)) {

            statement.setInt(1, id);
            int result = statement.executeUpdate();
            isBookRemoved = result != 0;
        } catch (PoolException | SQLException e) {
            throw new DaoException("Connection error. ", e);
        }
        if (!isBookRemoved) {
            throw new DaoException("Book didn't remove. ");
        }
    }

    @Override
    public Optional<Book> findById(Integer id) throws DaoException {

        Optional<Book> foundBook = Optional.empty();

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_ID)){

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

            statement.setString(1, String.format(SEARCH_LIKE_REGEX, parameter));
            try (ResultSet resultSet = statement.executeQuery()) {
                books = fillBooks(resultSet);
            }
        } catch (SQLException | PoolException se) {
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
        } catch (SQLException | PoolException se) {
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
        } catch (SQLException | PoolException se) {
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

    private boolean executeUpdate(Book book, String request) throws DaoException {
        boolean isUpdate;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(request)) {

            statement.setString(1, book.getName());
            statement.setString(2, authorsToString(book.getAuthors()));
            statement.setString(3, book.getPublishingHouse());
            statement.setInt(4, book.getPages());
            int result = statement.executeUpdate();
            isUpdate = result != 0;
        } catch (PoolException | SQLException se) {
            throw new DaoException("Connection error. ", se);
        }
        return isUpdate;
    }

    private String authorsToString(List<String> authors) {
        StringBuilder result = new StringBuilder();
        if (authors.size() == 1) {
            result.append(authors.get(0));
        } else {
            for (int i = 0; i < authors.size() - 1; i++) {
                result.append(authors.get(i));
                result.append(AUTHOR_DELIMITER);
            }
            result.append(authors.get(authors.size() - 1));
        }
        return result.toString();
    }


    // TODO: 27.07.2020 delete unused methods
    @Override
    public List<Book> findByName(String name) throws DaoException {
        List<Book> books;

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_NAME)) {

            statement.setString(1, String.format(SEARCH_LIKE_REGEX, name));
            try (ResultSet resultSet = statement.executeQuery()) {
                books = fillBooks(resultSet);
            }
        } catch (SQLException | PoolException se) {
            throw new DaoException("Connection error. ", se);
        }
        return books;
    }

    @Override
    public List<Book> findByAuthor(String author) throws DaoException {
        List<Book> books;

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_AUTHOR)) {

            statement.setString(1, String.format(SEARCH_LIKE_REGEX, author));
            try (ResultSet resultSet = statement.executeQuery()) {
                books = fillBooks(resultSet);
            }
        } catch (SQLException | PoolException se) {
            throw new DaoException("Connection error. ", se);
        }
        return books;
    }

    @Override
    public List<Book> findByPublishingHouse(String publishingHouse) {
        List<Book> books = Library.getInstance().getBooks();
        List<Book> foundBooks =
                books.stream().filter(book -> book.getPublishingHouse().contains(publishingHouse))
                        .collect(Collectors.toList());

        return foundBooks;
    }

    @Override
    public List<Book> findByPages(int pages) {
        List<Book> books = Library.getInstance().getBooks();
        List<Book> foundBooks = books.stream().filter(book -> book.getPages() == pages)
                .collect(Collectors.toList());

        return foundBooks;
    }

    @Override
    public List<Book> sortById() {
        List<Book> books = fillFromLibrary();
        books.sort(new Book.IdComparator());
        return books;
    }

    @Override
    public List<Book> sortByName() {
        List<Book> books = fillFromLibrary();
        books.sort(new Book.NameComparator());
        return books;
    }

    @Override
    public List<Book> sortByAuthor() {
        List<Book> books = fillFromLibrary();
        books.sort(new Book.AuthorComparator());
        return books;
    }

    @Override
    public List<Book> sortByPublishingHouse() {
        List<Book> books = fillFromLibrary();
        books.sort(new Book.PublishingHouseComparator());
        return books;
    }

    @Override
    public List<Book> sortByPages() {
        List<Book> books = fillFromLibrary();
        books.sort(new Book.PagesComparator());
        return books;
    }

    private List<Book> fillFromLibrary() {
        List<Book> unmodList = Library.getInstance().getBooks();
        return new ArrayList<>(unmodList);
    }
}
