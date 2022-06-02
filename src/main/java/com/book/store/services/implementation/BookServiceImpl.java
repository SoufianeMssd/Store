package com.book.store.services.implementation;

import com.book.store.dto.BookDto;
import com.book.store.mappers.BookMapper;
import com.book.store.models.Book;
import com.book.store.repositories.BookRepository;
import com.book.store.repositories.specification.BookSpec;
import com.book.store.services.BookService;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService{

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public BookServiceImpl(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    @Override
    public void create(Book book) {
        book.getChapters().forEach(chapter -> chapter.setBook(book));
        bookRepository.save(book);
    }

    @Override
    public List<BookDto> findByName(String name) {
        List<Book> books = bookRepository.findAll(BookSpec.isBookName(name));
        return bookMapper.map(books);
    }
    
}
