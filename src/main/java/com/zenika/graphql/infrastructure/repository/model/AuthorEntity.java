package com.zenika.graphql.infrastructure.repository.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("AUTHORS")
public record AuthorEntity(
        @Id
        Integer id,
        String firstname,
        String lastname
) {
}
