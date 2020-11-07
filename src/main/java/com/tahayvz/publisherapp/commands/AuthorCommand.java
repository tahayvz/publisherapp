package com.tahayvz.publisherapp.commands;

import java.util.HashSet;
import java.util.Set;

public class AuthorCommand {
    private Long id;
    private Long publishingHouseId;
    private String name;
    private String descriptions;

    private Set<BookCommand> books = new HashSet<>();

    public AuthorCommand() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPublishingHouseId() {
        return publishingHouseId;
    }

    public void setPublishingHouseId(Long publishingHouseId) {
        this.publishingHouseId = publishingHouseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public Set<BookCommand> getBooks() {
        return books;
    }

    public void setBooks(Set<BookCommand> books) {
        this.books = books;
    }
}