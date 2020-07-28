package com.shitikov.task8.controller.command.impl;

import com.shitikov.task8.dataprovider.CommandDataProvider;
import com.shitikov.task8.dataprovider.LibraryData;
import com.shitikov.task8.model.entity.Book;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static org.testng.Assert.assertEquals;

public class FindByAuthorCommandTest {
    FindByAuthorCommand findByAuthorCommand;

    @BeforeClass
    public void setUp() {
        LibraryData.setLibrary();
        findByAuthorCommand = new FindByAuthorCommand();
    }

    @Test(dataProvider = "findByAuthor", dataProviderClass = CommandDataProvider.class)
    public void testExecute(Map<String, String> parameters, Map<String, List<Book>> expected) {
        Map<String, List<Book>> actual = findByAuthorCommand.execute(parameters);
        assertEquals(actual, expected);
    }
}