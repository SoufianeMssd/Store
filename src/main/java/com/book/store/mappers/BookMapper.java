//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.book.store.mappers;

import com.book.store.dto.BookDto;
import com.book.store.models.Book;
import java.util.List;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

@Mapper(
    componentModel = "spring"
)
public interface BookMapper {
    @Mapping(
        target = "id",
        ignore = true
    )
    Book map(BookDto var1);

    @Named("mapWithouChapters")
    @Mappings({@Mapping(
    target = "id",
    ignore = true
), @Mapping(
    target = "chapters",
    ignore = true
), @Mapping(
    target = "nbrChapters",
    expression = "java(getNbrOfChapters(book))"
)})
    BookDto map(Book var1);

    @IterableMapping(
        qualifiedByName = {"mapWithouChapters"}
    )
    List<BookDto> map(List<Book> var1);

    default int getNbrOfChapters(Book book) {
        return (int)book.getChapters().stream().count();
    }
}
