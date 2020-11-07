package com.tahayvz.publisherapp.commands;

public class BookCommand {
    private Long id;
    private Long authorId;
    private String name;
    private String descriptions;
    private Long publishingHouseId;
    private String subname;
    private Long printingNumber;
    private Long isbnNumber;

    public String getSubname() {
        return subname;
    }

    public void setSubname(String subname) {
        this.subname = subname;
    }

    public Long getPrintingNumber() {
        return printingNumber;
    }

    public void setPrintingNumber(Long printingNumber) {
        this.printingNumber = printingNumber;
    }

    public Long getIsbnNumber() {
        return isbnNumber;
    }

    public void setIsbnNumber(Long isbnNumber) {
        this.isbnNumber = isbnNumber;
    }

    public BookCommand() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
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

}
