package com.book.store.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.book.store.dto.BookChapterFirst;
import com.book.store.filters.BookFilter;
import com.book.store.models.Book;

public interface BookService {
    void create(Book book);

    List<Book> findByName(String name);
    List<Book> findByWriter(String writer);
    List<Book> findAll();
    List<Book> findAllPoliticBooks();
    Page<Book> findByCriteria(BookFilter bookFilter);
    Book findById(long id);
    void delete(long id);
    List<BookChapterFirst> findAllAndFirstChapter();
}
