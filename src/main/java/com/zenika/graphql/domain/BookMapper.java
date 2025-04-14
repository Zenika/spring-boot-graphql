package com.zenika.graphql.domain;

import com.zenika.graphql.application.model.BookDto;
import com.zenika.graphql.infrastructure.repository.model.BookEntity;

public class BookMapper {
    private BookMapper() {}

    public static BookDto mapBookToDto(BookEntity bookEntity) {
        return new BookDto(
                bookEntity.id(),
                bookEntity.label(),
                bookEntity.summary(),
                bookEntity.authorId()
        );
    }
}
