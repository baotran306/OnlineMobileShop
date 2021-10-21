package com.example.phonestore.entity.Orderdetail;

import com.google.gson.annotations.SerializedName;

public class PhoneOrder {
    @SerializedName("phone_id")
    private int phoneId;
    @SerializedName("phone_name")
    private String phoneName;
    @SerializedName("price")
    private Double price;
    @SerializedName("quantity")
    private int quantity;
    @SerializedName("image")
    private String image;
    private String Date;
    private int Status;

    public PhoneOrder() {
    }

    public PhoneOrder(int phoneId, String phoneName, Double price, int quantity, String image, String date, int status) {
        this.phoneId = phoneId;
        this.phoneName = phoneName;
        this.price = price;
        this.quantity = quantity;
        this.image = image;
        Date = date;
        Status = status;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public int getPhoneId() {
        return phoneId;
    }

    public void setPhoneId(int phoneId) {
        this.phoneId = phoneId;
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
