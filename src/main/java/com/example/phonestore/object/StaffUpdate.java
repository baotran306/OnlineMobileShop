package com.example.phonestore.object;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StaffUpdate {
    @JsonProperty("staff_id")
    private String id;
    @JsonProperty("role_id")
    private int idRole;
    @JsonProperty("salary")
    private Double salary;

    public StaffUpdate(String id, int idRole, Double salary) {
        this.id = id;
        this.idRole = idRole;
        this.salary = salary;
    }

    public StaffUpdate() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getIdRole() {
        return idRole;
    }

    public void setIdRole(int idRole) {
        this.idRole = idRole;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }
}
