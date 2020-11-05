package com.tahayvz.publisherapp.commands;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;

public class PublishingHouseCommand {

    private Long id;

    @NotBlank
    @Size(min = 3, max = 255)
    private String name;

    private String descriptions;

    public PublishingHouseCommand() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

}
