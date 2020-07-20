package com.shitikov.task6.controller.command.impl;

import com.shitikov.task6.model.builder.BookBuilder;
import com.shitikov.task6.model.entity.Book;
import com.shitikov.task6.model.entity.Library;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.shitikov.task6.dataprovider.LibraryData.*;
import static org.testng.Assert.assertEquals;

public class AddBookCommandTest {
    AddBookCommand addBookCommand;

    @BeforeGroups(groups = "addRemoveBookCommand")
    public void setUpGroup() {
        Library.getInstance().removeAll();
        Library.getInstance().add(BOOK_1);
        Library.getInstance().add(BOOK_2);
        Library.getInstance().add(BOOK_3);
        Library.getInstance().add(BOOK_4);
    }

    @BeforeMethod(groups = "addRemoveBookCommand")
    public void setUp() {
        addBookCommand = new AddBookCommand();
    }

    @Test(groups = "addRemoveBookCommand")
    public void testExecutePositive() {
        List<Book> expectedBooks = new ArrayList<>(Library.getInstance().getBooks());
        Book bookForCheck = new BookBuilder()
                .buildName("Java Software Solutions")
                .buildAuthors("Lewis J., Loftus W.")
                .buildPublishingHouse("Pearson")
                .buildPages(800)
                .buildBook();
        bookForCheck.setBookId("8");
        expectedBooks.add(bookForCheck);

        Map<String, String> parameters = new HashMap<>();
        parameters.put("name", "Java Software Solutions");
        parameters.put("author", "Lewis J., Loftus W.");
        parameters.put("publishingHouse", "Pearson");
        parameters.put("pages", "800");

        Map<String, List<Book>> actual = addBookCommand.execute(parameters);
        Map<String, List<Book>> expected = new HashMap<>();
        expected.put("Book added to library", expectedBooks);
        assertEquals(actual, expected);
    }

    @DataProvider(name = "executeNegativeData")
    public Object[][] createExceptionData() {
        Map<String, String> invalidParameters = new HashMap<>();
        invalidParameters.put("name", "Java Software Solutions");
        invalidParameters.put("author", "Lewis J., Loftus W.");
        invalidParameters.put("publishingHouse", "Pearson");
        invalidParameters.put("pages", "sde");

        Map<String, String> bookExistParameters = new HashMap<>();
        bookExistParameters.put("name", "Head First Java");
        bookExistParameters.put("author", "Sierra K., Bates B.");
        bookExistParameters.put("publishingHouse", "O Reilly");
        bookExistParameters.put("pages", "688");

        return new Object[][]{{"Something went wrong. Parameter is incorrect.", invalidParameters},
                {"Something went wrong. Error of adding book. Book exists in library.", bookExistParameters}};
    }

    @Test(groups = "addRemoveBookCommand", dataProvider = "executeNegativeData")
    public void testExecuteNegative(String response, Map<String, String> parameters) {

        Map<String, List<Book>> actual = addBookCommand.execute(parameters);
        Map<String, List<Book>> expected = new HashMap<>();
        expected.put(response, new ArrayList<>());
        assertEquals(actual, expected);
    }
}