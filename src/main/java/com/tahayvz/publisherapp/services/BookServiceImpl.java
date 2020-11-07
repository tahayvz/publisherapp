package com.tahayvz.publisherapp.services;

import com.tahayvz.publisherapp.commands.BookCommand;
import com.tahayvz.publisherapp.converters.BookCommandToBook;
import com.tahayvz.publisherapp.converters.BookToBookCommand;
import com.tahayvz.publisherapp.domain.Author;
import com.tahayvz.publisherapp.domain.Book;
import com.tahayvz.publisherapp.repositories.AuthorRepository;
import com.tahayvz.publisherapp.repositories.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class BookServiceImpl implements BookService {

    private final BookToBookCommand bookToBookCommand;
    private final BookCommandToBook bookCommandToBook;
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public BookServiceImpl(BookToBookCommand bookToBookCommand,
                           BookCommandToBook bookCommandToBook,
                           AuthorRepository authorRepository, BookRepository bookRepository) {
        this.bookToBookCommand = bookToBookCommand;
        this.bookCommandToBook = bookCommandToBook;
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public BookCommand findByAuthorIdAndBookId(Long authorId, Long bookId) {

        Optional<Author> authorOptional = authorRepository.findById(authorId);

        if (!authorOptional.isPresent()){
            //todo impl error handling
            log.error("author id not found. Id: " + authorId);
        }

        Author author = authorOptional.get();

        Optional<BookCommand> bookCommandOptional = author.getBooks().stream()
                .filter(book -> book.getId().equals(bookId))
                .map( book -> bookToBookCommand.convert(book)).findFirst();

        if(!bookCommandOptional.isPresent()){
            //todo impl error handling
            log.error("Book id not found: " + bookId);
        }

        return bookCommandOptional.get();
    }

    @Override
    @Transactional
    public BookCommand saveBookCommand(BookCommand command) {
        Optional<Author> authorOptional = authorRepository.findById(command.getAuthorId());

        if(!authorOptional.isPresent()){

            //todo toss error if not found!
            log.error("Author not found for id: " + command.getAuthorId());
            return new BookCommand();
        } else {
            Author author = authorOptional.get();

            Optional<Book> bookOptional = author
                    .getBooks()
                    .stream()
                    .filter(book -> book.getId().equals(command.getId()))
                    .findFirst();

            if(bookOptional.isPresent()){
                Book bookFound = bookOptional.get();
                bookFound.setName(command.getName());
                bookFound.setSubname(command.getSubname());
                bookFound.setPrintingNumber(command.getPrintingNumber());
                bookFound.setIsbnNumber(command.getIsbnNumber());
                bookFound.setDescriptions(command.getDescriptions());

            } else {
                //add new Author
                Book book = bookCommandToBook.convert(command);
                book.setAuthor(author);
                author.addBook(book);
            }

            Author savedAuthor = authorRepository.save(author);

            Optional<Book> savedBookOptional = savedAuthor.getBooks().stream()
                    .filter(authorBooks -> authorBooks.getId().equals(command.getId()))
                    .findFirst();

            //check by name
            if(!savedBookOptional.isPresent()){
                //not totally safe... But best guess
                savedBookOptional = savedAuthor.getBooks().stream()
                        .filter(authorBooks -> authorBooks.getName().equals(command.getName()))
                        .findFirst();
            }

            //to do check for fail
            return bookToBookCommand.convert(savedBookOptional.get());
        }

    }

    @Override
    public void deleteById(Long authorId, Long idToDelete) {

        log.debug("Deleting author: " + authorId + ":" + idToDelete);

        Optional<Author> authorOptional = authorRepository.findById(authorId);

        if(authorOptional.isPresent()){
            Author author = authorOptional.get();
            log.debug("found author");

            Optional<Book> bookOptional = author
                    .getBooks()
                    .stream()
                    .filter(book -> book.getId().equals(idToDelete))
                    .findFirst();

            if(bookOptional.isPresent()){
                log.debug("found Book");
                Book bookToDelete = bookOptional.get();
                bookToDelete.setAuthor(null);
                author.getBooks().remove(bookOptional.get());
                authorRepository.save(author);
            }
        } else {
            log.debug("Author Id Not found. Id:" + authorId);
        }
    }

    @Override
    public Book findByName(String name) {
        return bookRepository.findByName(name);
    }

    @Override
    public List<Book> findAllByNameIgnoreCaseLike(String name) {
        return bookRepository.findAllByNameIgnoreCaseLike(name);
    }

    @Override
    public List<Book> findAllBySubnameIgnoreCaseLike(String subname) {
        return bookRepository.findAllBySubnameIgnoreCaseLike(subname);

    }

    @Override
    public List<Book> findAllByIsbnNumberLike(Long isbnNumber) {
        if(isbnNumber!=null) {
            return bookRepository.findAllByIsbnNumberLike(isbnNumber);
        }
        else{
            return new ArrayList<Book>();
        }
    }

}