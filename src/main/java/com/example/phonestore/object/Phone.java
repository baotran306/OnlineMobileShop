package com.example.phonestore.object;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Phone {
    @JsonProperty("id")
    private int id;
    @JsonProperty("phone_name")
    private String phoneName;
    @JsonProperty("color")
    private String color;
    @JsonProperty("type_phone_name")
    private String brand;
    @JsonProperty("img")
    private String image;
    @JsonProperty("quantity")
    private int quantity;
    @JsonProperty("price")
    private Double price;
    @JsonProperty("phone_description")
    private  String description;

    public Phone() {
    }

    public Phone(int id, String phoneName, String color, String brand, String image, int quantity, Double price, String description) {
        this.id = id;
        this.phoneName = phoneName;
        this.color = color;
        this.brand = brand;
        this.image = image;
        this.quantity = quantity;
        this.price = price;
        this.description = description;
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
