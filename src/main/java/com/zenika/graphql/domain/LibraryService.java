package com.zenika.graphql.domain;

import com.zenika.graphql.application.model.AuthorDto;
import com.zenika.graphql.application.model.BookDto;
import com.zenika.graphql.infrastructure.repository.AuthorAdapter;
import com.zenika.graphql.infrastructure.repository.BookAdaptor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class LibraryService {
    private final BookAdaptor bookAdaptor;
    private final AuthorAdapter authorAdapter;

    public List<BookDto> listAllBooks() {
        return bookAdaptor.listAllBooks();
    }

    public BookDto getBookById(Integer id) {
        return bookAdaptor.getBookById(id);
    }

    public AuthorDto getAuthorById(Integer id) {
        return authorAdapter.getAuthorById(id);
    }

    public List<BookDto> getBooksByAuthorId(Integer id) {
        return bookAdaptor.getBooksByAuthorId(id);
    }

    public Map<BookDto, AuthorDto> getAuthorsForBooks(List<BookDto> books) {
        List<Integer> authorIds = books
                .stream()
                .map(BookDto::authorId)
                .distinct()
                .toList();


        Map<Integer, AuthorDto> authorById = authorAdapter.getAuthorsByIds(authorIds)
                .collect(Collectors.toMap(AuthorDto::id, Function.identity()));

        return books
                .stream()
                .map(bookDto -> Map.entry(
                                bookDto,
                                authorById.get(bookDto.authorId())
                        )
                )
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

    }

    public Map<AuthorDto, List<BookDto>> getBooksForAuthors(List<AuthorDto> authors) {
        List<Integer> authorIds = authors
                .stream()
                .map(AuthorDto::id)
                .distinct()
                .toList();

        Map<Integer, List<BookDto>> booksGroupByAuthorId = bookAdaptor.getBooksByAuthorIds(authorIds);

        return authors
                .stream()
                .map(authorDto -> Map.entry(
                                authorDto,
                                booksGroupByAuthorId.get(authorDto.id())
                        )
                )
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
