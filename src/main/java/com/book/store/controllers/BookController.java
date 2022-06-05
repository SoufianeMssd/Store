package com.book.store.controllers;

import com.book.store.common.dto.PaginableResultSet;
import com.book.store.dto.BookChapterFirst;
import com.book.store.dto.BookDto;
import com.book.store.filters.BookFilter;
import com.book.store.mappers.BookMapper;
import com.book.store.models.Book;
import com.book.store.services.BookService;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> getBookById(@PathVariable long id) {
        BookDto bookDto = bookMapper.map(bookService.findById(id));
        return ResponseEntity.ok(bookDto);
    }

    @GetMapping(path = "/{bookName}/name")
    public ResponseEntity<List<BookDto>> getBookByName(@PathVariable String bookName) {
        List<Book> books = bookService.findByName(bookName);
        List<BookDto> bookDtos = bookMapper.map(books);
        logger.info("list of books by name: {}", bookDtos);

        return ResponseEntity.ok(bookDtos);
    }

    @GetMapping
    public ResponseEntity<List<BookDto>> getBookByWriter(@RequestParam String writer) {
        List<Book> books = bookService.findByWriter(writer);
        List<BookDto> bookDtos = bookMapper.map(books);
        logger.info("list of books by writer: {}", bookDtos);

        return ResponseEntity.ok(bookDtos);
    }
    
    @GetMapping("/all")
    public ResponseEntity<List<BookDto>> getAllBook() {
        List<Book> books = bookService.findAll();
        List<BookDto> bookDtos = bookMapper.map(books);
        logger.info("list of all books : {}", bookDtos);

        return ResponseEntity.ok(bookDtos);
    }
    
    @GetMapping("/politics")
    public ResponseEntity<List<BookDto>> getAllPoliticBooks() {
        List<Book> books = bookService.findAllPoliticBooks();
        List<BookDto> bookDtos = bookMapper.map(books);
        logger.info("list of all politic books : {}", bookDtos);

        return ResponseEntity.ok(bookDtos);
    }

    @GetMapping("/filter")
    public ResponseEntity<PaginableResultSet<Book, BookDto>> getBookByCriteria(@RequestBody BookFilter bookFilter) {
        Page<Book> page = bookService.findByCriteria(bookFilter);
        return ResponseEntity.ok(PaginableResultSet.of(page, bookMapper::map));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        bookService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/first")
    public ResponseEntity<List<BookChapterFirst>> getBookAndFirstChapter() {
        return ResponseEntity.ok(bookService.findAllAndFirstChapter());
    }
}
