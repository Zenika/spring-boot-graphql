package com.zenika.graphql.application.model;

public record BookDto(
        Integer id,
        String label,
        String summary,
        Integer authorId
){}
