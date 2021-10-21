package com.example.phonestore.entity;

import android.app.Application;

public class temp extends Application {
    private int count;

    public temp() {
    }

    public temp(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
