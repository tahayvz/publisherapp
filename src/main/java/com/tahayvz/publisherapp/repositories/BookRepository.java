package com.tahayvz.publisherapp.repositories;

import com.tahayvz.publisherapp.domain.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Long> {

    Book findByName(String Name);

    List<Book> findAllByNameIgnoreCaseLike(String Name);

    List<Book> findAllBySubnameIgnoreCaseLike(String Subname);

    List<Book> findAllByIsbnNumberLike(Long IsbnNumber);


}
