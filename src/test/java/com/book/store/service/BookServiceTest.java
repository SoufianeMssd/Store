package com.book.store.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.AdditionalAnswers;
import org.mockito.ArgumentCaptor;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.assertj.core.groups.Tuple;
import org.assertj.core.util.Lists;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.book.store.common.exceptions.CustomException;
import com.book.store.models.Book;
import com.book.store.models.Category;
import com.book.store.models.Chapter;
import com.book.store.repositories.BookRepository;
import com.book.store.services.implementation.BookServiceImpl;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {
    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookServiceImpl bookServiceImpl;

    @Test
    public void create_book_with_no_chapters(){
        /* Given */
        /* variables */
        String bookName = "Love in time of cholera";
        String Writer = "Gabriel García Márquez";
        Book book = new Book();
        book.setCategory(Category.POLITICS);
        book.setName(bookName);
        book.setWriter(Writer);
        ArgumentCaptor<Book> bookCaptor = ArgumentCaptor.forClass(Book.class);
        given(bookRepository.save(any())).willAnswer(AdditionalAnswers.returnsFirstArg());

        /* when */
        bookServiceImpl.create(book);
        /* then */
        verify(bookRepository, times(1)).save(bookCaptor.capture());
        Book bookCaptorValue = bookCaptor.getValue();

        assertThat(bookCaptorValue).isNotNull();
        assertThat(bookCaptorValue.getCategory()).isEqualTo(Category.POLITICS);
        assertThat(bookCaptorValue.getWriter()).isEqualTo(Writer);
        assertThat(bookCaptorValue.getName()).isEqualTo(bookName);
        assertThat(bookCaptorValue.getChapters()).isEmpty();
    }

    @Test
    public void create_book_with_chapters(){
        /* Given */
        /* variables */
        String bookName = "Love in time of cholera";
        String Writer = "Gabriel García Márquez";
        String chapter1Name = "introduction";
        String chapter2Name = "Aléjandro's life";
        Book book = new Book();
        book.setCategory(Category.POLITICS);
        book.setName(bookName);
        book.setWriter(Writer);
        Chapter chapter1 = new Chapter();
        chapter1.setName(chapter1Name);
        chapter1.setStart(3);
        chapter1.setEnd(13);
        Chapter chapter2 = new Chapter();
        chapter2.setName(chapter2Name);
        chapter2.setStart(2);
        chapter2.setEnd(12);
        book.setChapters(Lists.newArrayList(chapter1, chapter2));
        ArgumentCaptor<Book> bookCaptor = ArgumentCaptor.forClass(Book.class);
        given(bookRepository.save(any())).willAnswer(AdditionalAnswers.returnsFirstArg());

        /* when */
        bookServiceImpl.create(book);
        /* then */
        verify(bookRepository, times(1)).save(bookCaptor.capture());
        Book bookCaptorValue = bookCaptor.getValue();

        assertThat(bookCaptorValue).isNotNull();
        assertThat(bookCaptorValue.getCategory()).isEqualTo(Category.POLITICS);
        assertThat(bookCaptorValue.getWriter()).isEqualTo(Writer);
        assertThat(bookCaptorValue.getName()).isEqualTo(bookName);
        assertThat(bookCaptorValue.getChapters()).isNotEmpty();
        assertThat(bookCaptorValue.getChapters()).hasSize(2).isNotNull()
        .extracting(Chapter::getName, Chapter::getStart).containsExactly(Tuple.tuple(chapter1Name, 3), Tuple.tuple(chapter2Name, 2));
    }

    @Test
    public void create_book_throw_exception_book_is_null() {
        assertThatExceptionOfType(CustomException.class).isThrownBy(() -> bookServiceImpl.create(null))
				.withMessage("Book shouldn't be null");
    }
}
