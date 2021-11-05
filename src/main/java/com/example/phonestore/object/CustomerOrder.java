package com.example.phonestore.object;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class CustomerOrder {
    @JsonProperty("id_order")
    private int idOrder;
    @JsonProperty("name")
    private String name;
    @JsonProperty("status_id")
    private int idStatus;
    @JsonProperty("status_name")
    private String status;
    @JsonProperty("address")
    private String address;
    @JsonProperty("created_date")
    private String createDate;
    @JsonProperty("note")
    private String note;
    @JsonProperty("list_order")
    private List<Order> listOrder;

    public CustomerOrder() {
    }

    public CustomerOrder(int idOrder, String name, int idStatus, String status, String address, String createDate, String note, List<Order> listOrder) {
        this.idOrder = idOrder;
        this.name = name;
        this.idStatus = idStatus;
        this.status = status;
        this.address = address;
        this.createDate = createDate;
        this.note = note;
        this.listOrder = listOrder;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(int idStatus) {
        this.idStatus = idStatus;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public List<Order> getListOrder() {
        return listOrder;
    }

    public void setListOrder(List<Order> listOrder) {
        this.listOrder = listOrder;
    }
}
