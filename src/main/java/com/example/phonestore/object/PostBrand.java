package com.example.phonestore.object;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PostBrand {
    @JsonProperty("name_phone_type")
    private String brand;

    public PostBrand() {
    }

    public PostBrand(String brand) {
        this.brand = brand;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}

