package com.shitikov.task8.dataprovider;

import com.shitikov.task8.model.entity.Book;
import com.shitikov.task8.model.entity.KeyType;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.shitikov.task8.dataprovider.LibraryData.*;

public class DaoServiceDataProvider {

    @DataProvider(name = "findById")
    public static Object[][] createFindById() {
        return new Object[][]{{"1", Optional.of(BOOK_1)},
                {"78", Optional.empty()}};
    }

    @DataProvider(name = "findByKey")
    public static Object[][] createFindByName() {
        List<Book> foundBooksSingle = new ArrayList<>();
        foundBooksSingle.add(BOOK_1);
        List<Book> foundBooksSome = new ArrayList<>();
        foundBooksSome.add(BOOK_3);
        foundBooksSome.add(BOOK_5);
        return new Object[][]{{KeyType.NAME, "Maven", foundBooksSingle},
                {KeyType.NAME, "Java", foundBooksSome},
                {KeyType.NAME, "Flutter for Beginners", new ArrayList<>()},
                {KeyType.AUTHOR, "Varanasi B.", foundBooksSingle},
                {KeyType.AUTHOR, "Biessek A.", new ArrayList<>()},
                {KeyType.PUBLISHING_HOUSE, "Apress", foundBooksSingle},
                {KeyType.PUBLISHING_HOUSE, "Packt Publishing", new ArrayList<>()},
                {KeyType.PAGES, "135", foundBooksSingle},
                {KeyType.PAGES, "1025", new ArrayList<>()}};
    }

    @DataProvider(name = "sortByKey")
    public static Object[][] createSortById() {
        List<Book> sortedBooksById = new ArrayList<>();
        sortedBooksById.add(BOOK_1);
        sortedBooksById.add(BOOK_2);
        sortedBooksById.add(BOOK_3);
        sortedBooksById.add(BOOK_4);
        sortedBooksById.add(BOOK_5);

        List<Book> sortedBooksByName = new ArrayList<>();
        sortedBooksByName.add(BOOK_4);
        sortedBooksByName.add(BOOK_3);
        sortedBooksByName.add(BOOK_1);
        sortedBooksByName.add(BOOK_5);
        sortedBooksByName.add(BOOK_2);

        List<Book> sortedBooksByAuthor = new ArrayList<>();
        sortedBooksByAuthor.add(BOOK_3);
        sortedBooksByAuthor.add(BOOK_4);
        sortedBooksByAuthor.add(BOOK_2);
        sortedBooksByAuthor.add(BOOK_5);
        sortedBooksByAuthor.add(BOOK_1);

        List<Book> sortedBooksByPublishingHouse = new ArrayList<>();
        sortedBooksByPublishingHouse.add(BOOK_1);
        sortedBooksByPublishingHouse.add(BOOK_4);
        sortedBooksByPublishingHouse.add(BOOK_2);
        sortedBooksByPublishingHouse.add(BOOK_3);
        sortedBooksByPublishingHouse.add(BOOK_5);

        List<Book> sortedBooksByPages = new ArrayList<>();
        sortedBooksByPages.add(BOOK_1);
        sortedBooksByPages.add(BOOK_4);
        sortedBooksByPages.add(BOOK_2);
        sortedBooksByPages.add(BOOK_3);
        sortedBooksByPages.add(BOOK_5);

        return new Object[][]{{KeyType.ID, sortedBooksById},
                {KeyType.NAME, sortedBooksByName},
                {KeyType.AUTHOR, sortedBooksByAuthor},
                {KeyType.PUBLISHING_HOUSE, sortedBooksByPublishingHouse},
                {KeyType.PAGES, sortedBooksByPages}};
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
