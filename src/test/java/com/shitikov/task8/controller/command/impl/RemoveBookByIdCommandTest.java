package com.shitikov.task8.controller.command.impl;

import com.shitikov.task8.model.entity.Book;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.shitikov.task8.dataprovider.LibraryData.BOOK_5;
import static com.shitikov.task8.dataprovider.LibraryData.createExpectedList;
import static org.testng.Assert.assertEquals;

public class RemoveBookByIdCommandTest {
    RemoveBookByIdCommand removeBookByIdCommand;

    @BeforeMethod(groups = "addRemoveBookCommand")
    public void setUp() {
        removeBookByIdCommand = new RemoveBookByIdCommand();

    }

    @Test(groups = "addRemoveBookCommand", priority = 2)
    public void testExecutePositive() {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("id", "5");
        List<Book> expectedBooks = createExpectedList();
        expectedBooks.remove(BOOK_5);

        Map<String, List<Book>> actual = removeBookByIdCommand.execute(parameters);
        Map<String, List<Book>> expected = new HashMap<>();
        expected.put("Book removed from library", expectedBooks);
        assertEquals(actual, expected);
    }

    @DataProvider(name = "executeNegativeData")
    public Object[][] createExceptionData() {
        Map<String, String> invalidParameters = new HashMap<>();
        invalidParameters.put("id", "dsd");

        Map<String, String> bookExistParameters = new HashMap<>();
        bookExistParameters.put("id", "8");
        List<Book> expectedBooks = createExpectedList();
        expectedBooks.remove(BOOK_5);

        return new Object[][]{{"Something went wrong. Parameters are incorrect.", invalidParameters, new ArrayList<>()},
                {"Book doesn't exist in library", bookExistParameters, expectedBooks}};
    }

    @Test(groups = "addRemoveBookCommand", dataProvider = "executeNegativeData", priority = 4)
    public void testExecuteNegative(String response, Map<String, String> parameters, List<Book> expectedList) {

        Map<String, List<Book>> actual = removeBookByIdCommand.execute(parameters);
        Map<String, List<Book>> expected = new HashMap<>();
        expected.put(response, expectedList);
        assertEquals(actual, expected);
    }
}