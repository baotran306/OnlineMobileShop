package com.example.phonestore.object;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class PhonePost {

    int id;
    @JsonProperty("phone_name")
    @NotNull(message = "Phone name cannot be null")
    private String phoneName;
    @JsonProperty("color")
    private int color;
    @JsonProperty("phone_type")
    private int brand;
    @JsonProperty("image")
    @NotNull(message = "image cannot be null")
    private String image;
    @JsonProperty("quantity")
    @NotNull(message = "quantity cannot be null")
    private int quantity;
    @JsonProperty("price")
    @NotNull(message = "price cannot be null")
    private Double price;
    @JsonProperty("phone_description")
    private  String description;

    public PhonePost(int id,String phoneName, int color, int brand, String image, int quantity, Double price, String description) {
        this.phoneName = phoneName;
        this.color = color;
        this.brand = brand;
        this.image = image;
        this.quantity = quantity;
        this.price = price;
        this.description = description;
    }

    public PhonePost() {
    }
    public int getId(){
        return this.id;
    }
    public void setId(int id){
        this.id=id;
    }
    public String getPhoneName() {
        return phoneName;
    }

    public void setPhoneName(String phoneName) {
        this.phoneName = phoneName;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getBrand() {
        return brand;
    }

    public void setBrand(int brand) {
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

    @Override
    public String toString() {
        return "PhonePost{" +
                " phoneName='" + phoneName + '\'' +
                ", color=" + color +
                ", brand=" + brand +
                ", image='" + image + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", description='" + description + '\'' +
                '}';
    }
}
