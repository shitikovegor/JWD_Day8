package com.shitikov.task8.controller.command;

import com.shitikov.task8.model.entity.Book;

import java.util.List;
import java.util.Map;

public interface Command {
    Map<String, List<Book>> execute(Map<String, String> parameters);
}
