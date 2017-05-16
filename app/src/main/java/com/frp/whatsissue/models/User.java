package com.frp.whatsissue.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Saurav on 16/05/17.
 */

public class User {

    @SerializedName("avatar_url")
    private String avatar;

    @SerializedName("login")
    private String username;

    public User(String avatar, String username) {
        this.avatar = avatar;
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
