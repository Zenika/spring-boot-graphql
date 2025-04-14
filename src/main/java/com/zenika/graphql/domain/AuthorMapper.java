package com.zenika.graphql.domain;

import com.zenika.graphql.application.model.AuthorDto;
import com.zenika.graphql.infrastructure.repository.model.AuthorEntity;

public class AuthorMapper {

    private AuthorMapper() {}

    public static AuthorDto mapAuthor(AuthorEntity author) {
        return new AuthorDto(
                author.id(),
                author.firstname(),
                author.lastname()
        );
    }
}
