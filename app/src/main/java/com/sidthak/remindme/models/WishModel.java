package com.sidthak.remindme.models;

public class WishModel {
    String title;
    String description;
    String creator;

    public WishModel(String title, String description, String creator) {
        this.title = title;
        this.description = description;
        this.creator = creator;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getCreator() {
        return creator;
    }
}
