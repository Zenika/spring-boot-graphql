package com.zenika.graphql.application;

import com.zenika.graphql.AbstractSpringBootIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.graphql.test.tester.GraphQlTester;
import org.springframework.graphql.test.tester.HttpGraphQlTester;

class GraphqlControllerTest extends AbstractSpringBootIntegrationTest {
    private static final Integer TOLKIEN_ID = 1;

    @Autowired
    HttpGraphQlTester httpGraphQlTester;

    @Test
    void shouldReturnTolkien() {
        // GIVEN
        String query = """
                query MyQuery($id: ID!) {
                  authorById(id: $id) {
                    id
                    lastName
                    firstName
                  }
                }
                """;

        // WHEN
        GraphQlTester.Response response = httpGraphQlTester.document(query)
                .variable("id", TOLKIEN_ID)
                .execute();

        // THEN
        response
                .errors().verify()
                .path("authorById")
                .matchesJsonStrictly(
                        """
                                {
                                  "id": "1",
                                  "lastName": "Tolkien",
                                  "firstName": "JRR"
                                }
                                """
                );
    }

    @Test
    void shouldReturnNotFound() {
        // GIVEN

        // WHEN
        GraphQlTester.Response response = httpGraphQlTester.documentName("authorById")
                .variable("id", 666)
                .execute();

        // THEN
        response
                .errors()
                .expect(error -> error.getErrorType().equals(ErrorType.NOT_FOUND))
                .verify();
    }

    @Test
    void shouldHandleBadRequest() {
        // GIVEN
        String id = "BAD_VALUE";

        // WHEN
        GraphQlTester.Response response = httpGraphQlTester.documentName("authorById")
                .variable("id", id)
                .execute();

        // THEN
        response
                .errors()
                .expect(error -> error.getErrorType().equals(ErrorType.BAD_REQUEST))
                .verify();
    }

}