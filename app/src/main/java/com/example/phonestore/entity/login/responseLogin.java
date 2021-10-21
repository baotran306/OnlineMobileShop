package com.example.phonestore.entity.login;

import com.google.gson.annotations.SerializedName;

import retrofit2.http.Body;

public class responseLogin {
    @SerializedName("result")
    private Boolean result;
    @SerializedName("info")
    private info info;
    @SerializedName("message")
    private String message;

    public responseLogin(Boolean result, info info,String message) {
        this.result = result;
        this.info = info;
        this.message = message;
    }

    public Boolean isResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

    public info getInfo() {
        return info;
    }

    public void setInfo(info info) {
        this.info = info;
    }

    public Boolean getResult() {
        return result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "responseLogin{" +
                "result=" + result +
                ", info=" + info +
                ", message='" + message + '\'' +
                '}';
    }
}
