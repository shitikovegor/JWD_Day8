package com.shitikov.task6.controller.command;

import com.shitikov.task6.model.entity.Book;

import java.util.List;
import java.util.Map;

public interface Command {
    Map<String, List<Book>> execute(Map<String, String> parameters);
}
