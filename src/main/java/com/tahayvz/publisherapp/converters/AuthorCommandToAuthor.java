package com.tahayvz.publisherapp.converters;

import com.tahayvz.publisherapp.commands.AuthorCommand;
import com.tahayvz.publisherapp.domain.Author;
import com.tahayvz.publisherapp.domain.PublishingHouse;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class AuthorCommandToAuthor implements Converter<AuthorCommand, Author> {

    @Nullable
    @Override
    public Author convert(AuthorCommand source) {
        if (source == null) {
            return null;
        }

        final Author author = new Author();
        author.setId(source.getId());

        if(source.getPublishingHouseId() != null){
            PublishingHouse publishingHouse = new PublishingHouse();
            publishingHouse.setId(source.getPublishingHouseId());
            author.setPublishingHouse(publishingHouse);
            author.setDescriptions(source.getDescriptions());
            publishingHouse.addAuthor(author);
        }

        author.setName(source.getName());
        author.setDescriptions(source.getDescriptions());
        return author;
    }
}