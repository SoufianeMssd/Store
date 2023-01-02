package com.book.store.repositories;

import com.book.store.dto.BookChapterFirst;
import com.book.store.models.Book;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface BookRepository extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book> {
    List<Book> findByName(String var1);

    @Query(
        value = "SELECT bk.NAME as book, ch.NAME as chapter, bk.CATEGORY as category from BOOK bk join CHAPTER ch on ch.BOOK_ID = bk.ID where ch.START between 0 and 20 group by bk.NAME, ch.NAME ;",
        nativeQuery = true
    )
    List<BookChapterFirst> findBookFirstChapter();
}
