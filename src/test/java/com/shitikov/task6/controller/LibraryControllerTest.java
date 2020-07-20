package com.shitikov.task6.controller;

import com.shitikov.task6.dataprovider.CommandDataProvider;
import com.shitikov.task6.dataprovider.LibraryData;
import com.shitikov.task6.model.builder.BookBuilder;
import com.shitikov.task6.model.entity.Book;
import com.shitikov.task6.model.entity.Library;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.shitikov.task6.dataprovider.LibraryData.*;
import static org.testng.Assert.*;

public class LibraryControllerTest {

    @BeforeClass
    public void setUp() {
        LibraryData.setLibrary();
    }

    @DataProvider(name = "controllerData")
    public Object[][] createData() {
        List<Book> expectedBooks = new ArrayList<>(Library.getInstance().getBooks());
        Book bookForCheck = new BookBuilder()
                .buildName("Learn Java for Android Development")
                .buildAuthors("Friesen J., Späth P.")
                .buildPublishingHouse("CreateSpace")
                .buildPages(710)
                .buildBook();
        bookForCheck.setBookId("7");
        expectedBooks.add(bookForCheck);

        Map<String, String> bookParameters = new HashMap<>();
        bookParameters.put("name", "Learn Java for Android Development");
        bookParameters.put("author", "Friesen J., Späth P.");
        bookParameters.put("publishingHouse", "CreateSpace");
        bookParameters.put("pages", "710");

        Map<String, List<Book>> addExpected = new HashMap<>();
        addExpected.put("Book added to library", expectedBooks);

        Map<String, List<Book>> removeExpected = new HashMap<>();
        removeExpected.put("Book removed from library", new ArrayList<>(Library.getInstance().getBooks()));

        Map<String, String> idParameters = new HashMap<>();
        idParameters.put("id", "1");
        List<Book> idExpectedList = new ArrayList<>();
        idExpectedList.add(BOOK_1);
        Map<String, List<Book>> findIdExpected = new HashMap<>();
        findIdExpected.put("Books found", idExpectedList);

        Map<String, String> nameParameters = new HashMap<>();
        nameParameters.put("name", "Maven");
        List<Book> nameExpectedList = new ArrayList<>();
        nameExpectedList.add(BOOK_1);
        Map<String, List<Book>> findNameExpected = new HashMap<>();
        findNameExpected.put("Books found", nameExpectedList);

        Map<String, String> authorParameters = new HashMap<>();
        authorParameters.put("author", "Varanasi B.");
        List<Book> authorExpectedList = new ArrayList<>();
        authorExpectedList.add(BOOK_1);
        Map<String, List<Book>> findAuthorExpected = new HashMap<>();
        findAuthorExpected.put("Books found", authorExpectedList);

        Map<String, String> publHouseParameters = new HashMap<>();
        publHouseParameters.put("publishingHouse", "Apress");
        List<Book> publHouseExpectedList = new ArrayList<>();
        publHouseExpectedList.add(BOOK_1);
        Map<String, List<Book>> findPublHouseExpected = new HashMap<>();
        findPublHouseExpected.put("Books found", publHouseExpectedList);

        Map<String, String> pagesParameters = new HashMap<>();
        pagesParameters.put("pages", "135");
        List<Book> pagesExpectedList = new ArrayList<>();
        pagesExpectedList.add(BOOK_1);
        Map<String, List<Book>> findPagesExpected = new HashMap<>();
        findPagesExpected.put("Books found", pagesExpectedList);

        List<Book> sortedById = new ArrayList<>();
        sortedById.add(BOOK_1);
        sortedById.add(BOOK_2);
        sortedById.add(BOOK_3);
        sortedById.add(BOOK_4);
        sortedById.add(BOOK_5);

        Map<String, List<Book>> byIdExpected = new HashMap<>();
        byIdExpected.put("Books sorted by ID", sortedById);

        List<Book> sortedByName = new ArrayList<>();
        sortedByName.add(BOOK_4);
        sortedByName.add(BOOK_3);
        sortedByName.add(BOOK_1);
        sortedByName.add(BOOK_5);
        sortedByName.add(BOOK_2);

        Map<String, List<Book>> byNameExpected = new HashMap<>();
        byNameExpected.put("Books sorted by name", sortedByName);

        List<Book> sortedByAuthor = new ArrayList<>();
        sortedByAuthor.add(BOOK_3);
        sortedByAuthor.add(BOOK_4);
        sortedByAuthor.add(BOOK_2);
        sortedByAuthor.add(BOOK_5);
        sortedByAuthor.add(BOOK_1);

        Map<String, List<Book>> byAuthorExpected = new HashMap<>();
        byAuthorExpected.put("Books sorted by author", sortedByAuthor);

        List<Book> sortedByPublHouse = new ArrayList<>();
        sortedByPublHouse.add(BOOK_1);
        sortedByPublHouse.add(BOOK_4);
        sortedByPublHouse.add(BOOK_2);
        sortedByPublHouse.add(BOOK_3);
        sortedByPublHouse.add(BOOK_5);

        Map<String, List<Book>> byPublHouseExpected = new HashMap<>();
        byPublHouseExpected.put("Books sorted by publishing house", sortedByPublHouse);

        List<Book> sortedByPages = new ArrayList<>();
        sortedByPages.add(BOOK_1);
        sortedByPages.add(BOOK_4);
        sortedByPages.add(BOOK_2);
        sortedByPages.add(BOOK_3);
        sortedByPages.add(BOOK_5);

        Map<String, List<Book>> byPagesExpected = new HashMap<>();
        byPagesExpected.put("Books sorted by number of pages", sortedByPages);

        Map<String, List<Book>> emptyCommandExpected = new HashMap<>();
        emptyCommandExpected.put("Something went wrong. ", new ArrayList<>());

        return new Object[][]{{"add book", bookParameters, addExpected},
                {"remove book", bookParameters, removeExpected},
                {"find by id", idParameters, findIdExpected},
                {"find by name", nameParameters, findNameExpected},
                {"find by author", authorParameters, findAuthorExpected},
                {"find by publishing house", publHouseParameters, findPublHouseExpected},
                {"find by pages", pagesParameters, findPagesExpected},
                {"sort by id", new HashMap<>(), byIdExpected},
                {"sort by name", new HashMap<>(), byNameExpected},
                {"sort by author", new HashMap<>(), byAuthorExpected},
                {"sort by publishing house", new HashMap<>(), byPublHouseExpected},
                {"sort by pages", new HashMap<>(), byPagesExpected},
                {"find all", new HashMap<>(), Library.getInstance().getBooks()},
                {"new command", new HashMap<>(), emptyCommandExpected}};
    }


    @Test(dataProvider = "controllerData")
    public void testProcessRequest(String commandName, Map<String, String> parameters,
                                   Map<String, List<Book>> expected) {

        Map<String, List<Book>> actual = LibraryController.getInstance().processRequest(commandName, parameters);
        assertEquals(actual, expected);
    }
}