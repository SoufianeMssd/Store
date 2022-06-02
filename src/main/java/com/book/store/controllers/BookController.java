package com.book.store.controllers;

import com.book.store.dto.BookDto;
import com.book.store.mappers.BookMapper;
import com.book.store.models.Book;
import com.book.store.services.BookService;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
public class BookController {

    private final Logger logger = LoggerFactory.getLogger(BookController.class);
    
    private final BookService bookService;
    private final BookMapper bookMapper;

    public BookController(BookService bookService, BookMapper bookMapper){
        this.bookService = bookService;
        this.bookMapper = bookMapper;
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody BookDto bookDto) {
        Book book = bookMapper.map(bookDto);
        logger.info("book : {}", book);
        bookService.create(book);

        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/{bookName}")
    public ResponseEntity<List<BookDto>> getBookByName(@PathVariable String bookName) {
        List<BookDto> bookDtos = bookService.findByName(bookName);
        return ResponseEntity.ok(bookDtos);
    }
}
