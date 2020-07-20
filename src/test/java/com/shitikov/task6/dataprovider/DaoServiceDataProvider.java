package com.shitikov.task6.dataprovider;

import com.shitikov.task6.model.entity.Book;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.shitikov.task6.dataprovider.LibraryData.*;

public class DaoServiceDataProvider {

    @DataProvider(name = "findById")
    public static Object[][] createFindById() {
        return new Object[][]{{"1", Optional.of(BOOK_1)},
                {"78", Optional.empty()}};
    }

    @DataProvider(name = "findByName")
    public static Object[][] createFindByName() {
        List<Book> foundBooksSingle = new ArrayList<>();
        foundBooksSingle.add(BOOK_1);
        List<Book> foundBooksSome = new ArrayList<>();
        foundBooksSome.add(BOOK_3);
        foundBooksSome.add(BOOK_5);
        return new Object[][]{{"Maven", foundBooksSingle},
                {"Java", foundBooksSome},
                {"Flutter for Beginners", new ArrayList<>()}};
    }

    @DataProvider(name = "findByAuthor")
    public static Object[][] createFindByAuthor() {
        List<Book> foundBooks = new ArrayList<>();
        foundBooks.add(BOOK_1);
        return new Object[][]{{"Varanasi B.", foundBooks},
                {"Biessek A.", new ArrayList<>()}};
    }

    @DataProvider(name = "findByPublishingHouse")
    public static Object[][] createFindByPublishingHouse() {
        List<Book> foundBooks = new ArrayList<>();
        foundBooks.add(BOOK_1);
        return new Object[][]{{"Apress", foundBooks},
                {"Packt Publishing", new ArrayList<>()}};
    }

    @DataProvider(name = "findByPagesDao")
    public static Object[][] createFindByPagesDao() {
        List<Book> foundBooks = new ArrayList<>();
        foundBooks.add(BOOK_1);
        return new Object[][]{{135, foundBooks},
                {1025, new ArrayList<>()}};
    }

    @DataProvider(name = "findByPages")
    public static Object[][] createFindByPages() {
        List<Book> foundBooks = new ArrayList<>();
        foundBooks.add(BOOK_1);
        return new Object[][]{{"135", foundBooks},
                {"1025", new ArrayList<>()}};
    }

    @DataProvider(name = "sortById")
    public static Object[] createSortById() {
        List<Book> sortedBooks = new ArrayList<>();
        sortedBooks.add(BOOK_1);
        sortedBooks.add(BOOK_2);
        sortedBooks.add(BOOK_3);
        sortedBooks.add(BOOK_4);
        sortedBooks.add(BOOK_5);

        return new Object[]{sortedBooks};
    }

    @DataProvider(name = "sortByName")
    public static Object[] createSortByName() {
        List<Book> sortedBooks = new ArrayList<>();
        sortedBooks.add(BOOK_4);
        sortedBooks.add(BOOK_3);
        sortedBooks.add(BOOK_1);
        sortedBooks.add(BOOK_5);
        sortedBooks.add(BOOK_2);

        return new Object[]{sortedBooks};
    }

    @DataProvider(name = "sortByAuthor")
    public static Object[] createSortByAuthor() {
        List<Book> sortedBooks = new ArrayList<>();
        sortedBooks.add(BOOK_3);
        sortedBooks.add(BOOK_4);
        sortedBooks.add(BOOK_2);
        sortedBooks.add(BOOK_5);
        sortedBooks.add(BOOK_1);

        return new Object[]{sortedBooks};
    }

    @DataProvider(name = "sortByPublishingHouse")
    public static Object[] createSortByPublishingHouse() {
        List<Book> sortedBooks = new ArrayList<>();
        sortedBooks.add(BOOK_1);
        sortedBooks.add(BOOK_4);
        sortedBooks.add(BOOK_2);
        sortedBooks.add(BOOK_3);
        sortedBooks.add(BOOK_5);

        return new Object[]{sortedBooks};
    }

    @DataProvider(name = "sortByPages")
    public static Object[] createSortByPages() {
        List<Book> sortedBooks = new ArrayList<>();
        sortedBooks.add(BOOK_1);
        sortedBooks.add(BOOK_4);
        sortedBooks.add(BOOK_2);
        sortedBooks.add(BOOK_3);
        sortedBooks.add(BOOK_5);

        return new Object[]{sortedBooks};
    }

    @DataProvider(name = "findAll")
    public static Object[] createFindAll() {
        List<Book> foundBooks = new ArrayList<>();
        foundBooks.add(BOOK_1);
        foundBooks.add(BOOK_2);
        foundBooks.add(BOOK_3);
        foundBooks.add(BOOK_4);
        foundBooks.add(BOOK_5);

        return new Object[]{foundBooks};
    }
}
