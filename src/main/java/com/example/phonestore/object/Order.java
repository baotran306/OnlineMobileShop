package com.example.phonestore.object;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Order {
    @JsonProperty("phone_id")
    private int id;
    @JsonProperty("phone_name")
    private String phoneName;
    @JsonProperty("price")
    private Double price;
    @JsonProperty("quantity")
    private int quantity;

    public Order() {
    }

    public Order(int id, String phoneName, Double price, int quantity) {
        this.id = id;
        this.phoneName = phoneName;
        this.price = price;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhoneName() {
        return phoneName;
    }

    public void setPhoneName(String phoneName) {
        this.phoneName = phoneName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
