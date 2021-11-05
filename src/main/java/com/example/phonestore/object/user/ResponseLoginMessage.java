package com.example.phonestore.object.user;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseLoginMessage {
    @JsonProperty("message")
    private String message;
    @JsonProperty("result")
    private Boolean result;
    @JsonProperty("info")
    private StaffInfo staffInfo;

    public ResponseLoginMessage() {
    }

    public ResponseLoginMessage(String message, Boolean result, StaffInfo staffInfo) {
        this.message = message;
        this.result = result;
        this.staffInfo = staffInfo;
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

    public StaffInfo getStaffInfo() {
        return staffInfo;
    }

    public void setStaffInfo(StaffInfo staffInfo) {
        this.staffInfo = staffInfo;
    }
}
