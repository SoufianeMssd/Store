package com.book.store.services.implementation;

import com.book.store.common.exceptions.CustomException;
import com.book.store.common.exceptions.ResourceNotFoundException;
import com.book.store.common.utils.CheckCategory;
import com.book.store.dto.BookChapterFirst;
import com.book.store.filters.BookFilter;
import com.book.store.models.Book;
import com.book.store.repositories.BookRepository;
import com.book.store.repositories.specification.BookSpec;
import com.book.store.services.BookService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void create(Book book) {
        if (book == null) {
            throw new CustomException("Book shouldn't be null");
        } else {
            book.getChapters().forEach((chapter) -> {
                chapter.setBook(book);
            });
            bookRepository.save(book);
        }
    }

    public Book findById(long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> {
            return new ResourceNotFoundException("Book not found with id : " + id);
        });
        return book;
    }

    public List<Book> findByName(String name) {
        return bookRepository.findAll(BookSpec.isBookName(name));
    }

    public List<Book> findByWriter(String writer) {
        return bookRepository.findAll(BookSpec.isBookWriterNamer(writer));
    }

    public List<Book> findAll() {
        return bookRepository.findAll().stream().filter(Book::getIsForSale).collect(Collectors.toList());
    }

    public List<Book> findAllPoliticBooks() {
        return bookRepository.findAll().stream().filter(CheckCategory::isPolitics).collect(Collectors.toList());
    }

    public Page<Book> findByCriteria(BookFilter bookFilter) {
        if (bookFilter == null) {
            throw new CustomException("BookFilter should not be null");
        } else {
            PageRequest pageRequest = PageRequest.of(bookFilter.getPage(), bookFilter.getSize());
            Specification<Book> specification = Specification.where(BookSpec.isBookName(bookFilter.getName()))
            .and(BookSpec.isBookWriterNamer(bookFilter.getWriter()));
            return bookRepository.findAll(specification, pageRequest);
        }
    }

    public void delete(long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> {
            return new ResourceNotFoundException("Book not found with id : " + id);
        });
        bookRepository.delete(book);
    }

    public List<BookChapterFirst> findAllAndFirstChapter() {
        return bookRepository.findBookFirstChapter();
    }
}
