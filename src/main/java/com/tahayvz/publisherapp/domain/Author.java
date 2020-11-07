package com.tahayvz.publisherapp.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

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
}
