package com.example.phonestore.entity.location;

import com.example.phonestore.entity.location.districts;

import java.util.ArrayList;
import java.util.List;

public class City {
    private String name;
    private int code;
    private String codename;
    private String division_type;
    private int phone_code;
    private List<districts> districts ;


    public City(String name, int code, String codename, String division_type, int phone_code, List<districts> districts) {
        this.name = name;
        this.code = code;
        this.codename = codename;
        this.division_type = division_type;
        this.phone_code = phone_code;
        this.districts = districts;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<districts> getDistricts() {
        return districts;
    }

    public void setDistricts(List<districts> districts) {
        this.districts = districts;
    }

    public String getCodename() {
        return codename;
    }

    public void setCodename(String codename) {
        this.codename = codename;
    }

    public String getDivision_type() {
        return division_type;
    }

    public void setDivision_type(String division_type) {
        this.division_type = division_type;
    }

    public int getPhone_code() {
        return phone_code;
    }

    public void setPhone_code(int phone_code) {
        this.phone_code = phone_code;
    }

    @Override
    public String toString() {
        return "City{" +
                "name='" + name + '\'' +
                ", code=" + code +
                ", codename='" + codename + '\'' +
                ", division_type='" + division_type + '\'' +
                ", phone_code=" + phone_code +
                ", districts=" + districts.toString() +
                '}';
    }
}
