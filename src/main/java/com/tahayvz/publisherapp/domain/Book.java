package com.tahayvz.publisherapp.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;

@Data
@EqualsAndHashCode(exclude = {"author"})
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String subname;
    private String descriptions;

    @NumberFormat
    private Long printingNumber;

    @NumberFormat
    private Long isbnNumber;

    @ManyToOne
    private Author author;

    @ManyToOne
    private PublishingHouse publishingHouse;

    public Book() {
    }

    public Book(String name, String descriptions) {
        this.name = name;
        this.descriptions = descriptions;
    }

    public Book(String name, String descriptions, Author author) {
        this.name = name;
        this.descriptions = descriptions;
        this.author = author;
    }
}
