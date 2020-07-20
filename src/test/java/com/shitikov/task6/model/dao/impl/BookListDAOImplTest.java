package com.shitikov.task6.model.dao.impl;

import com.shitikov.task6.dataprovider.LibraryData;
import com.shitikov.task6.dataprovider.DaoServiceDataProvider;
import com.shitikov.task6.model.builder.BookBuilder;
import com.shitikov.task6.model.dao.BookListDAO;
import com.shitikov.task6.model.entity.Book;
import com.shitikov.task6.model.entity.Library;
import com.shitikov.task6.model.exception.BookDAOException;
import com.shitikov.task6.model.factory.DAOFactory;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.shitikov.task6.dataprovider.LibraryData.*;
import static org.testng.Assert.*;

public class BookListDAOImplTest {
    BookListDAO bookListDAO;

    @DataProvider(name = "addExceptionData")
    public static Object[] createExceptionData() {
        return new Object[]{null, BOOK_2};
    }

    @BeforeClass
    public void setUpLibrary() {
        LibraryData.setLibrary();
    }

    @BeforeMethod
    public void setUp() {
        DAOFactory daoFactory = DAOFactory.getInstance();
        bookListDAO = daoFactory.getBookListDao();
    }

    @BeforeGroups(groups = "addRemoveBookDao")
    public void setUpGroup() {
        DAOFactory daoFactory = DAOFactory.getInstance();
        bookListDAO = daoFactory.getBookListDao();

        Library.getInstance().removeAll();
        Library.getInstance().add(BOOK_1);
        Library.getInstance().add(BOOK_2);
        Library.getInstance().add(BOOK_3);
        Library.getInstance().add(BOOK_4);
    }

    @Test(groups = "addRemoveBookDao", dataProvider = "addExceptionData", expectedExceptions = BookDAOException.class)
    public void testAddException(Book book) throws BookDAOException {
        bookListDAO.add(book);
    }

    @Test(groups = "addRemoveBookDao")
    public void testAdd() throws BookDAOException {
        bookListDAO.add(BOOK_5);
        assertTrue(Library.getInstance().contains(BOOK_5));
    }

    @Test(groups = "addRemoveBookDao", expectedExceptions = BookDAOException.class)
    public void testRemoveException() throws BookDAOException {
        Book bookForRemove = new BookBuilder()
                .buildName("Modern Java Recipes")
                .buildAuthors("Kousen K.")
                .buildPublishingHouse("Addison-Wesley Professional")
                .buildPages(424)
                .buildBook();
        bookListDAO.remove(bookForRemove);
    }

    @Test(groups = "addRemoveBookDao")
    public void testRemove() throws BookDAOException {
        bookListDAO.remove(BOOK_4);
        assertFalse(Library.getInstance().contains(BOOK_4));
    }

    @Test(dataProvider = "findById", dataProviderClass = DaoServiceDataProvider.class)
    public void testFindById(String request, Optional<Book> expected) {
        Optional<Book> actual = bookListDAO.findById(request);
        assertEquals(actual, expected);
    }

    @Test(dataProvider = "findByName", dataProviderClass = DaoServiceDataProvider.class)
    public void testFindByName(String request, List<Book> expected) {
        List<Book> actual = bookListDAO.findByName(request);
        assertEquals(actual, expected);
    }

    @Test(dataProvider = "findByAuthor", dataProviderClass = DaoServiceDataProvider.class)
    public void testFindByAuthor(String request, List<Book> expected) {
        List<Book> actual = bookListDAO.findByAuthor(request);
        assertEquals(actual, expected);
    }

    @Test(dataProvider = "findByPublishingHouse", dataProviderClass = DaoServiceDataProvider.class)
    public void testFindByPublishingHouse(String request, List<Book> expected) {
        List<Book> actual = bookListDAO.findByPublishingHouse(request);
        assertEquals(actual, expected);
    }

    @Test(dataProvider = "findByPagesDao", dataProviderClass = DaoServiceDataProvider.class)
    public void testFindByPages(int request, List<Book> expected) {
        List<Book> actual = bookListDAO.findByPages(request);
        assertEquals(actual, expected);
    }

    @Test(dataProvider = "sortById", dataProviderClass = DaoServiceDataProvider.class)
    public void testSortById(List<Book> expected) {
        List<Book> actual = bookListDAO.sortById();
        assertEquals(actual, expected);
    }

    @Test(dataProvider = "sortByName", dataProviderClass = DaoServiceDataProvider.class)
    public void testSortByName(List<Book> expected) {
        List<Book> actual = bookListDAO.sortByName();
        assertEquals(actual, expected);
    }

    @Test(dataProvider = "sortByAuthor", dataProviderClass = DaoServiceDataProvider.class)
    public void testSortByAuthor(List<Book> expected) {
        List<Book> actual = bookListDAO.sortByAuthor();
        assertEquals(actual, expected);
    }

    @Test(dataProvider = "sortByPublishingHouse", dataProviderClass = DaoServiceDataProvider.class)
    public void testSortByPublishingHouse(List<Book> expected) {
        List<Book> actual = bookListDAO.sortByPublishingHouse();
        assertEquals(actual, expected);
    }

    @Test(dataProvider = "sortByPages", dataProviderClass = DaoServiceDataProvider.class)
    public void testSortByPages(List<Book> expected) {
        List<Book> actual = bookListDAO.sortByPages();
        assertEquals(actual, expected);
    }

    @Test(dataProvider = "findAll", dataProviderClass = DaoServiceDataProvider.class)
    public void testFindAll(List<Book> expected) {
        List<Book> actual = new ArrayList<>(bookListDAO.findAll());
        assertEquals(actual, expected);
    }
}