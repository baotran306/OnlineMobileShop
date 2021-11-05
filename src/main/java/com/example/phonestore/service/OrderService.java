package com.example.phonestore.service;

import com.example.phonestore.object.CustomerOrder;
import com.example.phonestore.object.SearchDateOrder;

import java.util.List;

public interface OrderService {
    public List<CustomerOrder> getListOrder(SearchDateOrder searchDateOrder);

}
