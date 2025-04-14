package com.zenika.graphql.infrastructure.repository;

import com.zenika.graphql.infrastructure.repository.model.AuthorEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends CrudRepository<AuthorEntity, Integer> {
    List<AuthorEntity> findByIdIn(List<Integer> authorIds);
}
