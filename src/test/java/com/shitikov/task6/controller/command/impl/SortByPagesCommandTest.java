package com.shitikov.task6.controller.command.impl;

import com.shitikov.task6.dataprovider.CommandDataProvider;
import com.shitikov.task6.dataprovider.LibraryData;
import com.shitikov.task6.model.entity.Book;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.assertEquals;

public class SortByPagesCommandTest {
    SortByPagesCommand sortByPagesCommand;

    @BeforeClass
    public void setUp() {
        LibraryData.setLibrary();
        sortByPagesCommand = new SortByPagesCommand();
    }

    @Test(dataProvider = "sortByPages", dataProviderClass = CommandDataProvider.class)
    public void testExecute(Map<String, List<Book>> expected) {
        Map<String, List<Book>> actual = sortByPagesCommand.execute(new HashMap<>());
        assertEquals(actual, expected);
    }
}