package com.shitikov.task8.controller.command.impl;

import com.shitikov.task8.dataprovider.CommandDataProvider;
import com.shitikov.task8.dataprovider.LibraryData;
import com.shitikov.task8.model.entity.Book;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.*;

public class SortByNameCommandTest {
    SortByNameCommand sortByNameCommand;

    @BeforeClass
    public void setUp() {
        LibraryData.setLibrary();
        sortByNameCommand = new SortByNameCommand();
    }

    @Test(dataProvider = "sortByName", dataProviderClass = CommandDataProvider.class)
    public void testExecute(Map<String, List<Book>> expected) {
        Map<String, List<Book>> actual = sortByNameCommand.execute(new HashMap<>());
        assertEquals(actual, expected);
    }
}