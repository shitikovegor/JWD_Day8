package com.shitikov.task8.dataprovider;

import com.shitikov.task8.model.builder.BookBuilder;
import com.shitikov.task8.model.entity.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LibraryData {
    private static final Logger logger = Logger.getLogger(LibraryData.class.getName());

    public static final Book BOOK_1;
    public static final Book BOOK_2;
    public static final Book BOOK_3;
    public static final Book BOOK_4;
    public static final Book BOOK_5;
    public static final Book BOOK_FOR_ADD_REMOVE;

    private static final String SQL_INSERT = "INSERT INTO books(name, author, publishingHouse, pages) VALUES(?,?,?,?)";
    private static final String SQL_CLEAR_LIBRARY = "DELETE FROM books";
    private static final String SQL_RESET_ID = "ALTER TABLE books AUTO_INCREMENT=1";


    static {
        BOOK_1 = new BookBuilder()
                .buildBookId(1)
                .buildName("Introducing Maven")
                .buildAuthors("Varanasi B.")
                .buildPublishingHouse("Apress")
                .buildPages(135)
                .buildBook();

        BOOK_2 = new BookBuilder()
                .buildBookId(2)
                .buildName("Pro Spring Boot 2")
                .buildAuthors("Gutierrez F.")
                .buildPublishingHouse("Morgan Kaufmann")
                .buildPages(479)
                .buildBook();

        BOOK_3 = new BookBuilder()
                .buildBookId(3)
                .buildName("Head First Java")
                .buildAuthors("Bates B., Sierra K.")
                .buildPublishingHouse("O Reilly")
                .buildPages(688)
                .buildBook();

        BOOK_4 = new BookBuilder()
                .buildBookId(4)
                .buildName("Go in Practice")
                .buildAuthors("Butcher M., Farina M.")
                .buildPublishingHouse("Manning Publications")
                .buildPages(312)
                .buildBook();

        BOOK_5 = new BookBuilder()
                .buildBookId(5)
                .buildName("Java Software Solutions")
                .buildAuthors("Lewis J., Loftus W.")
                .buildPublishingHouse("Pearson")
                .buildPages(800)
                .buildBook();

        BOOK_FOR_ADD_REMOVE = new BookBuilder()
                .buildBookId(6)
                .buildName("Философия Java")
                .buildAuthors("Брюс Эккель")
                .buildPublishingHouse("Питер")
                .buildPages(1168)
                .buildBook();
    }

    public static List<Book> createExpectedList() {
        List<Book> books = new ArrayList<>();
        books.add(BOOK_1);
        books.add(BOOK_2);
        books.add(BOOK_3);
        books.add(BOOK_4);
        books.add(BOOK_5);

        return books;
    }

    public static void setLibrary() {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library?useUnicode=true&serverTimezone=UTC",
                    "root", "pass");

            statement = connection.prepareStatement(SQL_CLEAR_LIBRARY);
            statement.executeUpdate();
            statement.execute(SQL_RESET_ID);
            statement = connection.prepareStatement(SQL_INSERT);

            for (Book book : createExpectedList()) {
                statement.setString(1, book.getName());
                statement.setString(2, book.getAuthorsAsString());
                statement.setString(3, book.getPublishingHouse());
                statement.setInt(4, book.getPages());
                statement.executeUpdate();
            }
        } catch (ClassNotFoundException | SQLException e) {
            logger.log(Level.WARNING, "Test connection exception: ", e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    logger.log(Level.WARNING, "Exception during closing statement: ", e);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    logger.log(Level.WARNING, "Exception during closing connection: ", e);
                }
            }
        }
    }
}
