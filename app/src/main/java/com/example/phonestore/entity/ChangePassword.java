package com.example.phonestore.entity;

import com.google.gson.annotations.SerializedName;

public class ChangePassword {
    @SerializedName("username")
    private String username;
    @SerializedName("old_pass")
    private String oldPassword;
    @SerializedName("new_pass")
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
