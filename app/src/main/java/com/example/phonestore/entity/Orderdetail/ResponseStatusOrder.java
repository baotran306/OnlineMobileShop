package com.example.phonestore.entity.Orderdetail;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseStatusOrder {
    @SerializedName("info")
    private List<Detail> details;
    @SerializedName("message")
    private String message;
    @SerializedName("result")
    private Boolean result;

    public ResponseStatusOrder(List<Detail> details, String message, Boolean result) {
        this.details = details;
        this.message = message;
        this.result = result;
    }

    public ResponseStatusOrder() {
    }

    public List<Detail> getDetails() {
        return details;
    }

    public void setDetails(List<Detail> details) {
        this.details = details;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }
}
