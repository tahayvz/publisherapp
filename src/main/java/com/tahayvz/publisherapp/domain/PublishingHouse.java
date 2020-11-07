package com.tahayvz.publisherapp.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class PublishingHouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String password;

    @Lob
    private String descriptions;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "publishingHouse")
    private Set<Author> authors = new HashSet<>();

    public PublishingHouse addAuthor(Author author){
        author.setPublishingHouse(this);
        this.authors.add(author);
        return this;
    }
}