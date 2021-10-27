package com.example.phonestore.object;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Brand {
    @JsonProperty("id")
    private int id;
    @JsonProperty("type_phone_name")
    private String brand;

    public Brand() {
    }

    public Brand(int id, String brand) {
        this.id = id;
        this.brand = brand;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
