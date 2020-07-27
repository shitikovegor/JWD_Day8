package com.shitikov.task8.model.builder;

import com.shitikov.task8.model.entity.Book;

import java.util.ArrayList;
import java.util.List;

public class BookBuilder {
    private static final String DELIMITER = ",";
    private int bookId;
    private String name;
    private List<String> authors;
    private String publishingHouse;
    private int pages;

    public int getBookId() {
        return bookId;
    }

    public BookBuilder buildBookId(int bookId) {
        this.bookId = bookId;
        return this;
    }

    public String getName() {
        return name;
    }

    public BookBuilder buildName(String name) {
        this.name = name;
        return this;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public BookBuilder buildAuthors(String authors) {
        this.authors = authorsToList(authors);
        return this;
    }

    public String getPublishingHouse() {
        return publishingHouse;
    }

    public BookBuilder buildPublishingHouse(String publishingHouse) {
        this.publishingHouse = publishingHouse;
        return this;
    }

    public int getPages() {
        return pages;
    }

    public BookBuilder buildPages(int pages) {
        this.pages = pages;
        return this;
    }

    public Book buildBook() {
        return new Book(this);
    }

    List<String> authorsToList(String authors) {

        List<String> result = new ArrayList<>();
        String[] authorsForFill = authors.split(DELIMITER);

        for (String author : authorsForFill) {
            result.add(author.strip());
        }
        return result;
    }
}
