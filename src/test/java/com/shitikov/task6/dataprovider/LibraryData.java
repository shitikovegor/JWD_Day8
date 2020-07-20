package com.shitikov.task6.dataprovider;

import com.shitikov.task6.model.builder.BookBuilder;
import com.shitikov.task6.model.entity.Book;
import com.shitikov.task6.model.entity.Library;

public class LibraryData {

    public static final Book BOOK_1;
    public static final Book BOOK_2;
    public static final Book BOOK_3;
    public static final Book BOOK_4;
    public static final Book BOOK_5;

    static {
        BOOK_1 = new BookBuilder()
                .buildName("Introducing Maven")
                .buildAuthors("Varanasi B.")
                .buildPublishingHouse("Apress")
                .buildPages(135)
                .buildBook();


        BOOK_2 = new BookBuilder()
                .buildName("Pro Spring Boot 2")
                .buildAuthors("Gutierrez F.")
                .buildPublishingHouse("Morgan Kaufmann")
                .buildPages(479)
                .buildBook();

        BOOK_3 = new BookBuilder()
                .buildName("Head First Java")
                .buildAuthors("Sierra K., Bates B.")
                .buildPublishingHouse("O Reilly")
                .buildPages(688)
                .buildBook();

        BOOK_4 = new BookBuilder()
                .buildName("Go in Practice")
                .buildAuthors("Butcher M., Farina M.")
                .buildPublishingHouse("Manning Publications")
                .buildPages(312)
                .buildBook();

        BOOK_5 = new BookBuilder()
                .buildName("Java Software Solutions")
                .buildAuthors("Lewis J., Loftus W.")
                .buildPublishingHouse("Pearson")
                .buildPages(800)
                .buildBook();
    }

    public static void setLibrary() {
        Library.getInstance().removeAll();
        Library.getInstance().add(BOOK_1);
        Library.getInstance().add(BOOK_2);
        Library.getInstance().add(BOOK_3);
        Library.getInstance().add(BOOK_4);
        Library.getInstance().add(BOOK_5);
    }
}
