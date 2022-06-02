package com.book.store.mappers;

import com.book.store.dto.BookDto;
import com.book.store.models.Book;

import java.util.List;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface BookMapper {
    
    @Mapping(target = "id", ignore = true)
    Book map(BookDto bookDto);

    @Named("mapWithouChapters")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "chapters", ignore = true)
    BookDto map(Book book);

    @IterableMapping(qualifiedByName = "mapWithouChapters")
    List<BookDto> map(List<Book> books);
}
