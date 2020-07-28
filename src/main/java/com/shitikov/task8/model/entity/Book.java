package com.shitikov.task8.model.entity;

import com.shitikov.task8.model.builder.BookBuilder;

import java.util.*;

public class Book extends Entity {
    private static final String AUTHOR_DELIMITER = ", ";

    private int bookId;
    private String name;
    private List<String> authors;
    private String publishingHouse;
    private int pages;

    public Book(BookBuilder bookBuilder) {
        this.bookId = bookBuilder.getBookId();
        this.name = bookBuilder.getName();
        this.authors = bookBuilder.getAuthors();
        this.publishingHouse = bookBuilder.getPublishingHouse();
        this.pages = bookBuilder.getPages();
    }

    public Book(int bookId, String name, List<String> authors, String publishingHouse, int pages) {
        this.bookId = bookId;
        this.name = name;
        this.authors = authors;
        this.publishingHouse = publishingHouse;
        this.pages = pages;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getBookId() {
        return bookId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getAuthors() {
        return Collections.unmodifiableList(authors);
    }

    public String getAuthorsAsString() {
        StringBuilder result = new StringBuilder();
        if (authors.size() == 1) {
            result.append(authors.get(0));
        } else {
            for (int i = 0; i < authors.size() - 1; i++) {
                result.append(authors.get(i));
                result.append(AUTHOR_DELIMITER);
            }
            result.append(authors.get(authors.size() - 1));
        }
        return result.toString();
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public String getPublishingHouse() {
        return publishingHouse;
    }

    public void setPublishingHouse(String publishingHouse) {
        this.publishingHouse = publishingHouse;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Book other = (Book) obj;

        if (pages != other.pages) {
            return false;
        }
        if (name != null ? !name.equals(other.name) : other.name != null) {
            return false;
        }
        if (authors != null ? !authors.equals(other.authors) : other.authors != null) {
            return false;
        }
        if (publishingHouse != null ? !publishingHouse.equals(other.publishingHouse) : other.publishingHouse != null) {
            return false;
        }
        return bookId == other.bookId;
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int result = prime + bookId;
        result = prime * result + (name != null ? name.hashCode() : 0);
        result = prime * result + (authors != null ? authors.hashCode() : 0);
        result = prime * result + (publishingHouse != null ? publishingHouse.hashCode() : 0);
        result = prime * result + pages;
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Book.class.getSimpleName() + "[", "]")
                .add("bookId=" + bookId)
                .add("name='" + name + "'")
                .add("authors=" + authors)
                .add("publishingHouse='" + publishingHouse + "'")
                .add("pages=" + pages)
                .toString();
    }
}
