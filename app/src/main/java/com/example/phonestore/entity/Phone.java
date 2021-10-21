package com.example.phonestore.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Phone implements Serializable {

    private int id;
    @SerializedName("phone_name")
    private String phoneName;
    @SerializedName("img")
    private String image;
    @SerializedName("type_phone_name")
    private String phoneType;
    @SerializedName("phone_description")
    private String description;
    @SerializedName("quantity")
    private int quantity;
    private String color;
    private double price;
    public Boolean isSelect = false;

    public Phone() {
    }

    public Phone(int id, String phoneName, String image, String phoneType, String description, int quantity, String color, double price) {
        this.id = id;
        this.phoneName = phoneName;
        this.image = image;
        this.phoneType = phoneType;
        this.description = description;
        this.quantity = quantity;
        this.color = color;
        this.price = price;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPhoneType() {
        return phoneType;
    }

    public void setPhoneType(String phoneType) {
        this.phoneType = phoneType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "id=" + id +
                ", phoneName='" + phoneName + '\'' +
                ", image='" + image + '\'' +
                ", phoneType='" + phoneType + '\'' +
                ", description='" + description + '\'' +
                ", quantity=" + quantity +
                ", color='" + color + '\'' +
                ", price=" + price +
                ", isSelect=" + isSelect +
                '}';
    }
}
