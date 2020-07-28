package com.shitikov.task8.controller.command.impl;

import com.shitikov.task8.model.entity.Book;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.shitikov.task8.dataprovider.LibraryData.*;
import static org.testng.Assert.assertEquals;

public class RemoveBookCommandTest {
    RemoveBookCommand removeBookCommand;

    @BeforeMethod(groups = "addRemoveBookCommand")
    public void setUp() {
        removeBookCommand = new RemoveBookCommand();
    }

    @Test(groups = "addRemoveBookCommand", priority = 2)
    public void testExecutePositive() {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("name", "Философия Java");
        parameters.put("author", "Брюс Эккель");
        parameters.put("publishingHouse", "Питер");
        parameters.put("pages", "1168");

        Map<String, List<Book>> actual = removeBookCommand.execute(parameters);
        Map<String, List<Book>> expected = new HashMap<>();
        expected.put("Book removed from library", createExpectedList());
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

        return new Object[][]{{"Something went wrong. Parameters are incorrect.", invalidParameters, new ArrayList<>()},
                {"Book doesn't exist in library", bookExistParameters, createExpectedList()}};
    }

    @Test(groups = "addRemoveBookCommand", dataProvider = "executeNegativeData", priority = 4)
    public void testExecuteNegative(String response, Map<String, String> parameters, List<Book> expectedList) {

        Map<String, List<Book>> actual = removeBookCommand.execute(parameters);
        Map<String, List<Book>> expected = new HashMap<>();
        expected.put(response, expectedList);
        assertEquals(actual, expected);
    }
}