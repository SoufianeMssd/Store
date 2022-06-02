package com.book.store.repositories.specification;

import com.book.store.models.Book;

import com.book.store.models.Book_;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;


public final class BookSpec {

    private BookSpec () {}

    public static Specification<Book> isBookName(String name) {
        return (root, query, cb) -> StringUtils.isBlank(name) ? cb.conjunction() : cb.equal(root.get(Book_.NAME), name);
    }

    public static Specification<Book> isBookWriterNamer(String name) {
        return (root, query, cb) -> StringUtils.isBlank(name) ? cb.conjunction() : cb.equal(root.get(Book_.WRITER), name);
    }
}
