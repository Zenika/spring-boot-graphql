package com.zenika.graphql.application;

import com.zenika.graphql.domain.exception.NotFoundException;
import graphql.GraphQLError;
import graphql.schema.DataFetchingEnvironment;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.GraphQlExceptionHandler;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;

@Slf4j
@ControllerAdvice
public class GraphqlExceptionHandler {

    @SuppressWarnings("unused")
    @GraphQlExceptionHandler(NotFoundException.class)
    GraphQLError handleNotFound(NotFoundException notFoundException, DataFetchingEnvironment env) {
        log.warn("NotFound Exception: {}", notFoundException.getMessage());
        return GraphQLError
                .newError()
                .errorType(ErrorType.NOT_FOUND)
                .message(notFoundException.getMessage())
                .path(env.getExecutionStepInfo().getPath())
                .location(env.getField().getSourceLocation())
                .build();
    }

    @SuppressWarnings("unused")
    @GraphQlExceptionHandler(BindException.class)
    GraphQLError handleBindException(BindException exception, DataFetchingEnvironment env) {
        log.warn("BindException: {}", exception.getMessage());
        return GraphQLError
                .newError()
                .errorType(ErrorType.BAD_REQUEST)
                .message(exception.getMessage())
                .path(env.getExecutionStepInfo().getPath())
                .location(env.getField().getSourceLocation())
                .build();
    }

    @GraphQlExceptionHandler(ValidationException.class)
    @SuppressWarnings("unused")
    GraphQLError handle(ValidationException notFoundException, DataFetchingEnvironment env) {
        log.warn("Validation Exception: {}", notFoundException.getMessage());
        return GraphQLError
                .newError()
                .errorType(graphql.ErrorType.ValidationError)
                .message(notFoundException.getMessage())
                .path(env.getExecutionStepInfo().getPath())
                .location(env.getField().getSourceLocation())
                .build();
    }
}
