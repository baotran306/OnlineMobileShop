package com.example.phonestore.entity.login;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class info {
    @SerializedName("id")
    private String id;
    @SerializedName("first_name")
    private String first_name;
    @SerializedName("last_name")
    private String last_name;
    @SerializedName("gender")
    private String gender;
    @SerializedName("identity_card")
    private String identity_card;
    @SerializedName("email")
    private String email;
    @SerializedName("phone_num")
    private String phone_num;
    @SerializedName("day_of_birth")
    private String day_of_birth;
    @SerializedName("address")
    private String address;
    @SerializedName("username")
    private String username;
    @SerializedName("is_active")
    private int is_active;

    public info(String id, String first_name, String last_name, String gender, String identity_card, String email, String phone_num, String day_of_birth, String address, String username, int is_active) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.gender = gender;
        this.identity_card = identity_card;
        this.email = email;
        this.phone_num = phone_num;
        this.day_of_birth = day_of_birth;
        this.address = address;
        this.username = username;
        this.is_active = is_active;
    }

    public info() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getIdentity_card() {
        return identity_card;
    }

    public void setIdentity_card(String identity_card) {
        this.identity_card = identity_card;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_num() {
        return phone_num;
    }

    public void setPhone_num(String phone_num) {
        this.phone_num = phone_num;
    }

    public String getDay_of_birth() {
        return day_of_birth;
    }

    public void setDay_of_birth(String day_of_birth) {
        this.day_of_birth = day_of_birth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getIs_active() {
        return is_active;
    }

    public void setIs_active(int is_active) {
        this.is_active = is_active;
    }

    @Override
    public String toString() {
        return "info{" +
                "id='" + id + '\'' +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", gender='" + gender + '\'' +
                ", identity_card='" + identity_card + '\'' +
                ", email='" + email + '\'' +
                ", phone_num='" + phone_num + '\'' +
                ", day_of_birth='" + day_of_birth + '\'' +
                ", address='" + address + '\'' +
                ", username='" + username + '\'' +
                ", is_active=" + is_active +
                '}';
    }
}
