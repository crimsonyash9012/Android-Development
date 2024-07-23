package com.example.thejournalapp;

import com.google.firebase.Timestamp;

// model class
public class Journal {

    private String title;
    private String thoughts;
    private String imageUrl;
    private String userId;
    private Timestamp timeAdded;
    private String userName;

    public Journal() {
    }

    public Journal(String title, String thoughts, String imgURL, String userId, Timestamp timeAdded, String userName) {
        this.title = title;
        this.thoughts = thoughts;
        this.imageUrl = imgURL;
        this.userId = userId;
        this.timeAdded = timeAdded;
        this.userName = userName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThoughts() {
        return thoughts;
    }

    public void setThoughts(String thoughts) {
        this.thoughts = thoughts;
    }

    public String getImgURL() {
        return imageUrl;
    }

    public void setImgURL(String imgURL) {
        this.imageUrl = imgURL;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Timestamp getTimeAdded() {
        return timeAdded;
    }

    public void setTimeAdded(Timestamp timeAdded) {
        this.timeAdded = timeAdded;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
