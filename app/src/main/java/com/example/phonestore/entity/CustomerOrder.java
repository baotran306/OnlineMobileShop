package com.example.phonestore.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CustomerOrder {
    @SerializedName("customer_id")
    private String customerId;
    @SerializedName("address")
    private String address;
    @SerializedName("note")
    private String note;
    @SerializedName("phone_id_list")
    private List<Integer> id;
    @SerializedName("price_list")
    private List<Double> price;
    @SerializedName("quantity_list")
    private List<Integer> quantity;

    public CustomerOrder() {
    }

    public CustomerOrder(String customerId, String address, String note, List<Integer> id, List<Double> price, List<Integer> quantity) {
        this.customerId = customerId;
        this.address = address;
        this.note = note;
        this.id = id;
        this.price = price;
        this.quantity = quantity;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public List<Integer> getId() {
        return id;
    }

    public void setId(List<Integer> id) {
        this.id = id;
    }

    public List<Double> getPrice() {
        return price;
    }

    public void setPrice(List<Double> price) {
        this.price = price;
    }

    public List<Integer> getQuantity() {
        return quantity;
    }

    public void setQuantity(List<Integer> quantity) {
        this.quantity = quantity;
    }
}
