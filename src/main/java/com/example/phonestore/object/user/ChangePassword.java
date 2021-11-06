package com.example.phonestore.object.user;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ChangePassword {
    @JsonProperty("username")
    private String username;
    @JsonProperty("old_pass")
    private String oldPassword;
    @JsonProperty("new_pass")
    private String newPassword;

    public ChangePassword() {
    }

    public ChangePassword(String username, String oldPassword, String newPassword) {
        this.username = username;
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
