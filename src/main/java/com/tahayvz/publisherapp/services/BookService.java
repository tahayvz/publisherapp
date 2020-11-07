package com.tahayvz.publisherapp.services;

import com.tahayvz.publisherapp.commands.BookCommand;
import com.tahayvz.publisherapp.domain.Book;

import java.util.List;

public interface BookService {
    BookCommand findByAuthorIdAndBookId(Long authorId, Long bookId);

    BookCommand saveBookCommand(BookCommand command);

    void deleteById(Long authorId, Long idToDelete);

    Book findByName(String Name);

    List<Book> findAllByNameIgnoreCaseLike(String Name);

    List<Book> findAllBySubnameIgnoreCaseLike(String Subname);

    List<Book> findAllByIsbnNumberLike(Long isbnNumber);





}