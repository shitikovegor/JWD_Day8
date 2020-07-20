package com.shitikov.task8.model.dao.impl;

import com.mysql.cj.jdbc.MysqlDataSource;
import com.shitikov.task8.model.builder.BookBuilder;
import com.shitikov.task8.model.dao.BookListDAO;
import com.shitikov.task8.model.entity.Book;
import com.shitikov.task8.model.entity.Library;
import com.shitikov.task8.model.exception.BookDAOException;
import com.shitikov.task8.model.pool.MySqlDataSourceFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BookListDAOImpl implements BookListDAO {
    private static final String SQL_FIND_ALL = "SELECT bookId, name, author, publishingHouse, pages FROM books";
    private static final String SQL_INSERT = "INSERT INTO books(bookId, name, author, " +
                                                "publishingHouse ,pages) VALUES" +
                                                "(?,?,?,?,?)";

    @Override
    public void add(Book book) throws BookDAOException {
        Library library = Library.getInstance();
        if (book == null) {
            throw new BookDAOException("Book is null.");
        }
        if (library.size() == library.getMaxCapacity()) {
            throw new BookDAOException("No library space.");
        }
        if (library.contains(book)) {
            throw new BookDAOException("Book exists in library.");
        }

        MysqlDataSource dataSource = MySqlDataSourceFactory.createMySqlDataSource();

        try (Connection connection = dataSource.getConnection()){
            try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT)){
                preparedStatement.setInt(1, Integer.parseInt(book.getBookId()));
                preparedStatement.setString(2, book.getName());
                preparedStatement.setString(3, authorsToString(book.getAuthors()));
                preparedStatement.setString(4, book.getPublishingHouse());
                preparedStatement.setInt(5, book.getPages());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException se) {
            throw new BookDAOException("Connection error. " + se.getMessage());
        }
    }

    private String authorsToString(List<String> authors) {
        StringBuilder result = new StringBuilder();
        if (authors.size() == 1) {
            result.append(authors.get(0));
        } else {
            for (int i = 0; i < authors.size() - 1; i++) {
                result.append(authors.get(i));
                result.append(", ");
            }
            result.append(authors.get(authors.size() - 1));
        }
        return result.toString();
    }

    @Override
    public void remove(Book book) throws BookDAOException {
        Library library = Library.getInstance();
        if (!library.contains(book)) {
            throw new BookDAOException("Book doesn't exist in library.");
        }

        library.remove(book);
    }

    @Override
    public Optional<Book> findById(String id) {
        List<Book> books = Library.getInstance().getBooks();
        for (Book book : books) {
            if (book.getBookId().equals(id)) {
                return Optional.of(book);
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Book> findByName(String name) {
        List<Book> books = Library.getInstance().getBooks();
        List<Book> foundBooks = books.stream().filter(book -> book.getName().contains(name))
                .collect(Collectors.toList());

        return foundBooks;
    }

    @Override
    public List<Book> findByAuthor(String author) {
        List<Book> books = Library.getInstance().getBooks();
        List<Book> foundBooks = new ArrayList<>();

        for (Book book : books) {
            List<String> bookAuthors = book.getAuthors();
            for (String bookAuthor : bookAuthors) {
                if (bookAuthor.contains(author)) {
                    foundBooks.add(book);
                    break;
                }
            }
        }
        return foundBooks;
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

    @Override
    public List<Book> findAll() throws BookDAOException {
        List<Book> books = new ArrayList<>();
        MysqlDataSource dataSource = MySqlDataSourceFactory.createMySqlDataSource();

        try (Connection connection = dataSource.getConnection()) {
            try (Statement statement = connection.createStatement()) {

                try (ResultSet resultSet = statement.executeQuery(SQL_FIND_ALL)) {
                    int id;
                    String name;
                    String author;
                    String publishingHouse;
                    int pages;
                    while (resultSet.next()) {
                        id = resultSet.getInt("bookId");
                        name = resultSet.getString("name");
                        author = resultSet.getString("author");
                        publishingHouse = resultSet.getString("publishingHouse");
                        pages = resultSet.getInt("pages");
                        Book book = new BookBuilder()
                                .buildBookId(Integer.toString(id))
                                .buildName(name)
                                .buildAuthors(author)
                                .buildPublishingHouse(publishingHouse)
                                .buildPages(pages)
                                .buildBook();
                        books.add(book);
                    }
                }
            }
        } catch (SQLException se) {
            throw new BookDAOException("Connection error. " + se.getMessage());
        }
        return books;
    }
}
