package com.shitikov.task6.model.dao.impl.comparator;

import com.shitikov.task6.model.entity.Book;

import java.util.Comparator;

public class PagesComparator implements Comparator<Book> {
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
