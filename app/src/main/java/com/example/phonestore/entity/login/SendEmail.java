package com.example.phonestore.entity.login;

import com.google.gson.annotations.SerializedName;

public class SendEmail {
    @SerializedName("email")
    private String email;
    @SerializedName("username")
    private String username;

    public SendEmail(String email, String username) {
        this.email = email;
        this.username = username;
    }

    public SendEmail() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
