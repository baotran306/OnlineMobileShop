package com.example.phonestore.object;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ResponseMessageGetHistoryOrder {
    @JsonProperty("message")
    private String message;
    @JsonProperty("result")
    private Boolean result;
    @JsonProperty("info")
    private List<CustomerOrder> customerOrder;

    public ResponseMessageGetHistoryOrder(String message, Boolean result, List<CustomerOrder> customerOrder) {
        this.message = message;
        this.result = result;
        this.customerOrder = customerOrder;
    }

    public ResponseMessageGetHistoryOrder() {
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

    public List<CustomerOrder> getCustomerOrder() {
        return customerOrder;
    }

    public void setCustomerOrder(List<CustomerOrder> customerOrder) {
        this.customerOrder = customerOrder;
    }
}
