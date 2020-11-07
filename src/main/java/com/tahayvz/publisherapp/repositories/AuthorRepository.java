package com.tahayvz.publisherapp.repositories;

import com.tahayvz.publisherapp.domain.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author, Long> {

}