package com.shitikov.task6.model.entity;

import java.util.*;

public class Book {
    private String id;
    private String name;
    private List<String> authors;
    private String publishingHouse;
    private int pages;

    public Book() {
        this.id = createId();
        this.name = "";
        this.authors = new ArrayList<>();
        this.publishingHouse = "";
        this.pages = 0;
    }

    public Book(String name, List<String> authors, String publishingHouse, int pages) {
        this.id = createId();
        this.name = name;
        this.authors = authors;
        this.publishingHouse = publishingHouse;
        this.pages = pages;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getAuthors() {
        List<String> copy = copy = Collections.unmodifiableList(authors);
        return copy;
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

    private String createId() {
        UUID newId = UUID.randomUUID();
        return newId.toString();
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
        return id == other.id;
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int result = prime + (id != null ? id.hashCode() : 0);
        result = prime * result + (name != null ? name.hashCode() : 0);
        result = prime * result + (authors != null ? authors.hashCode() : 0);
        result = prime * result + (publishingHouse != null ? publishingHouse.hashCode() : 0);
        result = prime * result + pages;
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Book.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("authors=" + authors)
                .add("publishingHouse='" + publishingHouse + "'")
                .add("pages=" + pages)
                .toString();
    }
}
