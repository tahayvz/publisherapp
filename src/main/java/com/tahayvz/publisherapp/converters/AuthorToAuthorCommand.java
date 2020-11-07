package com.tahayvz.publisherapp.converters;

import com.tahayvz.publisherapp.domain.Author;
import com.tahayvz.publisherapp.commands.AuthorCommand;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class AuthorToAuthorCommand implements Converter<Author, AuthorCommand> {

    @Synchronized
    @Nullable
    @Override
    public AuthorCommand convert(Author author) {
        if (author == null) {
            return null;
        }

        AuthorCommand authorCommand = new AuthorCommand();
        authorCommand.setId(author.getId());
        if (author.getPublishingHouse() != null) {
            authorCommand.setPublishingHouseId(author.getPublishingHouse().getId());
        }
//        authorCommand.setAmount(author.getAmount());
        authorCommand.setName(author.getName());
        authorCommand.setDescriptions(author.getDescriptions());

        return authorCommand;
    }
}