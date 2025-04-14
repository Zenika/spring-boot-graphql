package com.zenika.graphql.application.model;

import jakarta.validation.constraints.NotBlank;

public record AuthorInputDto(
    @NotBlank String firstName,
    @NotBlank String lastName
) {}
