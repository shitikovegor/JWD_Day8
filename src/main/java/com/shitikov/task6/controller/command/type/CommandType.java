package com.shitikov.task6.controller.command.type;

import com.shitikov.task6.controller.command.Command;
import com.shitikov.task6.controller.command.impl.*;

public enum CommandType {
    ADD_BOOK("add book", new AddBookCommand()),
    REMOVE_BOOK("remove book", new RemoveBookCommand()),
    FIND_BY_ID("find by id", new FindByIdCommand()),
    FIND_BY_NAME("find by name", new FindByNameCommand()),
    FIND_BY_AUTHOR("find by author", new FindByAuthorCommand()),
    FIND_BY_PUBLISHING_HOUSE("find by publishing house", new FindByPublishingHouseCommand()),
    FIND_BY_PAGES("find by pages", new FindByPagesCommand()),
    SORT_BY_ID("sort by id", new SortByIdCommand()),
    SORT_BY_NAME("sort by name", new SortByNameCommand()),
    SORT_BY_AUTHOR("sort by author", new SortByAuthorCommand()),
    SORT_BY_PUBLISHING_HOUSE("sort by publishing house", new SortByPublishingHouseCommand()),
    SORT_BY_PAGES("sort by pages", new SortByPagesCommand()),
    FIND_ALL("find all", new FindAllCommand()),
    EMPTY_COMMAND("find all", new EmptyCommand());

    String name;
    Command command;

    CommandType(String name, Command command) {
        this.name = name;
        this.command = command;
    }

    public String getName() {
        return name;
    }

    public Command getCommand() {
        return command;
    }
}
