package com.shitikov.task6.model.dao.impl;

import com.shitikov.task6.model.dao.BookListDAO;
import com.shitikov.task6.model.entity.Book;
import com.shitikov.task6.model.entity.Library;
import com.shitikov.task6.model.exception.BookDAOException;

import java.util.*;
import java.util.stream.Collectors;

public class BookListDAOImpl implements BookListDAO {

    @Override
    public void add(Book book) throws BookDAOException {
        Library library = Library.getInstance();
        if (book == null) {
            throw new BookDAOException("Book is null.");
        }
        if (library.size() == library.getMaxCapacity()) {
            throw new BookDAOException("No library space.");
        }
        if (library.contains(book)) {
            throw new BookDAOException("Book exists in library.");
        }
        library.add(book);
    }

    @Override
    public void remove(Book book) throws BookDAOException {
        Library library = Library.getInstance();
        if (!library.contains(book)) {
            throw new BookDAOException("Book doesn't exist in library.");
        }

        library.remove(book);
    }

    @Override
    public Optional<Book> findById(String id) {
        List<Book> books = Library.getInstance().getBooks();
        for (Book book : books) {
            if (book.getBookId().equals(id)) {
                return Optional.of(book);
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Book> findByName(String name) {
        List<Book> books = Library.getInstance().getBooks();
        List<Book> foundBooks = books.stream().filter(book -> book.getName().contains(name))
                .collect(Collectors.toList());

        return foundBooks;
    }

    @Override
    public List<Book> findByAuthor(String author) {
        List<Book> books = Library.getInstance().getBooks();
        List<Book> foundBooks = new ArrayList<>();

        for (Book book: books) {
            List<String> bookAuthors = book.getAuthors();
            for (String bookAuthor : bookAuthors) {
                if (bookAuthor.contains(author)) {
                    foundBooks.add(book);
                    break;
                }
            }
        }
        return foundBooks;
    }

    @Override
    public List<Book> findByPublishingHouse(String publishingHouse) {
        List<Book> books = Library.getInstance().getBooks();
        List<Book> foundBooks = 
                books.stream().filter(book -> book.getPublishingHouse().contains(publishingHouse))
                .collect(Collectors.toList());

        return foundBooks;
    }

    @Override
    public List<Book> findByPages(int pages) {
        List<Book> books = Library.getInstance().getBooks();
        List<Book> foundBooks = books.stream().filter(book -> book.getPages() == pages)
                .collect(Collectors.toList());

        return foundBooks;
    }

    @Override
    public List<Book> sortById() {
        List<Book> books = fillFromLibrary();
        books.sort(new Book.IdComparator());
        return books;
    }

    @Override
    public List<Book> sortByName() {
        List<Book> books = fillFromLibrary();
        books.sort(new Book.NameComparator());
        return books;
    }

    @Override
    public List<Book> sortByAuthor() {
        List<Book> books = fillFromLibrary();
        books.sort(new Book.AuthorComparator());
        return books;
    }

    @Override
    public List<Book> sortByPublishingHouse() {
        List<Book> books = fillFromLibrary();
        books.sort(new Book.PublishingHouseComparator());
        return books;
    }

    @Override
    public List<Book> sortByPages() {
        List<Book> books = fillFromLibrary();
        books.sort(new Book.PagesComparator());
        return books;
    }

    private List<Book> fillFromLibrary() {
        List<Book> unmodList = Library.getInstance().getBooks();
        return new ArrayList<>(unmodList);
    }

    @Override
    public List<Book> findAll() {
        return Library.getInstance().getBooks();
    }
}
