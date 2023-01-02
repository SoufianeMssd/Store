package com.book.store.common.utils;

import com.book.store.models.Book;
import com.book.store.models.Category;

public final class CheckCategory {
    private CheckCategory() {
    }

    public static boolean isPolitics(Book book) {
        return Category.POLITICS.equals(book.getCategory());
    }
}
