package com.zenika.graphql.application;

import com.zenika.graphql.AbstractSpringBootIntegrationTest;
import com.zenika.graphql.application.model.AuthorInputDto;
import graphql.ErrorType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.test.tester.GraphQlTester;
import org.springframework.graphql.test.tester.HttpGraphQlTester;

class MutationTest extends AbstractSpringBootIntegrationTest {
    @Autowired
    HttpGraphQlTester httpGraphQlTester;


    @Test
    void shouldAddAuthor() {
        // GIVEN
        AuthorInputDto authorInputDto = new AuthorInputDto(
                "John",
                "Do"
        );

        // WHEN
        GraphQlTester.Response response = httpGraphQlTester.documentName("addAuthor")
                .variable("author", authorInputDto)
                .execute();

        // THEN
        response
                .errors()
                .verify()
                .path("addAuthor")
                .matchesJsonStrictly("""
                        {"id":"3"}
                        """);
    }

    @Test
    void shouldValidateAuthor() {
        // GIVEN
        AuthorInputDto authorInputDto = new AuthorInputDto(
                "John",
                ""
        );

        // WHEN
        GraphQlTester.Response response = httpGraphQlTester.documentName("addAuthor")
                .variable("author", authorInputDto)
                .execute();

        // THEN
        response
                .errors().expect(error -> error.getErrorType().equals(ErrorType.ValidationError))
                .verify();
    }
}
