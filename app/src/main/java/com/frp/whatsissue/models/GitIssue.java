package com.frp.whatsissue.models;

import com.google.gson.annotations.SerializedName;

import java.util.Observable;

/**
 * Created by Saurav on 16/05/17.
 */

public class GitIssue {

    @SerializedName("number")
    private Integer number;

    @SerializedName("title")
    private String title;

    @SerializedName("user")
    private User user;

    @SerializedName("state")
    private String state;

    @SerializedName("body")
    private String body;

    public GitIssue(Integer number, String title, User user, String state, String body) {
        this.number = number;
        this.title = title;
        this.user = user;
        this.state = state;
        this.body = body;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
