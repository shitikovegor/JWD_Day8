package com.shitikov.task6.model.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringJoiner;

public class Library {
    private static final int MAX_CAPACITY = 100;
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
        int index = 0;
        boolean bookRemoved = false;
        while (index < books.size() && !bookRemoved) {
            Book element = books.get(index);
            if (book.getName().equals(element.getName())
                    && book.getAuthors().equals(element.getAuthors())
                    && book.getPublishingHouse().equals(element.getPublishingHouse())
                    && book.getPages() == element.getPages()) {
                books.remove(element);
                bookRemoved = true;
            }
            index++;
        }
    }

    public void removeAll() {
        books.clear();
    }

    public int size() {
        return books.size();
    }

    public int getMaxCapacity() {
        return MAX_CAPACITY;
    }

    public boolean contains(Book book) {
        for (Book element : books) {
            if (book.getName().equals(element.getName())
            && book.getAuthors().equals(element.getAuthors())
            && book.getPublishingHouse().equals(element.getPublishingHouse())
            && book.getPages() == element.getPages()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Library.class.getSimpleName() + "[", "]")
                .add("books=" + books)
                .toString();
    }
}
