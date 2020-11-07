package com.tahayvz.publisherapp.services;

import com.tahayvz.publisherapp.commands.AuthorCommand;
import com.tahayvz.publisherapp.domain.Author;

public interface AuthorService {
    AuthorCommand findByPublishingHouseIdAndAuthorId(Long publishingHouseId, Long authorId);

    AuthorCommand saveAuthorCommand(AuthorCommand command);

    void deleteById(Long publishingHouseId, Long idToDelete);

    Author findById(Long l);

    AuthorCommand findCommandById(Long l);
}
