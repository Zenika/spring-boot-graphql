package com.zenika.graphql.application.model;

public record BookInputDto(
        String label,
        String summary,
        Integer authorId
){}
