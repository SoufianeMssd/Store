package com.book.store.controllers;

import com.book.store.common.dto.PaginableResultSet;
import com.book.store.dto.BookChapterFirst;
import com.book.store.dto.BookDto;
import com.book.store.filters.BookFilter;
import com.book.store.mappers.BookMapper;
import com.book.store.models.Book;
import com.book.store.models.Category;
import com.book.store.services.BookService;
import java.util.List;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/books"})
public class BookController {
    private final Logger logger = LoggerFactory.getLogger(BookController.class);
    private final BookService bookService;
    private final BookMapper bookMapper;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private static final String TOPIC = "NewBook";

    public BookController(BookService bookService, BookMapper bookMapper, KafkaTemplate<String, String> kafkaTemplate) {
        this.bookService = bookService;
        this.bookMapper = bookMapper;
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody @Valid BookDto bookDto) {
        Book book = this.bookMapper.map(bookDto);
        this.logger.info("book : {}", book);
        this.bookService.create(book);
        this.kafkaTemplate.send("NewBook", "new book create with name : " + book.getName() + " by : " + book.getWriter());
        return ResponseEntity.ok().build();
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<BookDto> getBookById(@PathVariable long id) {
        Book book = new Book();
        book.setCategory(Category.POLITICS);
        book.setName("my book");
        book.setWriter("soufiane");
        this.logger.info("to string book : {}", book.toString());
        BookDto bookDto = this.bookMapper.map(this.bookService.findById(id));
        return ResponseEntity.ok(bookDto);
    }

    @GetMapping(
        path = {"/{bookName}/name"}
    )
    public ResponseEntity<List<BookDto>> getBookByName(@PathVariable String bookName) {
        List<Book> books = this.bookService.findByName(bookName);
        List<BookDto> bookDtos = this.bookMapper.map(books);
        this.logger.info("list of books by name: {}", bookDtos);
        return ResponseEntity.ok(bookDtos);
    }

    @GetMapping
    public ResponseEntity<List<BookDto>> getBookByWriter(@RequestParam String writer) {
        List<Book> books = this.bookService.findByWriter(writer);
        List<BookDto> bookDtos = this.bookMapper.map(books);
        this.logger.info("list of books by writer: {}", bookDtos);
        return ResponseEntity.ok(bookDtos);
    }

    @GetMapping({"/all"})
    public ResponseEntity<List<BookDto>> getAllBook() {
        List<Book> books = this.bookService.findAll();
        List<BookDto> bookDtos = this.bookMapper.map(books);
        this.logger.info("list of all books : {}", bookDtos);
        return ResponseEntity.ok(bookDtos);
    }

    @GetMapping({"/politics"})
    public ResponseEntity<List<BookDto>> getAllPoliticBooks() {
        List<Book> books = this.bookService.findAllPoliticBooks();
        List<BookDto> bookDtos = this.bookMapper.map(books);
        this.logger.info("list of all politic books : {}", bookDtos);
        return ResponseEntity.ok(bookDtos);
    }

    @GetMapping({"/filter"})
    public ResponseEntity<PaginableResultSet<Book, BookDto>> getBookByCriteria(@RequestBody BookFilter bookFilter) {
        Page<Book> page = this.bookService.findByCriteria(bookFilter);
        BookMapper var10001 = this.bookMapper;
        var10001.getClass();
        return ResponseEntity.ok(PaginableResultSet.of(page, var10001::map));
    }

    @DeleteMapping({"/{id}"})
    public ResponseEntity<Void> delete(@PathVariable long id) {
        this.bookService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping({"/first"})
    public ResponseEntity<List<BookChapterFirst>> getBookAndFirstChapter() {
        return ResponseEntity.ok(this.bookService.findAllAndFirstChapter());
    }
}
