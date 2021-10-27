package com.example.phonestore.object;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Color {
    @JsonProperty("id")
    private int id;
    @JsonProperty("phone_color")
    private String color;

    public Color() {
    }

    public Color(int id, String color) {
        this.id = id;
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
