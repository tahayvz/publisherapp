package com.tahayvz.publisherapp.converters;

import com.tahayvz.publisherapp.commands.BookCommand;
import com.tahayvz.publisherapp.domain.Book;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class BookToBookCommand implements Converter<Book, BookCommand> {

    @Synchronized
    @Nullable
    @Override
    public BookCommand convert(Book book) {
        if (book == null) {
            return null;
        }

        BookCommand bookCommand = new BookCommand();
        bookCommand.setId(book.getId());
        if (book.getAuthor() != null) {
            bookCommand.setAuthorId(book.getAuthor().getId());
            bookCommand.setPublishingHouseId(book.getPublishingHouse().getId());
        }
        bookCommand.setName(book.getName());

        bookCommand.setSubname(book.getSubname());
        bookCommand.setPrintingNumber(book.getPrintingNumber());
        bookCommand.setIsbnNumber(book.getIsbnNumber());
        bookCommand.setDescriptions(book.getDescriptions());

        return bookCommand;
    }
}