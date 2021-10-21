package com.example.phonestore.entity.location;

public class wards {
    private int code;
    private String name;
    private String codename;
    private String division_type;
    private int phone_code;

    public wards(int code, String name, String codename, String division_type, int phone_code) {
        this.code = code;
        this.name = name;
        this.codename = codename;
        this.division_type = division_type;
        this.phone_code = phone_code;
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

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "wards{" +
                "code=" + code +
                ", name='" + name + '\'' +
                ", codename='" + codename + '\'' +
                ", division_type='" + division_type + '\'' +
                ", phone_code=" + phone_code +
                '}';
    }
}