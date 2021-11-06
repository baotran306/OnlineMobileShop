package com.example.phonestore.object;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PostColor {
    @JsonProperty("color")
    private String color;

    public PostColor(String color) {
        this.color = color;
    }

    public PostColor() {
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
