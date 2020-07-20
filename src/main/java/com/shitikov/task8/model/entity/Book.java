package com.shitikov.task8.model.entity;

import com.shitikov.task8.model.builder.BookBuilder;

import java.util.*;

import static com.shitikov.task8.util.IdGenerator.generateId;

public class Book {
    private String bookId;
    private String name;
    private List<String> authors;
    private String publishingHouse;
    private int pages;

    public Book(BookBuilder bookBuilder) {
        this.bookId = generateId();
        this.name = bookBuilder.getName();
        this.authors = bookBuilder.getAuthors();
        this.publishingHouse = bookBuilder.getPublishingHouse();
        this.pages = bookBuilder.getPages();
    }

    public Book() {
        this.bookId = generateId();
        this.name = "";
        this.authors = new ArrayList<>();
        this.publishingHouse = "";
    }

    public Book(String name, List<String> authors, String publishingHouse, int pages) {
        this.bookId = generateId();
        this.name = name;
        this.authors = authors;
        this.publishingHouse = publishingHouse;
        this.pages = pages;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBookId() {
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
        return bookId.equals(other.bookId);
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int result = prime + (bookId != null ? bookId.hashCode() : 0);
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

    public static class AuthorComparator implements Comparator<Book> {
        @Override
        public int compare(Book book1, Book book2) {
            List<String> unmodAuthors1 = book1.getAuthors();
            List<String> unmodAuthors2 = book2.getAuthors();

            List<String> authors1 = new ArrayList<>(unmodAuthors1);
            List<String> authors2 = new ArrayList<>(unmodAuthors2);

            Collections.sort(authors1);
            Collections.sort(authors2);

            Iterator<String> it1 = authors1.iterator();
            Iterator<String> it2 = authors2.iterator();

            while (it1.hasNext()) {
                String author1 = it1.next();
                String author2 = it2.next();
                if (author1.compareTo(author2) != 0) {
                    return author1.compareTo(author2);
                }
            }
            return 0;
        }
    }

    public static class IdComparator implements Comparator<Book> {
        @Override
        public int compare(Book book1, Book book2) {
            return book1.getBookId().compareTo(book2.getBookId());
        }
    }

    public static class NameComparator implements Comparator<Book> {
        @Override
        public int compare(Book book1, Book book2) {
            return book1.getName().compareTo(book2.getName());
        }
    }

    public static class PagesComparator implements Comparator<Book> {
        @Override
        public int compare(Book book1, Book book2) {
            int pages1 = book1.getPages();
            int pages2 = book2.getPages();

            if (pages1 == pages2) {
                return 0;
            }
            return pages1 > pages2 ? 1 : -1;
        }
    }

    public static class PublishingHouseComparator implements Comparator<Book> {
        @Override
        public int compare(Book book1, Book book2) {
            return book1.getPublishingHouse().compareTo(book2.getPublishingHouse());
        }
    }
}
