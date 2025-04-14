package com.zenika.graphql.infrastructure.repository.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("BOOKS")
public record BookEntity (
    @Id
    Integer id,
    String label,
    String summary,
    Integer authorId
) {}
