package com.shitikov.task6.service.impl;

import com.shitikov.task6.dataprovider.LibraryData;
import com.shitikov.task6.dataprovider.DaoServiceDataProvider;
import com.shitikov.task6.model.entity.Book;
import com.shitikov.task6.model.entity.Library;
import com.shitikov.task6.service.LibraryService;
import com.shitikov.task6.service.exception.LibraryServiceException;
import org.testng.annotations.*;

import java.util.List;
import java.util.Optional;

import static com.shitikov.task6.dataprovider.LibraryData.*;
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

        Library.getInstance().removeAll();
        Library.getInstance().add(BOOK_1);
        Library.getInstance().add(BOOK_2);
        Library.getInstance().add(BOOK_3);
        Library.getInstance().add(BOOK_4);
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
    public void testAdd() throws LibraryServiceException {
        libraryService.add("Java Software Solutions", "Lewis J., Loftus W.",
                "Pearson", "800");
        assertTrue(Library.getInstance().contains(BOOK_5));
    }

    @DataProvider(name = "removeExceptionData")
    public static Object[][] createRemoveExceptionData() {
        return new Object[][] {{"", "Yaneg", "Public Books", "123"},
                {"Name", " ", "Public Books", "23"},
                {"Name", "Yaneg", "Public Books", "-23"},
                {"Name", "Yaneg", "Public Books", "10500"},
                {"Name", "Yaneg", "Public Books", "sdf"},
                {"Modern Java Recipes", "Kousen K.", "Addison-Wesley Professional", "424"}};
    }

    @Test(groups = "addRemoveBookService", dataProvider = "removeExceptionData",
            expectedExceptions = LibraryServiceException.class)
    public void testRemoveException(String name, String authors, String publishingHouse, String pages) throws LibraryServiceException {
        libraryService.remove(name, authors, publishingHouse, pages);
    }

    @Test(groups = "addRemoveBookService")
    public void testRemove() throws LibraryServiceException {
        libraryService.remove("Go in Practice", "Butcher M., Farina M.",
                "Manning Publications", "312");
        assertFalse(Library.getInstance().contains(BOOK_4));
    }

    @Test(dataProvider = "findById", dataProviderClass = DaoServiceDataProvider.class)
    public void testFindById(String request, java.util.Optional<Book> expected) {
        Optional<Book> actual = libraryService.findById(request);
        assertEquals(actual, expected);
    }

    @Test(dataProvider = "findByName", dataProviderClass = DaoServiceDataProvider.class)
    public void testFindByName(String request, List<Book> expected) {
        List<Book> actual = libraryService.findByName(request);
        assertEquals(actual, expected);
    }

    @Test(dataProvider = "findByAuthor", dataProviderClass = DaoServiceDataProvider.class)
    public void testFindByAuthor(String request, List<Book> expected) {
        List<Book> actual = libraryService.findByAuthor(request);
        assertEquals(actual, expected);
    }

    @Test(dataProvider = "findByPublishingHouse", dataProviderClass = DaoServiceDataProvider.class)
    public void testFindByPublishingHouse(String request, List<Book> expected) {
        List<Book> actual = libraryService.findByPublishingHouse(request);
        assertEquals(actual, expected);
    }

    @Test(dataProvider = "findByPages", dataProviderClass = DaoServiceDataProvider.class)
    public void testFindByPages(String request, List<Book> expected) {
        List<Book> actual = libraryService.findByPages(request);
        assertEquals(actual, expected);
    }

    @Test(dataProvider = "sortById", dataProviderClass = DaoServiceDataProvider.class)
    public void testSortById(List<Book> expected) {
        List<Book> actual = libraryService.sortById();
        assertEquals(actual, expected);
    }

    @Test(dataProvider = "sortByName", dataProviderClass = DaoServiceDataProvider.class)
    public void testSortByName(List<Book> expected) {
        List<Book> actual = libraryService.sortByName();
        assertEquals(actual, expected);
    }

    @Test(dataProvider = "sortByAuthor", dataProviderClass = DaoServiceDataProvider.class)
    public void testSortByAuthor(List<Book> expected) {
        List<Book> actual = libraryService.sortByAuthor();
        assertEquals(actual, expected);
    }

    @Test(dataProvider = "sortByPublishingHouse", dataProviderClass = DaoServiceDataProvider.class)
    public void testSortByPublishingHouse(List<Book> expected) {
        List<Book> actual = libraryService.sortByPublishingHouse();
        assertEquals(actual, expected);
    }

    @Test(dataProvider = "sortByPages", dataProviderClass = DaoServiceDataProvider.class)
    public void testSortByPages(List<Book> expected) {
        List<Book> actual = libraryService.sortByPages();
        assertEquals(actual, expected);
    }

    @Test(dataProvider = "findAll", dataProviderClass = DaoServiceDataProvider.class)
    public void testFindAll(List<Book> expected) {
        List<Book> actual = libraryService.findAll();
        assertEquals(actual, expected);
    }
}