package com.zenika.graphql.infrastructure.repository.mapper;

import com.zenika.graphql.application.model.AuthorDto;
import com.zenika.graphql.application.model.AuthorInputDto;
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

    public static AuthorEntity mapToAuthorEntity(AuthorInputDto authorDto) {
        return new AuthorEntity(
                null,
                authorDto.firstName(),
                authorDto.lastName()
        );
    }
}
