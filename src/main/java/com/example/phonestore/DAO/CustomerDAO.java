package com.example.phonestore.DAO;

import com.example.phonestore.object.GetCustomer;
import com.example.phonestore.object.ResponseMessageGetHistoryOrder;

import java.util.List;

public interface CustomerDAO {
    public List<GetCustomer> getListCustomer();
    public ResponseMessageGetHistoryOrder getListHistory(String theId);
}
