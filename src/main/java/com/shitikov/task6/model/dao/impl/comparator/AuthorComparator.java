package com.shitikov.task6.model.dao.impl.comparator;

import com.shitikov.task6.model.entity.Book;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class AuthorComparator implements Comparator<Book> {
    @Override
    public int compare(Book book1, Book book2) {
        List<String> authors1 = book1.getAuthors();
        List<String> authors2 = book2.getAuthors();

        Collections.sort(authors1);
        Collections.sort(authors1);

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
