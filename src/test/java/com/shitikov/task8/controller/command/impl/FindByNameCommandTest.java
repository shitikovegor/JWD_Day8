package com.shitikov.task8.controller.command.impl;

import com.shitikov.task8.dataprovider.CommandDataProvider;
import com.shitikov.task8.dataprovider.LibraryData;
import com.shitikov.task8.model.entity.Book;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static org.testng.Assert.assertEquals;

public class FindByNameCommandTest {
    FindByNameCommand findByNameCommand;

    @BeforeClass
    public void setUp() {
        LibraryData.setLibrary();
        findByNameCommand = new FindByNameCommand();
    }

    @Test(dataProvider = "findByName", dataProviderClass = CommandDataProvider.class)
    public void testExecute(Map<String, String> parameters, Map<String, List<Book>> expected) {
        Map<String, List<Book>> actual = findByNameCommand.execute(parameters);
        assertEquals(actual, expected);
    }
}