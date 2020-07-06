package com.shitikov.task6.model.entity;

import com.shitikov.task6.model.exception.ProjectException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringJoiner;

public class Library {
    private List<Book> books;
    private static final int MAX_CAPACITY = 1000;

    private static Library instance;

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
        List<Book> copy = copy = Collections.unmodifiableList(books);
        return copy;
    }

    public void add(Book book) throws ProjectException {
        if (book == null) {
            throw new ProjectException("Book is null.");
        }
        if (books.size() == MAX_CAPACITY) {
            throw new ProjectException("No library space.");
        }
        if (books.contains(book)) {
            throw new ProjectException("Book exists in library.");
        }
        books.add(book);
    }

    public void remove(Book book) throws ProjectException {
        if (!books.contains(book)) {
            throw new ProjectException("Book doesn't exist in library.");
        }
        books.remove(book);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;

        Library other = (Library) obj;

        return books != null ? books.equals(other.books) : other.books == null;
    }

    @Override
    public int hashCode() {
        return books != null ? books.hashCode() : 0;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Library.class.getSimpleName() + "[", "]")
                .add("books=" + books)
                .toString();
    }
}
