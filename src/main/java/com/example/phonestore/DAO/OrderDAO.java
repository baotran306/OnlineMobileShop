package com.example.phonestore.DAO;

import com.example.phonestore.object.CustomerOrder;
import com.example.phonestore.object.SearchDateOrder;

import java.util.List;

public interface OrderDAO {
    public List<CustomerOrder> getListOrder(SearchDateOrder searchDateOrder);
}
