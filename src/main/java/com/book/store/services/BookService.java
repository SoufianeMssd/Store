package com.book.store.services;

import java.util.List;

import com.book.store.dto.BookDto;
import com.book.store.models.Book;

public interface BookService {
    void create(Book book);

    List<BookDto> findByName(String name);
}
