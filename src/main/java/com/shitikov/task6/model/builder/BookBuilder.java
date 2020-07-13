package com.shitikov.task6.model.builder;

import com.shitikov.task6.model.entity.Book;

import java.util.Arrays;
import java.util.List;

public class BookBuilder {
    private String name;
    private List<String> authors;
    private String publishingHouse;
    private int pages;

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

    public BookBuilder buildAuthors(String... authors) {
        this.authors = Arrays.asList(authors);
        return this;
    }

    public BookBuilder buildAuthors(List<String> authors) {
        this.authors = authors;
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
}
