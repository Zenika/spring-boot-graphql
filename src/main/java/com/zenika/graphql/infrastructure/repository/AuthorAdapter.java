package com.zenika.graphql.infrastructure.repository;

import com.zenika.graphql.application.model.AuthorDto;
import com.zenika.graphql.domain.AuthorMapper;
import com.zenika.graphql.domain.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Stream;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthorAdapter {
    private final AuthorRepository authorRepository;

    public AuthorDto getAuthorById(Integer id) {
        log.debug("getAuthorById: {}", id);
        return authorRepository.findById(id)
                .map(AuthorMapper::mapAuthor)
                .orElseThrow(() -> new NotFoundException("Author [" + id + "] not found"));
    }

    public Stream<AuthorDto> getAuthorsByIds(List<Integer> authorIds) {
        return authorRepository.findByIdIn(authorIds)
                .stream()
                .map(AuthorMapper::mapAuthor);
    }
}
