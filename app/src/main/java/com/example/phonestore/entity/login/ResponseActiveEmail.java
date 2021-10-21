package com.example.phonestore.entity.login;

import com.google.gson.annotations.SerializedName;

public class ResponseActiveEmail {
    @SerializedName("info")
    private String info;
    @SerializedName("result")
    private Boolean result;
    @SerializedName("data")
    private DataActive data;

    public ResponseActiveEmail() {
    }

    public ResponseActiveEmail(String info, Boolean result, DataActive data) {
        this.info = info;
        this.result = result;
        this.data = data;
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

    public DataActive getData() {
        return data;
    }

    public void setData(DataActive data) {
        this.data = data;
    }
}
