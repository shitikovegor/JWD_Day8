package com.shitikov.task8.service.impl;

import com.shitikov.task8.dataprovider.LibraryData;
import com.shitikov.task8.dataprovider.DaoServiceDataProvider;
import com.shitikov.task8.model.entity.Book;
import com.shitikov.task8.model.service.LibraryService;
import com.shitikov.task8.model.exception.LibraryServiceException;
import com.shitikov.task8.model.service.impl.LibraryServiceImpl;
import com.shitikov.task8.model.entity.KeyType;
import org.testng.annotations.*;

import java.util.List;
import java.util.Optional;

import static org.testng.Assert.*;

public class LibraryServiceImplTest {
    LibraryService libraryService;

    @BeforeClass
    public void setUpLibrary() {
        LibraryData.setLibrary();
    }

    @BeforeMethod
    public void setUp() {
        libraryService = new LibraryServiceImpl();
    }

    @BeforeGroups(groups = "addRemoveBookService")
    public void setUpGroup() {
        libraryService = new LibraryServiceImpl();
    }

    @DataProvider(name = "addExceptionData")
    public static Object[][] createAddExceptionData() {
        return new Object[][] {{"", "Yaneg", "Public Books", "123"},
                {"Name", " ", "Public Books", "23"},
                {"Name", "Yaneg", "Public Books", "-23"},
                {"Name", "Yaneg", "Public Books", "10500"},
                {"Name", "Yaneg", "Public Books", "sdf"}};
    }

    @Test(groups = "addRemoveBookService", dataProvider = "addExceptionData",
            expectedExceptions = LibraryServiceException.class)
    public void testAddException(String name, String authors, String publishingHouse, String pages) throws LibraryServiceException {
        libraryService.add(name, authors, publishingHouse, pages);
    }

    @Test(groups = "addRemoveBookService")
    public void testAddTrue() throws LibraryServiceException {
        assertTrue(libraryService.add("Философия Java", "Брюс Эккель",
                "Питер", "1168"));

    }
    @Test(groups = "addRemoveBookService")
    public void testAddFalse() throws LibraryServiceException {
        assertFalse(libraryService.add("Go in Practice", "Butcher M., Farina M.",
                "Manning Publications", "312"));

    }

    @DataProvider(name = "removeExceptionData")
    public static Object[][] createRemoveExceptionData() {
        return new Object[][] {{"", "Yaneg", "Public Books", "123"},
                {"Name", " ", "Public Books", "23"},
                {"Name", "Yaneg", "Public Books", "-23"},
                {"Name", "Yaneg", "Public Books", "10500"},
                {"Name", "Yaneg", "Public Books", "sdf"},
                {"Modern Java Recipes", "Kousen K.", "", "424"}};
    }

    @Test(groups = "addRemoveBookService", dataProvider = "removeExceptionData",
            expectedExceptions = LibraryServiceException.class)
    public void testRemoveException(String name, String authors, String publishingHouse, String pages) throws LibraryServiceException {
        libraryService.remove(name, authors, publishingHouse, pages);
    }

    @Test(groups = "addRemoveBookService")
    public void testRemoveTrue() throws LibraryServiceException {
        assertTrue(libraryService.remove("Философия Java", "Брюс Эккель",
                "Питер", "1168"));
    }

    @Test(groups = "addRemoveBookService")
    public void testRemoveFalse() throws LibraryServiceException {
        assertFalse(libraryService.remove("Java XML and JSON", "Friesen J.",
                "Apress", "518"));
    }

    @Test(dataProvider = "findById", dataProviderClass = DaoServiceDataProvider.class)
    public void testFindById(String request, java.util.Optional<Book> expected) {
        Optional<Book> actual = libraryService.findById(request);
        assertEquals(actual, expected);
    }

    @Test(dataProvider = "findByKey", dataProviderClass = DaoServiceDataProvider.class)
    public void testFindByKey(KeyType key, String parameter, List<Book> expected) {
        List<Book> actual = libraryService.findByKey(key, parameter);
        assertEquals(actual, expected);
    }

    @Test(dataProvider = "sortByKey", dataProviderClass = DaoServiceDataProvider.class)
    public void testSortByKey(KeyType key, List<Book> expected) throws LibraryServiceException {
        List<Book> actual = libraryService.sortByKey(key);
        assertEquals(actual, expected);
    }

    @Test(dataProvider = "findAll", dataProviderClass = DaoServiceDataProvider.class)
    public void testFindAll(List<Book> expected) {
        List<Book> actual = libraryService.findAll();
        assertEquals(actual, expected);
    }
}