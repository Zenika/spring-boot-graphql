package com.zenika.graphql.infrastructure.repository;

import com.zenika.graphql.application.model.BookDto;
import com.zenika.graphql.application.model.BookInputDto;
import com.zenika.graphql.infrastructure.repository.mapper.BookMapper;
import com.zenika.graphql.domain.exception.NotFoundException;
import com.zenika.graphql.infrastructure.repository.model.BookEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Component
@RequiredArgsConstructor
public class BookAdaptor {
    private final BookRepository bookRepository;

    public List<BookDto> listAllBooks() {
        log.debug("  listAllBooks");
        return StreamSupport.stream(bookRepository.findAll().spliterator(), false)
                .map(BookMapper::mapBookToDto).toList();
    }

    public BookDto getBookById(Integer id) {
        log.debug("  getBookById: {}", id);
        return bookRepository.findById(id)
                .map(BookMapper::mapBookToDto)
                .orElseThrow(() -> new NotFoundException("Book [" + id + "] not found"));
    }

    public List<BookDto> getBooksByAuthorId(Integer id) {
        log.debug("  getBooksByAuthorId: {}", id);
        return bookRepository.findByAuthorId(id)
                .stream()
                .map(BookMapper::mapBookToDto)
                .toList();
    }

    public Map<Integer, List<BookDto>> getBooksByAuthorIds(List<Integer> authorIds) {
        log.debug("  getBooksByAuthorIds: {}", authorIds);
        return bookRepository.findByAuthorIdIn(authorIds)
                .stream()
                .map(BookMapper::mapBookToDto)
                .collect(Collectors.groupingBy(BookDto::authorId));
    }

    public BookDto addBook(BookInputDto bookDto) {
        log.debug("  addBook: {}", bookDto);
        BookEntity newBook = bookRepository.save(BookMapper.mapToBookEntity(bookDto));
        return BookMapper.mapBookToDto(newBook);
    }
}
