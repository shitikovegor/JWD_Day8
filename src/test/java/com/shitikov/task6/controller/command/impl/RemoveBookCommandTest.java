package com.shitikov.task6.controller.command.impl;

import com.shitikov.task6.model.entity.Book;
import com.shitikov.task6.model.entity.Library;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.shitikov.task6.dataprovider.LibraryData.*;
import static org.testng.Assert.assertEquals;

public class RemoveBookCommandTest {
    RemoveBookCommand removeBookCommand;

    @BeforeMethod(groups = "addRemoveBookCommand")
    public void setUp() {
        removeBookCommand = new RemoveBookCommand();
    }

    @Test(groups = "addRemoveBookCommand")
    public void testExecutePositive() {
        List<Book> expectedBooks = new ArrayList<>();
        expectedBooks.add(BOOK_1);
        expectedBooks.add(BOOK_2);
        expectedBooks.add(BOOK_3);
        expectedBooks.add(BOOK_4);

        Map<String, String> parameters = new HashMap<>();
        parameters.put("name", "Java Software Solutions");
        parameters.put("author", "Lewis J., Loftus W.");
        parameters.put("publishingHouse", "Pearson");
        parameters.put("pages", "800");

        Map<String, List<Book>> actual = removeBookCommand.execute(parameters);
        Map<String, List<Book>> expected = new HashMap<>();
        expected.put("Book removed from library", expectedBooks);
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
        bookExistParameters.put("name", "Flutter for Beginners");
        bookExistParameters.put("author", "Sierra K., Bates B.");
        bookExistParameters.put("publishingHouse", "Packt Publishing");
        bookExistParameters.put("pages", "688");

        return new Object[][]{{"Something went wrong. Parameter is incorrect.", invalidParameters, new ArrayList<>()},
                {"Book doesn't exist in library", bookExistParameters, Library.getInstance().getBooks()}};
    }

    @Test(groups = "addRemoveBookCommand", dataProvider = "executeNegativeData")
    public void testExecuteNegative(String response, Map<String, String> parameters, List<Book> expectedList) {

        Map<String, List<Book>> actual = removeBookCommand.execute(parameters);
        Map<String, List<Book>> expected = new HashMap<>();
        expected.put(response, expectedList);
        assertEquals(actual, expected);
    }
}