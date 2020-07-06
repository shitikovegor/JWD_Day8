package com.shitikov.task6.model.dao.impl;

import com.shitikov.task6.model.dao.BookListDao;
import com.shitikov.task6.model.dao.impl.comparator.AuthorComparator;
import com.shitikov.task6.model.dao.impl.comparator.IdComparator;
import com.shitikov.task6.model.dao.impl.comparator.PagesComparator;
import com.shitikov.task6.model.dao.impl.comparator.PublishingHouseComparator;
import com.shitikov.task6.model.entity.Book;
import com.shitikov.task6.model.entity.Library;
import com.shitikov.task6.model.exception.ProjectException;

import java.util.*;
import java.util.stream.Collectors;

public class BookListDaoImpl implements BookListDao {
    private Library library;

    public BookListDaoImpl() {
        this.library = Library.getInstance();
    }

    @Override
    public void addBook(Book book) throws ProjectException {
        library.add(book);
    }

    @Override
    public void removeBook(Book book) throws ProjectException {
        library.remove(book);
    }

    @Override
    public Optional<Book> findById(String id) {
        List<Book> books = library.getBooks();
        for (Book book : books) {
            if (book.getId().equals(id)) {
                return Optional.of(book);
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Book> findByName(String name) {
        List<Book> books = library.getBooks();
        List<Book> foundBooks = books.stream().filter(book -> book.getName().contains(name))
                .collect(Collectors.toList());

        return foundBooks;
    }

    @Override
    public List<Book> findByAuthor(String author) {
        List<Book> books = library.getBooks();
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
        List<Book> books = library.getBooks();
        List<Book> foundBooks = 
                books.stream().filter(book -> book.getPublishingHouse().contains(publishingHouse))
                .collect(Collectors.toList());

        return foundBooks;
    }

    @Override
    public List<Book> findByPages(int pages) {
        List<Book> books = library.getBooks();
        List<Book> foundBooks = books.stream().filter(book -> book.getPages() == pages)
                .collect(Collectors.toList());

        return foundBooks;
    }

    @Override
    public List<Book> sortBooksById() {
        List<Book> books = fillFromLibrary();
        books.sort(new IdComparator());
        return books;
    }

    // TODO: 06.07.2020 what is better - Comparator.comparing or Class Comparator?
    @Override
    public List<Book> sortBooksByName() {
        List<Book> books = fillFromLibrary();
        books.sort(Comparator.comparing(Book::getName));
        return books;
    }

    @Override
    public List<Book> sortBooksByAuthor() {
        List<Book> books = fillFromLibrary();
        books.sort(new AuthorComparator());
        return books;
    }

    @Override
    public List<Book> sortBooksByPublishingHouse() {
        List<Book> books = fillFromLibrary();
        books.sort(new PublishingHouseComparator());
        return books;
    }

    @Override
    public List<Book> sortBooksByPages() {
        List<Book> books = fillFromLibrary();
        books.sort(new PagesComparator());
        return books;
    }

    private List<Book> fillFromLibrary() {
        List<Book> unmodList = library.getBooks();
        List<Book> books = new ArrayList<>(unmodList);

        return books;
    }
}
