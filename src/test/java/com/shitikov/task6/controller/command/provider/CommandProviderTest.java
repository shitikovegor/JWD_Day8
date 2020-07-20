package com.shitikov.task6.controller.command.provider;

import com.shitikov.task6.controller.command.Command;
import com.shitikov.task6.dataprovider.CommandDataProvider;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class CommandProviderTest {

    @Test(dataProvider = "commands", dataProviderClass = CommandDataProvider.class)
    public void testDefineCommand(String commandName, Command expected) {
        Command actual = CommandProvider.defineCommand(commandName);
        assertEquals(actual.getClass(), expected.getClass());
    }
}