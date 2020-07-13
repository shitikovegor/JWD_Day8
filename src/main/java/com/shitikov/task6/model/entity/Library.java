package com.shitikov.task6.model.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringJoiner;

public class Library {
    private static final int MAX_CAPACITY = 1000;
    private static Library instance;
    private List<Book> books;

    private Library() {
        this.books = new ArrayList<>();
    }

    public static Library getInstance() {
        if (instance == null) {
            instance = new Library();
        }
        return instance;
    }

    public List<Book> getBooks() {
        return Collections.unmodifiableList(books);
    }

    public void add(Book book) {
        books.add(book);
    }

    public void remove(Book book) {
        books.remove(book);
    }

    public int size() {
        return books.size();
    }

    public int getMaxCapacity() {
        return MAX_CAPACITY;
    }

    public boolean contains(Book book) {
        return books.contains(book);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Library.class.getSimpleName() + "[", "]")
                .add("books=" + books)
                .toString();
    }
}
