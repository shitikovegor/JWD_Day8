package com.shitikov.task8.dataprovider;

import com.shitikov.task8.controller.command.impl.*;
import com.shitikov.task8.model.entity.Book;
import org.testng.annotations.DataProvider;

import java.util.*;

import static com.shitikov.task8.dataprovider.LibraryData.*;
import static com.shitikov.task8.dataprovider.LibraryData.BOOK_5;

public class CommandDataProvider {
    @DataProvider(name = "findById")
    public static Object[][] createFindById() {
        Map<String, String> parametersPositive = new HashMap<>();
        parametersPositive.put("id", "1");
        List<Book> expectedList = new ArrayList<>();
        expectedList.add(BOOK_1);
        Map<String, List<Book>> foundResponse = new HashMap<>();
        foundResponse.put("Books found", expectedList);

        Map<String, String> parametersNegative = new HashMap<>();
        parametersNegative.put("id", "78");
        Map<String, List<Book>> notFoundResponse = new HashMap<>();
        notFoundResponse.put("Book not found", new ArrayList<>());

        Map<String, String> parametersInvalidKey = new HashMap<>();
        parametersInvalidKey.put("ident", "2");

        return new Object[][]{{parametersPositive, foundResponse},
                {parametersNegative, notFoundResponse},
                {parametersInvalidKey, new HashMap<>()}};
    }

    @DataProvider(name = "findByName")
    public static Object[][] createFindByName() {
        Map<String, String> parametersPositive = new HashMap<>();
        parametersPositive.put("name", "Maven");
        List<Book> expectedList = new ArrayList<>();
        expectedList.add(BOOK_1);
        Map<String, List<Book>> foundResponse = new HashMap<>();
        foundResponse.put("Books found", expectedList);

        Map<String, String> parametersNegative = new HashMap<>();
        parametersNegative.put("name", "Harry Potter");
        Map<String, List<Book>> notFoundResponse = new HashMap<>();
        notFoundResponse.put("Books found", new ArrayList<>());

        Map<String, String> parametersInvalidKey = new HashMap<>();
        parametersInvalidKey.put("nameOfBook", "Flutter for Beginners");

        return new Object[][]{{parametersPositive, foundResponse},
                {parametersNegative, notFoundResponse},
                {parametersInvalidKey, new HashMap<>()}};
    }

    @DataProvider(name = "findByAuthor")
    public static Object[][] createFindByAuthor() {
        Map<String, String> parametersPositive = new HashMap<>();
        parametersPositive.put("author", "Varanasi B.");
        List<Book> expectedList = new ArrayList<>();
        expectedList.add(BOOK_1);
        Map<String, List<Book>> foundResponse = new HashMap<>();
        foundResponse.put("Books found", expectedList);

        Map<String, String> parametersNegative = new HashMap<>();
        parametersNegative.put("author", "Biessek A.");
        Map<String, List<Book>> notFoundResponse = new HashMap<>();
        notFoundResponse.put("Books found", new ArrayList<>());

        Map<String, String> parametersInvalidKey = new HashMap<>();
        parametersInvalidKey.put("bookAuthor", "Sierra. K");

        return new Object[][]{{parametersPositive, foundResponse},
                {parametersNegative, notFoundResponse},
                {parametersInvalidKey, new HashMap<>()}};
    }

    @DataProvider(name = "findByPublishingHouse")
    public static Object[][] createFindByPublishingHouse() {
        Map<String, String> parametersPositive = new HashMap<>();
        parametersPositive.put("publishingHouse", "Apress");
        List<Book> expectedList = new ArrayList<>();
        expectedList.add(BOOK_1);
        Map<String, List<Book>> foundResponse = new HashMap<>();
        foundResponse.put("Books found", expectedList);

        Map<String, String> parametersNegative = new HashMap<>();
        parametersNegative.put("publishingHouse", "Packt Publishing");
        Map<String, List<Book>> notFoundResponse = new HashMap<>();
        notFoundResponse.put("Books found", new ArrayList<>());

        Map<String, String> parametersInvalidKey = new HashMap<>();
        parametersInvalidKey.put("publishing Houses", "Packt Publishing");

        return new Object[][]{{parametersPositive, foundResponse},
                {parametersNegative, notFoundResponse},
                {parametersInvalidKey, new HashMap<>()}};
    }

    @DataProvider(name = "findByPages")
    public static Object[][] createFindByPages() {
        Map<String, String> parametersPositive = new HashMap<>();
        parametersPositive.put("pages", "135");
        List<Book> expectedList = new ArrayList<>();
        expectedList.add(BOOK_1);
        Map<String, List<Book>> foundResponse = new HashMap<>();
        foundResponse.put("Books found", expectedList);

        Map<String, String> parametersNegative = new HashMap<>();
        parametersNegative.put("pages", "1025");
        Map<String, List<Book>> notFoundResponse = new HashMap<>();
        notFoundResponse.put("Books found", new ArrayList<>());

        Map<String, String> parametersInvalidKey = new HashMap<>();
        parametersInvalidKey.put("pagesNumber", "225");

        return new Object[][]{{parametersPositive, foundResponse},
                {parametersNegative, notFoundResponse},
                {parametersInvalidKey, new HashMap<>()}};
    }

    @DataProvider(name = "sortById")
    public static Object[] createSortById() {
        List<Book> sortedBooks = new ArrayList<>();
        sortedBooks.add(BOOK_1);
        sortedBooks.add(BOOK_2);
        sortedBooks.add(BOOK_3);
        sortedBooks.add(BOOK_4);
        sortedBooks.add(BOOK_5);

        Map<String, List<Book>> sortedResponse = new HashMap<>();
        sortedResponse.put("Books sorted by id", sortedBooks);

        return new Object[]{sortedResponse};
    }

    @DataProvider(name = "sortByName")
    public static Object[] createSortByName() {
        List<Book> sortedBooks = new ArrayList<>();
        sortedBooks.add(BOOK_4);
        sortedBooks.add(BOOK_3);
        sortedBooks.add(BOOK_1);
        sortedBooks.add(BOOK_5);
        sortedBooks.add(BOOK_2);

        Map<String, List<Book>> sortedResponse = new HashMap<>();
        sortedResponse.put("Books sorted by name", sortedBooks);

        return new Object[]{sortedResponse};
    }

    @DataProvider(name = "sortByAuthor")
    public static Object[] createSortByAuthor() {
        List<Book> sortedBooks = new ArrayList<>();
        sortedBooks.add(BOOK_3);
        sortedBooks.add(BOOK_4);
        sortedBooks.add(BOOK_2);
        sortedBooks.add(BOOK_5);
        sortedBooks.add(BOOK_1);

        Map<String, List<Book>> sortedResponse = new HashMap<>();
        sortedResponse.put("Books sorted by author", sortedBooks);

        return new Object[]{sortedResponse};
    }

    @DataProvider(name = "sortByPublishingHouse")
    public static Object[] createSortByPublishingHouse() {
        List<Book> sortedBooks = new ArrayList<>();
        sortedBooks.add(BOOK_1);
        sortedBooks.add(BOOK_4);
        sortedBooks.add(BOOK_2);
        sortedBooks.add(BOOK_3);
        sortedBooks.add(BOOK_5);

        Map<String, List<Book>> sortedResponse = new HashMap<>();
        sortedResponse.put("Books sorted by publishingHouse", sortedBooks);

        return new Object[]{sortedResponse};
    }

    @DataProvider(name = "sortByPages")
    public static Object[] createSortByPages() {
        List<Book> sortedBooks = new ArrayList<>();
        sortedBooks.add(BOOK_1);
        sortedBooks.add(BOOK_4);
        sortedBooks.add(BOOK_2);
        sortedBooks.add(BOOK_3);
        sortedBooks.add(BOOK_5);

        Map<String, List<Book>> sortedResponse = new HashMap<>();
        sortedResponse.put("Books sorted by pages", sortedBooks);

        return new Object[]{sortedResponse};
    }

    @DataProvider(name = "findAll")
    public static Object[] createFindAll() {
        List<Book> foundBooks = new ArrayList<>();
        foundBooks.add(BOOK_1);
        foundBooks.add(BOOK_2);
        foundBooks.add(BOOK_3);
        foundBooks.add(BOOK_4);
        foundBooks.add(BOOK_5);

        Map<String, List<Book>> sortedResponse = new HashMap<>();
        sortedResponse.put("Books found", foundBooks);

        return new Object[]{sortedResponse};
    }

    @DataProvider(name = "commands")
    public static Object[][] createCommands() {

        return new Object[][]{{"add book", new AddBookCommand()},
                {"remove book", new RemoveBookCommand()},
                {"find by id", new FindByIdCommand()},
                {"find by name", new FindByNameCommand()},
                {"find by author", new FindByAuthorCommand()},
                {"find by publishing house", new FindByPublishingHouseCommand()},
                {"find by pages", new FindByPagesCommand()},
                {"sort by id", new SortByIdCommand()},
                {"sort by name", new SortByNameCommand()},
                {"sort by author", new SortByAuthorCommand()},
                {"sort by publishing house", new SortByPublishingHouseCommand()},
                {"sort by pages", new SortByPagesCommand()},
                {"find all", new FindAllCommand()},
                {"new command", new EmptyCommand()},
                {"", new EmptyCommand()},
                {null, new EmptyCommand()}};
    }
}
