package com.shitikov.task8.controller.command.impl;

import com.shitikov.task8.dataprovider.LibraryData;
import com.shitikov.task8.model.entity.Book;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.shitikov.task8.dataprovider.LibraryData.*;
import static org.testng.Assert.*;

public class AddBookCommandTest {
    AddBookCommand addBookCommand;

    @BeforeGroups(groups = "addRemoveBookCommand")
    public void setUpGroup() {
        LibraryData.setLibrary();
    }

    @BeforeMethod(groups = "addRemoveBookCommand")
    public void setUp() {
        addBookCommand = new AddBookCommand();
    }

    @Test(groups = "addRemoveBookCommand", priority = 1)
    public void testExecutePositive() {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("name", "Философия Java");
        parameters.put("author", "Брюс Эккель");
        parameters.put("publishingHouse", "Питер");
        parameters.put("pages", "1168");

        Map<String, List<Book>> actual = addBookCommand.execute(parameters);
        assertTrue(actual.containsKey("Book added to library"));
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
        bookExistParameters.put("author", "Bates B., Sierra K.");
        bookExistParameters.put("publishingHouse", "O Reilly");
        bookExistParameters.put("pages", "688");

        List<Book> expectedList = createExpectedList();
        expectedList.add(BOOK_FOR_ADD_REMOVE);

        return new Object[][]{{"Something went wrong. Parameters are incorrect.", invalidParameters, new ArrayList<>()},
                {"Book didn't add to library", bookExistParameters, expectedList}};
    }

    @Test(groups = "addRemoveBookCommand", dataProvider = "executeNegativeData", priority = 3)
    public void testExecuteNegative(String response, Map<String, String> parameters, List<Book> expectedList) {

        Map<String, List<Book>> actual = addBookCommand.execute(parameters);
        Map<String, List<Book>> expected = new HashMap<>();
        expected.put(response, expectedList);
        assertEquals(actual, expected);
    }
}