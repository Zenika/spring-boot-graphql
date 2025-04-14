package com.zenika.graphql.infrastructure.repository;

import com.zenika.graphql.application.model.AuthorDto;
import com.zenika.graphql.application.model.AuthorInputDto;
import com.zenika.graphql.infrastructure.repository.mapper.AuthorMapper;
import com.zenika.graphql.domain.exception.NotFoundException;
import com.zenika.graphql.infrastructure.repository.model.AuthorEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthorAdapter {
    private final AuthorRepository authorRepository;

    public AuthorDto getAuthorById(Integer id) {
        log.debug("  getAuthorById: {}", id);
        return authorRepository.findById(id)
                .map(AuthorMapper::mapAuthor)
                .orElseThrow(() -> new NotFoundException("Author [" + id + "] not found"));
    }

    public List<AuthorDto> getAuthorsByIds(List<Integer> authorIds) {
        log.debug("  getAuthorsByIds: {}", authorIds);
        return authorRepository.findByIdIn(authorIds)
                .stream()
                .map(AuthorMapper::mapAuthor)
                .toList();
    }

    public AuthorDto addAuthor(AuthorInputDto authorDto) {
        log.debug("  addAuthor: {}", authorDto);
        AuthorEntity save = authorRepository.save(AuthorMapper.mapToAuthorEntity(authorDto));
        return AuthorMapper.mapAuthor(save);
    }
}
