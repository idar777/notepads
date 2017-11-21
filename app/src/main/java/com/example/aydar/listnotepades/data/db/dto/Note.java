package com.example.aydar.listnotepades.data.db.dto;


/**
 * Created by aydar on 09.11.17.
 */

public class Note implements IDto {
    private long id;
    private long userID;
    private String name;
    private String text;
    private String date;

    public Note(long userID, String name, String text) {
        this.userID = userID;
        this.name = name;
        this.text = text;
    }

    public Note(long noteID) {
        this.id = noteID;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

