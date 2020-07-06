package com.shitikov.task6.model.dao.impl.comparator;

import com.shitikov.task6.model.entity.Book;

import java.util.Comparator;

public class NameComparator implements Comparator<Book> {
    @Override
    public int compare(Book book1, Book book2) {
        return book1.getName().compareTo(book2.getName());
    }
}
