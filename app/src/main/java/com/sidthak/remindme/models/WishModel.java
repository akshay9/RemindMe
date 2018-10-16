package com.sidthak.remindme.models;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class WishModel {
    FirebaseFirestore db;
    String id;
    String title;
    String description;
    String creator;

    public WishModel() {
        db = FirebaseFirestore.getInstance();
    }

    public WishModel(String title, String description, String creator) {
        db = FirebaseFirestore.getInstance();
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

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public void addToFireStore() {
        db.collection("wishes").add(this);
    }
}
