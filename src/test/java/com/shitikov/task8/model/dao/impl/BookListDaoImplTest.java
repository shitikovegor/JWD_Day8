package com.shitikov.task8.model.dao.impl;

import com.shitikov.task8.dataprovider.DaoServiceDataProvider;
import com.shitikov.task8.dataprovider.LibraryData;
import com.shitikov.task8.model.builder.BookBuilder;
import com.shitikov.task8.model.dao.BookListDao;
import com.shitikov.task8.model.entity.Book;
import com.shitikov.task8.model.exception.DaoException;
import com.shitikov.task8.model.factory.DAOFactory;
import com.shitikov.task8.model.entity.KeyType;
import org.testng.annotations.*;

import java.util.List;
import java.util.Optional;

import static com.shitikov.task8.dataprovider.LibraryData.BOOK_FOR_ADD_REMOVE;
import static org.testng.Assert.*;

public class BookListDaoImplTest {
    BookListDao bookListDAO;

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

    }

    @Test(groups = "addRemoveBookDao", expectedExceptions = DaoException.class)
    public void testAddException() throws DaoException {
        bookListDAO.add(null);
    }

    @Test(groups = "addRemoveBookDao")
    public void testAddTrue() throws DaoException {
        assertTrue(bookListDAO.add(BOOK_FOR_ADD_REMOVE));
    }

    @Test(groups = "addRemoveBookDao")
    public void testAddFalse() throws DaoException {
        Book bookForAdd = new BookBuilder()
                .buildName("Go in Practice")
                .buildAuthors("Butcher M., Farina M.")
                .buildPublishingHouse("Manning Publications")
                .buildPages(312)
                .buildBook();
        assertFalse(bookListDAO.add(bookForAdd));
    }

    @Test(groups = "addRemoveBookDao")
    public void testRemoveTrue() throws DaoException {
        assertTrue(bookListDAO.remove(BOOK_FOR_ADD_REMOVE));
    }

    @Test(groups = "addRemoveBookDao")
    public void testRemoveFalse() throws DaoException {
        Book bookForRemove = new BookBuilder()
                .buildName("Java XML and JSON")
                .buildAuthors("Friesen J.")
                .buildPublishingHouse("Apress")
                .buildPages(518)
                .buildBook();
        assertFalse(bookListDAO.remove(bookForRemove));
    }

    @Test(dataProvider = "findById", dataProviderClass = DaoServiceDataProvider.class)
    public void testFindById(String request, Optional<Book> expected) throws DaoException {
        Optional<Book> actual = bookListDAO.findById(Integer.parseInt(request));
        assertEquals(actual, expected);
    }

    @Test(dataProvider = "findByKey", dataProviderClass = DaoServiceDataProvider.class)
    public void testFindByKey(KeyType key, String parameter, List<Book> expected) throws DaoException {
        List<Book> actual = bookListDAO.findByKey(key, parameter);
        assertEquals(actual, expected);
    }

    @Test(dataProvider = "sortByKey", dataProviderClass = DaoServiceDataProvider.class)
    public void testSortByKey(KeyType key, List<Book> expected) throws DaoException {
        List<Book> actual = bookListDAO.sortByKey(key);
        assertEquals(actual, expected);
    }

    @Test(dataProvider = "findAll", dataProviderClass = DaoServiceDataProvider.class)
    public void testFindAll(List<Book> expected) throws DaoException {
        List<Book> actual = bookListDAO.findAll();
        assertEquals(actual, expected);
    }
}