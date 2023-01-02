package com.book.store.services;

import com.book.store.dto.BookChapterFirst;
import com.book.store.filters.BookFilter;
import com.book.store.models.Book;
import java.util.List;
import org.springframework.data.domain.Page;

public interface BookService {
    void create(Book var1);

    List<Book> findByName(String var1);

    List<Book> findByWriter(String var1);

    List<Book> findAll();

    List<Book> findAllPoliticBooks();

    Page<Book> findByCriteria(BookFilter var1);

    Book findById(long var1);

    void delete(long var1);

    List<BookChapterFirst> findAllAndFirstChapter();
}
