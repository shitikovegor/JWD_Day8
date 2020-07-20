package com.shitikov.task6.controller.command.impl;

import com.shitikov.task6.dataprovider.CommandDataProvider;
import com.shitikov.task6.dataprovider.LibraryData;
import com.shitikov.task6.model.entity.Book;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static org.testng.Assert.assertEquals;

public class FindByPagesCommandTest {
    FindByPagesCommand findByPagesCommand;

    @BeforeClass
    public void setUp() {
        LibraryData.setLibrary();
        findByPagesCommand = new FindByPagesCommand();
    }

    @Test(dataProvider = "findByPages", dataProviderClass = CommandDataProvider.class)
    public void testExecute(Map<String, String> parameters, Map<String, List<Book>> expected) {
        Map<String, List<Book>> actual = findByPagesCommand.execute(parameters);
        assertEquals(actual, expected);
    }
}