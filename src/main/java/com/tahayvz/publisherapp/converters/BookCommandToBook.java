package com.tahayvz.publisherapp.converters;

import com.tahayvz.publisherapp.commands.BookCommand;
import com.tahayvz.publisherapp.domain.Author;
import com.tahayvz.publisherapp.domain.Book;
import com.tahayvz.publisherapp.domain.PublishingHouse;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class BookCommandToBook implements Converter<BookCommand, Book> {

    @Nullable
    @Override
    public Book convert(BookCommand source) {
        if (source == null) {
            return null;
        }

        final Book book = new Book();
        book.setId(source.getId());

        if(source.getAuthorId() != null){
            Author author = new Author();
            PublishingHouse publishingHouse = new PublishingHouse();
            author.setId(source.getAuthorId());
            publishingHouse.setId((source.getPublishingHouseId()));
            book.setAuthor(author);
            book.setPublishingHouse(publishingHouse);
            //  book.setDescriptions(source.getDescriptions());
            author.addBook(book);
        }

        book.setName(source.getName());

        book.setSubname(source.getSubname());
        book.setPrintingNumber(source.getPrintingNumber());
        book.setIsbnNumber(source.getIsbnNumber());
        book.setDescriptions(source.getDescriptions());

        return book;
    }

}