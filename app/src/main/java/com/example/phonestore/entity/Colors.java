package com.example.phonestore.entity;

import com.google.gson.annotations.SerializedName;

public class Colors {
    @SerializedName("id")
    private int id;
    @SerializedName("phone_color")
    private String Color;

    public boolean isSelectedColor = false;

    public Colors(String color) {
        Color = color;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String color) {
        Color = color;
    }

    @Override
    public String toString() {
        return "Colors{" +
                "id=" + id +
                ", Color='" + Color + '\'' +
                ", isSelectedColor=" + isSelectedColor +
                '}';
    }
}
