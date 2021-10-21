package com.example.phonestore.entity.login;

import com.google.gson.annotations.SerializedName;

public class DataActive {
    @SerializedName("email")
    private String email;
    @SerializedName("verify_number")
    private int verifyNumber;

    public DataActive() {
    }

    public DataActive(String email, int verifyNumber) {
        this.email = email;
        this.verifyNumber = verifyNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getVerifyNumber() {
        return verifyNumber;
    }

    public void setVerifyNumber(int verifyNumber) {
        this.verifyNumber = verifyNumber;
    }
}
