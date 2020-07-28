package com.shitikov.task8.controller.command.impl;

import com.shitikov.task8.dataprovider.CommandDataProvider;
import com.shitikov.task8.dataprovider.LibraryData;
import com.shitikov.task8.model.entity.Book;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.assertEquals;

public class SortByIdCommandTest {
    SortByIdCommand sortByIdCommand;

    @BeforeClass
    public void setUp() {
        LibraryData.setLibrary();
        sortByIdCommand = new SortByIdCommand();
    }

    @Test(dataProvider = "sortById", dataProviderClass = CommandDataProvider.class)
    public void testExecute(Map<String, List<Book>> expected) {
        Map<String, List<Book>> actual = sortByIdCommand.execute(new HashMap<>());
        assertEquals(actual, expected);
    }
}