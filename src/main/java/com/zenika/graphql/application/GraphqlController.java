package com.zenika.graphql.application;

import com.zenika.graphql.application.model.AuthorDto;
import com.zenika.graphql.application.model.AuthorInputDto;
import com.zenika.graphql.application.model.BookDto;
import com.zenika.graphql.application.model.BookInputDto;
import com.zenika.graphql.domain.LibraryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.BatchMapping;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
public class GraphqlController {
    private final LibraryService libraryService;

    @QueryMapping
    List<BookDto> books() {
        log.info("==== Getting all books");
        return libraryService.listAllBooks();
    }

    @QueryMapping
    BookDto bookById(@Argument Integer id) {
        log.info("==== Getting book by id: {}", id);
        return libraryService.getBookById(id);
    }

    @QueryMapping
    AuthorDto authorById(@Argument Integer id) {
        log.info("==== Getting author by id: {}", id);
        return libraryService.getAuthorById(id);
    }

    @BatchMapping(typeName = "Book", field = "author")
    Map<BookDto, AuthorDto> authorsForBooks(List<BookDto> books) {
        log.debug(" Getting authors for books: {}", books);
        return libraryService.getAuthorsForBooks(books);
    }

    @BatchMapping(typeName = "Author", field = "books")
    Map<AuthorDto, List<BookDto>> booksForAuthors(List<AuthorDto> authors) {
        log.debug(" Getting books for authors: {}", authors);
        return libraryService.getBooksForAuthors(authors);
    }

    @MutationMapping
    BookDto addBook(@Argument(name = "book") BookInputDto bookDto) {
        log.info("==== Adding book: {}", bookDto);
        return libraryService.addBook(bookDto);
    }

    @MutationMapping
    AuthorDto addAuthor(@Argument(name = "author") @Valid AuthorInputDto authorDto) {
        log.info("==== Adding author: {}", authorDto);
        return libraryService.addAuthor(authorDto);
    }

    //@SchemaMapping(typeName = "Book", field = "author")
    AuthorDto authorForBook(BookDto book) {
        return libraryService.getAuthorById(book.authorId());
    }

    //@SchemaMapping(typeName = "Author", field = "books")
    List<BookDto> booksForAuthor(AuthorDto author) {
        return libraryService.getBooksByAuthorId(author.id());
    }

}
