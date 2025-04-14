package com.zenika.graphql.infrastructure.repository;

import com.zenika.graphql.infrastructure.repository.model.BookEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<BookEntity, Integer> {
    List<BookEntity> findByAuthorId(Integer authorId);

    List<BookEntity> findByAuthorIdIn(List<Integer> authorIds);
}
