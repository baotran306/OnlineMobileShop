package com.example.phonestore.entity.login;

import static com.example.phonestore.api.customer.ApiActiveUser.active;

import com.example.phonestore.entity.ResponseMessage;
import com.google.gson.annotations.SerializedName;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SendOTP {
    private String email;
    @SerializedName("verify_number_input")
    private int verifyNumberInput;

    @SerializedName("verify_number_session")
    private int verifyNumberSession;

    public SendOTP(String email, int verifyNumberInput, int verifyNumberSession) {
        this.email = email;
        this.verifyNumberInput = verifyNumberInput;
        this.verifyNumberSession = verifyNumberSession;
    }

    public SendOTP() {

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getVerifyNumberInput() {
        return verifyNumberInput;
    }

    public void setVerifyNumberInput(int verifyNumberInput) {
        this.verifyNumberInput = verifyNumberInput;
    }

    public int getVerifyNumberSession() {
        return verifyNumberSession;
    }

    public void setVerifyNumberSession(int verifyNumberSession) {
        this.verifyNumberSession = verifyNumberSession;
    }
}
