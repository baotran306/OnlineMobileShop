package com.example.phonestore.entity;

import com.google.gson.annotations.SerializedName;

public class Brands {
    @SerializedName("id")
    private int id ;
    @SerializedName("type_phone_name")
    private String brandsName;

    public boolean isSelected = false;

    public Brands(int id, String brandsName) {
        this.id = id;
        this.brandsName = brandsName;
    }

    public Brands() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrandsName() {
        return brandsName;
    }

    public void setBrandsName(String brandsName) {
        this.brandsName = brandsName;
    }
}
