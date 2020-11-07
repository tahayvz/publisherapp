package com.tahayvz.publisherapp.services;

import com.tahayvz.publisherapp.commands.AuthorCommand;
import com.tahayvz.publisherapp.converters.AuthorCommandToAuthor;
import com.tahayvz.publisherapp.converters.AuthorToAuthorCommand;
import com.tahayvz.publisherapp.domain.Author;
import com.tahayvz.publisherapp.domain.PublishingHouse;
import com.tahayvz.publisherapp.repositories.AuthorRepository;
import com.tahayvz.publisherapp.repositories.PublishingHouseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
public class AuthorServiceImpl implements AuthorService{

    private final AuthorToAuthorCommand authorToAuthorCommand;
    private final AuthorCommandToAuthor authorCommandToAuthor;
    private final PublishingHouseRepository publishingHouseRepository;
    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorToAuthorCommand authorToAuthorCommand,
                             AuthorCommandToAuthor authorCommandToAuthor,
                             PublishingHouseRepository publishingHouseRepository, AuthorRepository authorRepository) {
        this.authorToAuthorCommand = authorToAuthorCommand;
        this.authorCommandToAuthor = authorCommandToAuthor;
        this.publishingHouseRepository = publishingHouseRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public AuthorCommand findByPublishingHouseIdAndAuthorId(Long publishingHouseId, Long authorId) {

        Optional<PublishingHouse> publishingHouseOptional = publishingHouseRepository.findById(publishingHouseId);

        if (!publishingHouseOptional.isPresent()){
            //todo impl error handling
            log.error("publishingHouse id not found. Id: " + publishingHouseId);
        }

        PublishingHouse publishingHouse = publishingHouseOptional.get();

        Optional<AuthorCommand> authorCommandOptional = publishingHouse.getAuthors().stream()
                .filter(author -> author.getId().equals(authorId))
                .map( author -> authorToAuthorCommand.convert(author)).findFirst();

        if(!authorCommandOptional.isPresent()){
            //todo impl error handling
            log.error("Author id not found: " + authorId);
        }

        return authorCommandOptional.get();
    }

    @Override
    @Transactional
    public AuthorCommand saveAuthorCommand(AuthorCommand command) {
        Optional<PublishingHouse> publishingHouseOptional = publishingHouseRepository.findById(command.getPublishingHouseId());

        if(!publishingHouseOptional.isPresent()){

            //todo toss error if not found!
            log.error("PublishingHouse not found for id: " + command.getPublishingHouseId());
            return new AuthorCommand();
        } else {
            PublishingHouse publishingHouse = publishingHouseOptional.get();

            Optional<Author> authorOptional = publishingHouse
                    .getAuthors()
                    .stream()
                    .filter(author -> author.getId().equals(command.getId()))
                    .findFirst();

            if(authorOptional.isPresent()){
                Author authorFound = authorOptional.get();
                authorFound.setName(command.getName());
                authorFound.setDescriptions(command.getDescriptions());

            } else {
                //add new Author
                Author author = authorCommandToAuthor.convert(command);
                author.setPublishingHouse(publishingHouse);
                publishingHouse.addAuthor(author);
            }

            PublishingHouse savedPublishingHouse = publishingHouseRepository.save(publishingHouse);

            Optional<Author> savedAuthorOptional = savedPublishingHouse.getAuthors().stream()
                    .filter(publishingHouseAuthors -> publishingHouseAuthors.getId().equals(command.getId()))
                    .findFirst();

            //check by name
            if(!savedAuthorOptional.isPresent()){
                //not totally safe... But best guess
                savedAuthorOptional = savedPublishingHouse.getAuthors().stream()
                        .filter(publishingHouseAuthors -> publishingHouseAuthors.getName().equals(command.getName()))
                        .filter(publishingHouseAuthors -> publishingHouseAuthors.getDescriptions().equals(command.getDescriptions()))
                        .findFirst();
            }

            //to do check for fail
            return authorToAuthorCommand.convert(savedAuthorOptional.get());
        }
    }

    @Override
    public void deleteById(Long publishingHouseId, Long idToDelete) {

        log.debug("Deleting author: " + publishingHouseId + ":" + idToDelete);

        Optional<PublishingHouse> publishingHouseOptional = publishingHouseRepository.findById(publishingHouseId);

        if(publishingHouseOptional.isPresent()){
            PublishingHouse publishingHouse = publishingHouseOptional.get();
            log.debug("found publishingHouse");

            Optional<Author> authorOptional = publishingHouse
                    .getAuthors()
                    .stream()
                    .filter(author -> author.getId().equals(idToDelete))
                    .findFirst();

            if(authorOptional.isPresent()){
                log.debug("found Author");
                Author authorToDelete = authorOptional.get();
                authorToDelete.setPublishingHouse(null);
                publishingHouse.getAuthors().remove(authorOptional.get());
                publishingHouseRepository.save(publishingHouse);
            }
        } else {
            log.debug("PublishingHouse Id Not found. Id:" + publishingHouseId);
        }
    }

    @Override
    public Author findById(Long l) {

        Optional<Author> authorOptional = authorRepository.findById(l);

        if (!authorOptional.isPresent()) {
            throw new RuntimeException("PublishingHouse Not Found!");
        }

        return authorOptional.get();
    }

    @Override
    @Transactional
    public AuthorCommand findCommandById(Long l) {
        return authorToAuthorCommand.convert(findById(l));
    }
}