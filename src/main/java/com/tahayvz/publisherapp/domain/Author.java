package com.tahayvz.publisherapp.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(exclude = {"publishingHouse"})
@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Lob
    private String descriptions;


    @ManyToOne
    private PublishingHouse publishingHouse;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "author")
    private Set<Book> books = new HashSet<>();

    public Author() {
    }

    public Author(String name, String descriptions) {
        this.name = name;
        this.descriptions=descriptions;
    }

    public Author(String name, String descriptions, PublishingHouse publishingHouse) {
        this.name = name;
        this.descriptions=descriptions;
        this.publishingHouse = publishingHouse;
    }

    public Author addBook(Book book){
        book.setAuthor(this);
        this.books.add(book);
        return this;
    }
}
