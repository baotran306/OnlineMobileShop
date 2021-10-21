package com.example.phonestore.entity;

import com.google.gson.annotations.SerializedName;

public class ResponseMessage {
    @SerializedName("info")
    private String info;
    @SerializedName("result")
    private Boolean result;

    public ResponseMessage() {
    }

    public ResponseMessage(String info, Boolean result) {
        this.info = info;
        this.result = result;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "ResponseMessage{" +
                "info='" + info + '\'' +
                ", result=" + result +
                '}';
    }
}
