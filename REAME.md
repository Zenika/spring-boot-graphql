# Graphql Demo application using Spring boot

This project is an example of a graphql application in java with Spring boot Graphql.
It develop the notion :
- Graphql Controller
- N+1 problem with @BatchMapping
- Mutation (update)
- Exception HAndler
- Integration Test

## Graphql Controller
Spring documentation : https://docs.spring.io/spring-graphql/reference/controllers.html

## N+1 problem
A simple implementation of Graphql will execute a request for each Book of an Author
This can be solve using @BatchMapping : https://docs.spring.io/spring-graphql/reference/controllers.html#controllers.batch-mapping

# Mutation
Graphql mutation is used to update data in the server it's done using @MutationMapping