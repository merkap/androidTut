package com.example.book.dataModel;

public class BookModel {
    private String label;
    private String description;
    private String link;

    public BookModel(String title, String description, String link) {
        this.label = title;
        this.description = description;
        this.link = link;
    }

    public String getTitle() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
