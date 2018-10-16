package com.sidthak.remindme.models;

import com.google.firebase.firestore.FirebaseFirestore;

public class UserModel {
    String username;
    String password;
    String emailid;
    int age;

    public UserModel() {
    }

    public UserModel(String username, String password, String emailid, int age) {
        this.username = username;
        this.password = password;
        this.emailid = emailid;
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmailid() {
        return emailid;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void addToFireStore() {
        FirebaseFirestore.getInstance().collection("users").add(this);
    }
}
