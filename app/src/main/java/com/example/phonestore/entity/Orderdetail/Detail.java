package com.example.phonestore.entity.Orderdetail;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Detail {
    @SerializedName("created_date")
    private String date;
    @SerializedName("id_order")
    private int orderId;
    @SerializedName("status_id")
    private int statusId;
    @SerializedName("status_name")
    private String statusName;
    @SerializedName("list_order")
    private List<PhoneOrder> listOrder;

    public Detail() {
    }

    public Detail(String date, int orderId, int statusId, String statusName, List<PhoneOrder> listOrder) {
        this.date = date;
        this.orderId = orderId;
        this.statusId = statusId;
        this.statusName = statusName;
        this.listOrder = listOrder;
    }

    public List<PhoneOrder> getListOrder() {
        return listOrder;
    }

    public void setListOrder(List<PhoneOrder> listOrder) {
        this.listOrder = listOrder;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }
}
